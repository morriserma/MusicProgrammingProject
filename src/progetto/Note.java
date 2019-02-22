package progetto;

public class Note {
    int octave;
    String note;
    //double durata;
    NoteRest n;

//////////////////////Constructors//////////////////////////////////////////////
    public Note(String note, int octave) {
        if (ConversionsFrom.checkNoteValidity(note, octave)) {
            this.octave = octave;
            this.note = note.toLowerCase();
        }
        else
            throw new IllegalArgumentException("Errore di costruzione della nota (valore " + note + " non valido)");
    }
    
    public Note(String note) {
        if(!note.equals("-")) {
            this.octave = 4;
            if (ConversionsFrom.checkNoteValidity(note, octave)) {
                this.note = note.toLowerCase();
            }
            else
                throw new IllegalArgumentException("Errore di costruzione della nota (valore " + note + " non valido)");
        }
        else
            this.note = note;
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
        int octaveParam;
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
        else if (notationLow.equals("cnc"))
            pitchOctave = ConversionsFrom.conversionFromCNC(noteLow);
        else if (notationLow.equals("binomial"))
            pitchOctave = ConversionsFrom.conversionFromBinomial(noteLow);
        else if (notationLow.equals("br"))
            pitchOctave = ConversionsFrom.conversionFromBR(noteLow);
        else if (notationLow.equals("cbr"))
            pitchOctave = ConversionsFrom.conversionFromCBR(noteLow);
        
        pitchOctave = pitchOctave.toLowerCase();
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
        //return note.replaceAll(note.substring(0,1), note.substring(0,1).toUpperCase());
        return note;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setNote(String note) {
        this.note = note;
    } 

    private NoteRest getNoteRest() {
        if(n == null)
            return new NoteRest(0);
        return n;
    }
    
    public String getNoteRestToString() {
        if(n == null)
            return null;
        return n.toString();
    }
    
    public double getNoteRestGetNumericDuration() {
        if(n == null)
            return 0;
        return n.getNumericDuration();
    }

    public void setNoteRest(int exp) {
        n = new NoteRest(exp);
        //this.durata = n.getNumericDuration();
    }
    
    public void setNoteRest(int num, int exp) {
        n = new NoteRest(num, exp);
        //this.durata = n.getNumericDuration();
    }
    
    public void setNoteRest(String value, String notationLanguage) {
        n = new NoteRest(value, notationLanguage);
        //this.durata = n.getNumericDuration();
    }
    
    public int getNumerator() {
        if(n != null)
            return n.getNumerator();
        else
            return 0;
    }
    
    public int getDenominator() {
        if(n != null)
            return n.getDenominator();
        else
            return 0;
    }
    
    @Override
    public String toString() {
        if(n != null)
            return "Note{" + "octave=" + octave + ", note=" + note + ", type=" + n.toString() + '}';
        else
            return "Note{" + "octave=" + octave + ", note=" + note + ", type= Non specificato}";
    }
    
    
    
    

////////////////////////////////////////////////////////////////////////////////
    public String getHelmholtz() {
        return ConversionsTo.conversionToHelm(this);
    }
    
    public String getScientificNotation() {
        return ConversionsTo.conversionToScientificNotation(this);
    }
    
    public String getNeolatinNotation() {
        return ConversionsTo.conversionToNeolatin(this);
    }
    
    public float getFreq() {
        return ConversionsTo.conversionToFrequency(this);
    }
    
    public int getMIDIPitch() {
        return ConversionsTo.convertsionToMIDI(this);
    }
    
    public short getPitchClass() {
        return ConversionsTo.conversionToPitchClass(this);
    }
    
    public int getContinuousPitchCode() {
        return ConversionsTo.conversionToCPC(this);
    }
    
    public short getNameClass() {
        return ConversionsTo.conversionToNC(this);
    }
    
    public short getContinuousNameCode() {
        return ConversionsTo.converstionToCNC(this);
    }
    
    public short getBR() {
        return ConversionsTo.conversionToBR(this);
    }
    
    public String getBinomial() {
        return ConversionsTo.conversionToBinomial(this);
    }
    
    public int getCBR() {
        return ConversionsTo.conversionToCBR(this);
    }
}