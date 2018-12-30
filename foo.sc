// https://sccode.org/1-5aB

~unity = 1/1;
~minor_3rd = 6/5;
~major_3rd = 5/4;
~major_5th = 3/2;
~major_6th = 12/7;
~minor_7th = 7/4;
~minor = [~unity, ~minor_3rd, ~major_5th];
~minor_6 = ~minor ++ ~major_6th;
~major = [~unity, ~major_3rd, ~major_5th];
~major_7 = ~major ++ ~minor_7th;

~note = {
    arg half_steps_above_A;
    440 * (2 ** (half_steps_above_A / 12))
};

~center = ~note.value(7);
~lower = ~center / 2.0;
~upper = ~center * 2.0;

~noise = {
    arg bass, chord; var full_chord, amplitude, side;
    while({bass < ~lower}, {bass = bass * 2});
    while({bass > ~upper}, {bass = bass / 2});
	bass.postln;
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

~bass.postln;
~time = 0;
~clock = TempoClock.new(60/60);
                                          ~play.value(~clock, ~time, ~bass, ~minor  ); ~time = ~time +  2   ; // christ
~bass = ~bass /  ~minor_7th             ; ~play.value(~clock, ~time, ~bass, ~major  ); ~time = ~time +  1   ; // je-
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
