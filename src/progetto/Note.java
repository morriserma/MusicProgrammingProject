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
        this.octave = octave;
        this.note = note;
        c = new Conversions();
    }
    
    public Note(String note) {
        this.note = note;
    }

    public Note(int pitchMidi) {
        this.pitchMidi = pitchMidi;
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
}