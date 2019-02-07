package progetto;

import java.util.ArrayList;

public class Manipulations {
    public static Note lowestPitchOfMelody(Melody melody) {
        int minMidiPitch = 127;
        for (int i = 0; i < melody.getLenght(); i++) {
            String note = melody.getNoteAt(i).getNote();
            int octave = melody.getNoteAt(i).getOctave();
            if(ConversionsTo.convertsionToMIDI(note, octave) < minMidiPitch)
                minMidiPitch = ConversionsTo.convertsionToMIDI(note, octave);
        }
        String pitchOctave = ConversionsFrom.conversionFromMidiPitch(minMidiPitch);
        String pitch = pitchOctave.split("/")[0];
        int octave = Integer.parseInt(pitchOctave.split("/")[1]);
        Note n = new Note(pitch, octave);
        return n;
    }
    
    public static Note highestPitchOfMelody(Melody melody) {
        int maxMidiPitch = 0;
        for (int i = 0; i < melody.getLenght(); i++) {
            String note = melody.getNoteAt(i).getNote();
            int octave = melody.getNoteAt(i).getOctave();
            if(ConversionsTo.convertsionToMIDI(note, octave) > maxMidiPitch)
                maxMidiPitch = ConversionsTo.convertsionToMIDI(note, octave);
        }
        String pitchOctave = ConversionsFrom.conversionFromMidiPitch(maxMidiPitch);
        String pitch = pitchOctave.split("/")[0];
        int octave = Integer.parseInt(pitchOctave.split("/")[1]);
        Note n = new Note(pitch, octave);
        return n;
    }
}
