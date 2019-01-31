package progetto;

/**
 *
 * @author Morris
 */
public class Note {
    int octave;
    String note;
    int pitchMidi;
    Conversions c;
    

//////////////////////Constructors//////////////////////////////////////////////
    public Note(String note, int octave) {
        if (checkNote(note, octave)) {
            this.octave = octave;
            this.note = note.toLowerCase();
            c = new Conversions();
        }
        else
            System.out.println("Input non valido");
    }
    
    public Note(String note) {
        this.octave = 4;
        if (checkNote(note, octave)) {
            c = new Conversions();
            this.note = note.toLowerCase();
        }
        else
            System.out.println("Input non valido");
    }

    public Note(int pitchMidi) {
        if (pitchMidi >=0 && pitchMidi <= 127) {
            this.pitchMidi = pitchMidi;
            c = new Conversions();
        }
        else
            System.out.println("Input non valido");
    }
////////////////////////////////////////////////////////////////////////////////
    public boolean checkNote(String note, int octave) {
        String[] notes = {"c", "c#/db", "d", "d#/eb", "e", "f", "f#/gb", "g", "g#/ab", "a", "a#/bb", "b"};
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
////////////////////////////////////////////////////////////////////////////////
    
    public int getPitchMidi() {
        return pitchMidi;
    }

    public void setPitchMidi(int pitchMidi) {
        this.pitchMidi = pitchMidi;
    }
    
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
        return c.conversionToHelm(note, octave);
    }
    
    public String getScientificNotation() {
        return c.conversionToScientificNotation(note, octave);
    }
    
    public String getNeolatinNotation() {
        return c.conversionToNeolatin(note, octave);
    }
    
    public float getFreq() {
        return c.conversionToFrequency(note, octave);
    }
    
    public int getMIDI() {
        return c.convertsionToMIDI(note, octave);
    }
}