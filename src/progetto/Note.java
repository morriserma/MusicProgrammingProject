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
        String pitchOctave = "";
        String[] parts;
        if (midiPitch >=0 && midiPitch <= 127) {
            pitchOctave = ConversionsFrom.conversionFromMidiPitch(midiPitch);
        }
        else
            System.out.println("Input non valido");
        
        parts = pitchOctave.split("/");
        if (checkNote(parts[0], Integer.parseInt(parts[1]))) {
            this.octave = Integer.parseInt(parts[1]);
            this.note = parts[0];
        }
    }
    
    public Note(String note, String notation) {
        String noteLow = note.toLowerCase();
        String notationLow = notation.toLowerCase();
        String[] parts;
        String pitchOctave = "";
        if (notationLow.equals("neolatina"))
            pitchOctave = ConversionsFrom.conversionFromNeolatin(noteLow);
        else if (notationLow.equals("anglosassone"))
            pitchOctave = ConversionsFrom.conversionFromAnglo(noteLow);
        else if (notationLow.equals("helmholtz"))
            System.out.println("");
        
        parts = pitchOctave.split("/");
        if (checkNote(parts[0], Integer.parseInt(parts[1]))) {
            this.octave = Integer.parseInt(parts[1]);
            this.note = parts[0];
        }
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
        return ConversionsTo.conversionToHelm(note, octave);
    }
    
    public String getScientificNotation() {
        return ConversionsTo.conversionToScientificNotation(note, octave);
    }
    
    public String getNeolatinNotation() {
        return ConversionsTo.conversionToNeolatin(note, octave);
    }
    
    public float getFreq() {
        return ConversionsTo.conversionToFrequency(note, octave);
    }
    
    public int getMIDIPitch() {
        return ConversionsTo.convertsionToMIDI(note, octave);
    }
}