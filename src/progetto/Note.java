package progetto;

public class Note {
    int octave;
    String note;   

//////////////////////Constructors//////////////////////////////////////////////
    public Note(String note, int octave) {
        if (checkNote(note, octave)) {
            this.octave = octave;
            this.note = note.toLowerCase();
        }
        else
            System.out.println("Input non valido");
    }
    
    public Note(String note) {
        this.octave = 4;
        if (checkNote(note, octave)) {
            this.note = note.toLowerCase();
        }
        else
            System.out.println("Input non valido");
    }

    public Note(int midiPitch) {
        if (midiPitch >=0 && midiPitch <= 127) {
            getPitchOctaveFromMidi(midiPitch);
        }
        else
            System.out.println("Input non valido");
    }
////////////////////////////////////////////////////////////////////////////////
    private boolean checkNote(String note, int octave) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        String[] parts;
        String p = note.toLowerCase();
        boolean b = false;
   
        if (octave >= 0 && octave <=10) {
            int i = 0;
            while (b == false && i < notes.length) {
                if (notes[i].length() > 1) {
                    parts = notes[i].split("/");
                    if (parts[0].equals(p) || parts[1].equals(p))
                        b = true;
                }
                else {
                    if (notes[i].equals(p))
                        b = true;
                    else
                        b = false;
                }
                i++;
            }
        }
        else
            b = false;
        return b;
    }
    
    private void getPitchOctaveFromMidi(int midiPitch) {
        String pitchOctave = Conversions.conversionMidiToPitchOctave(midiPitch);
        String[] parts = pitchOctave.split("/");
        setOctave(Integer.parseInt(parts[1]));
        setNote(parts[0]);
    }
////////////////////////////////////////////////////////////////////////////////
    
    public int getOctave() {
        return octave;
    }

    public String getNote() {
        return note;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setNote(String note) {
        this.note = note;
    } 

////////////////////////////////////////////////////////////////////////////////
    public String getHelmholtz() {
        return Conversions.conversionToHelm(note, octave);
    }
    
    public String getScientificNotation() {
        return Conversions.conversionToScientificNotation(note, octave);
    }
    
    public String getNeolatinNotation() {
        return Conversions.conversionToNeolatin(note, octave);
    }
    
    public float getFreq() {
        return Conversions.conversionToFrequency(note, octave);
    }
    
    public int getMIDIPitch() {
        return Conversions.convertsionToMIDI(note, octave);
    }
}