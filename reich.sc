////////////////////////////
// SynthDef ////////////////
////////////////////////////
(
	SynthDef(\clap,{
		arg output=0, vari=0, acc = 1;
		var noiz, amp, filt1, filt2, aEnv, fEnv, tap;

		noiz = WhiteNoise.ar;
		aEnv = EnvGen.kr(Env.perc(0.01, 0.4, 1, -10).delay(Rand(0,0.02)), 1, doneAction:2);
		fEnv = EnvGen.kr(Env.perc(0, 0.2, 1, -8), 1, doneAction:0)*600
		+ ((1100 + Rand(-100,300)) - (vari*100));

		filt1 = BPF.ar(noiz, fEnv, 0.4 - (vari * 0.1) )*0.4;
		filt2 = BPF.ar(noiz, 1000+ Rand(-50,50), 0.2)*1;

		acc = clip2((acc + 0.5) ,1);
		amp = ( (filt1+filt2) ) ;
		amp = RLPF.ar( amp, 10000 , 0.9)*2;
		amp = amp.softclip * aEnv * acc;
		Out.ar(output, amp);
	}).add;

	SynthDef(\reverb,{
		arg bus1, bus2;
		var in1, in2, mix;
		in1 = In.ar(bus1);
		in2 = In.ar(bus2);
		mix = FreeVerb2.ar(in1, in2, 0.2, 0.7, 0.98);
		Out.ar(0, mix);
	}).add;
)


////////////////////////////
// Sequence ////////////////
////////////////////////////

(
	var tempo, clapPattern, accPattern, performer1, performer2, numRepeates,
		effDef, bus1, bus2, group1, group2;

	tempo = 180/60;
	numRepeates = 8;

	bus1 = Bus.audio(s,1);
	bus2 = Bus.audio(s,1);
	group1 = Group.head(s);
	group2 = Group.after(group1);

	clapPattern = [1,1,1,\, 1,1,\, 1,\, 1,1,\];
    accPattern =  [1,0,0,0, 0,0,0, 0,0, 0,0,0];

	effDef = Synth.head(group2, \reverb, [\bus1, bus1.index, \bus2, bus2.index]);

	performer1 = Pbind(
		\instrument, \clap,
		\group, group1,
		\output, bus1.index,
		\vari, 1,
		\degree,Pseq(clapPattern,numRepeates*13) ,
		\acc,Pseq(accPattern,numRepeates*13),
		\dur, 0.5
	);

	performer2 = Pbind(
		\instrument, \clap,
		\group, group1,
		\output, bus2.index,
		\vari, 0,
		\degree, Pslide(clapPattern, 13, 12*numRepeates, 1, 0, true),
		\acc, Pslide(accPattern, 13, 12*numRepeates, 1, 0, true),
		\dur, 0.5
	);

	performer1.play(TempoClock(tempo));
	performer2.play(TempoClock(tempo));
)
