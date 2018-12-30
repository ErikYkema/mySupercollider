(
SynthDef(\bpfsaw, {
	arg freq = 220, center_freq = 2000, reciprocal_q = 0.2, attack = 2, sustain = 0, release = 3, c1 = 1, c2 = (-1), amp = 1, detune = 0.2;
	var sig, env;
	env = EnvGen.kr(Env([0, 1, 1, 0], [attack, sustain, release], [c1, 0, c2]), doneAction: 2);
	sig = Saw.ar(freq * LFNoise1.kr(0.5, detune).midiratio)!2;
	sig = BPF.ar(sig, center_freq, reciprocal_q);
	sig = sig * env * amp;
	Out.ar(0, sig);
}).add;
)

(
(2..5).choose.do {
	Synth(\bpfsaw, [
		\freq, (Scale.minor.degrees + 60).midicps.choose,
		\amp, 0.25,
		\center_freq, exprand(200, 500),
		\reciprocal_q, exprand(0.01, 0.5),
	]);
};
)

(
10.do {
	Synth(\bpfsaw, [
		\amp, 0.4,
		\freq, (Scale.minor.degrees + 40).midicps.choose,
		\center_freq, exprand(200, 400),
		\reciprocal_q, exprand(0.01, 0.2),
		// \detune, 2,
	])
};
)