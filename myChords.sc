// https://sccode.org/1-5aB

~unity = 1/1;
// intervals
~minor_3rd = 2**(3/12);
~major_3rd = 2**(4/12);
~major_5th = 2**(7/12);
~major_6th = 2**(9/12);
~minor_7th = 2**(10/12);
~octave = 2;
// chords
~minor = [~unity, ~minor_3rd, ~major_5th];
~minor_6 = ~minor ++ ~major_6th;
~major = [~unity, ~major_3rd, ~major_5th];
~major_7 = ~major ++ ~minor_7th;

~note0 = 110 * (2**(6/12));

~note = {
    arg half_steps_above_A;
    ~note0 * (2 ** (half_steps_above_A / 12))
};
~tone = {
	arg freq;
	(freq/~note0).log2*12.round
};

~center = ~note.value(0);
~lower = ~center / 2;
~upper = ~center * 2; //~center / 4;

~noise = {
    arg bass, chord; var full_chord, amplitude, side;

	//bass.postln;
	b = ~tone.value(bass).round;

    while({bass < ~lower}, {bass = bass * 2});
    while({bass > ~upper}, {bass = bass / 2});

	//bass.postln;
	(b + "->"+ ~tone.value(bass).round).postln;

    full_chord = {arg octave;
        chord * octave
    }.flop.value(2 ** Array.series(3)).flat;
    amplitude = 4 / 5 / full_chord.size;
    side = Mix.new(
        {arg ratio; var frequency;
            frequency = bass * ratio;
            SinOsc.ar(frequency, 0, amplitude) * AmpCompA.kr(frequency)
        }.flop.value(full_chord)
    );
    [side, side];
};

~add = {
	arg change;
	~bass = ~bass * change;
};

~sub = {
	arg change;
	~bass = ~bass / change;
};

~sound = 0;
~play = {
    arg clock, time, bass, chord;
	bass.postln;
    clock.sched(time, {~sound.free; ~sound = {~noise.value(bass, chord)}.play});
};
~kill = {
    arg clock, time;
    clock.sched(time, {~sound.free});
};

~bass = ~center;
~add.value(~minor_7th); // pickup note
~add.value(~octave);

~bass.postln;
~time = 0;
~clock = TempoClock.new(120/60);
                                          ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  2   ; // christ
~sub.value(~minor_7th)             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // je-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  2   ; // sus lay
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // in
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // death's
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // strong
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // bands
                                          ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // for
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // our
~bass = ~bass *  ~major_5th * ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // of-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time + (1/2); // fe-
~bass = ~bass               * ~major_3rd; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time + (1/2); // en-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // ces-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor_6); ~time = ~time +  1   ; // gi-
~bass = ~bass /  ~major_5th * ~major_6th; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // i-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  4   ; // ven. therefore
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // let
                                          ~play.value(~clock, ~time, ~bass, ~minor_6); ~time = ~time +  1   ; // us
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // keep
~bass = ~bass *  ~major_5th * ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // the
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  2   ; // feast and
~bass = ~bass               / ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // sing
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // to
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // god
~bass = ~bass * (~major_5th**2)         ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // right
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // thank-
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // ful-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // ly
~bass = ~bass *  ~major_5th * ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // loud
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // songs
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  2   ; // of hal
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // leh
~bass = ~bass /  ~major_5th / ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // lu
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // u-
~bass = ~bass /  ~major_5th / ~major_3rd; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  2   ; // jah
~bass = ~bass *  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // hal-
~bass = ~bass               * ~major_3rd; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // al-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  1   ; // le
~bass = ~bass               / ~major_3rd; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // eh-
~bass = ~bass               / ~minor_3rd; ~play.value(~clock, ~time, ~bass, ~minor_6); ~time = ~time +  1   ; // lu-
~bass = ~bass /  ~major_5th * ~major_6th; ~play.value(~clock, ~time, ~bass, ~major_7); ~time = ~time +  1   ; // u-
~bass = ~bass /  ~major_5th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  2   ; // jah

~kill.value(~clock, ~time                 )