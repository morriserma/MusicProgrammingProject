package progetto;

public class Manipulations {
    public static Note lowestPitchOfMelody(Melody melody) {
        if(melody.getLenght() > 0) {
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
        else
            return null;
    }
    
    public static Note highestPitchOfMelody(Melody melody) {
        if(melody.getLenght() > 0) {
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
        else
            return null;
    }
    
    public static short pitchClassInterval(Note n1, Note n2) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        String pitch1 = n1.getNote();
        String pitch2 = n2.getNote();
        short i = 0;
        boolean check = false;
        while(i < notes.length && check == false) {
            String[] oneNote = notes[i].split("/");
            int j = 0;
            while(j < oneNote.length && check == false) {
                if(oneNote[j].equals(pitch1))
                    check = true;
                j++;
            }
            i++;
        }
        short pitchClass1 = i;
        i = 0;
        check = false;
        while(i < notes.length && check == false) {
            String[] oneNote = notes[i].split("/");
            int j = 0;
            while(j < oneNote.length && check == false) {
                if(oneNote[j].equals(pitch2))
                    check = true;
                j++;
            }
            i++;
        }
        short pitchClass2 = i;
        short pci = (short) ((pitchClass2 - pitchClass1) % 12);
        if(pci < 0)
            pci += 12;
        
        return pci;
    }
}
