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
            throw new IllegalArgumentException();
        
        parts = pitchOctave.split("/");
        int octaveParam = 0;
        try {
            octaveParam = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Errore di costruzione della nota!\nNumero dell'ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
            System.exit(1);
        }
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
        else if (notationLow.equals("pc"))
            pitchOctave = ConversionsFrom.conversionFromPC(noteLow);
        else if (notationLow.equals("cpc"))
            pitchOctave = ConversionsFrom.conversionFromCPC(noteLow);
        else if (notationLow.equals("nc"))
            pitchOctave = ConversionsFrom.conversionFromNC(noteLow);
        
        parts = pitchOctave.split("/");
        int octaveParam = 0;
        try {
            octaveParam = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Errore di costruzione della nota!\nNumero dell'ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
            System.exit(1);
        }
        if (ConversionsFrom.checkNoteValidity(parts[0], octaveParam)) {
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
    
    public short getPitchClass() {
        return ConversionsTo.conversionToPitchClass(note);
    }
    
    public int getContinuousPitchCode() {
        return ConversionsTo.conversionToCPC(note, octave);
    }
    
    public short getNameClass() {
        return ConversionsTo.conversionToNC(note);
    }
}