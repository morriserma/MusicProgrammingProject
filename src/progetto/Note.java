package progetto;

public class Note {
    int octave;
    String note;   

//////////////////////Constructors//////////////////////////////////////////////
    public Note(String note, int octave) {
        if (ConversionsFrom.checkNoteValidity(note, octave)) {
            this.octave = octave;
            this.note = note.toLowerCase();
        }
        else
            throw new IllegalArgumentException();
    }
    
    public Note(String note) {
        this.octave = 4;
        if (ConversionsFrom.checkNoteValidity(note, octave)) {
            this.note = note.toLowerCase();
        }
        else
            throw new IllegalArgumentException();
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
        if (ConversionsFrom.checkNoteValidity(parts[0], Integer.parseInt(parts[1]))) {
            this.octave = Integer.parseInt(parts[1]);
            this.note = parts[0];
        }
        else
            throw new IllegalArgumentException();
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
            pitchOctave = ConversionsFrom.conversionFromHelmholtz(note);
        else if (notationLow.equals("frequency"))
            pitchOctave = ConversionsFrom.conversionFromFrequency(note);
        
        parts = pitchOctave.split("/");
        if (ConversionsFrom.checkNoteValidity(parts[0], Integer.parseInt(parts[1]))) {
            this.octave = Integer.parseInt(parts[1]);
            this.note = parts[0];
        }
        else
            throw new IllegalArgumentException();
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