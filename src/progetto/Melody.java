package progetto;

import java.util.ArrayList;

public class Melody {
    ArrayList<Note> melody;
            
    public Melody() {
        melody = new ArrayList<Note>();
    }
    
    public void add(Note n) {
        melody.add(n);
    }
    
    public void remove(Note n) {
        melody.remove(n);
    }
    
    public void editNote(Note newNote, int notePos) {
       
    }
    
    public int getLenght() {
        return melody.size();
    }
    
    public Note getNoteAt(int i) {
        return melody.get(i);
    }
    
    public void resetMelody() {
        melody.clear();
    }
    
    public Note getLowestNote() {
        return Manipulations.lowestPitchOfMelody(this);
    }
    
    public Note getHighestNote() {
        return Manipulations.highestPitchOfMelody(this);
    }
    
    public void melodyPCITrasposition(short pcTrasposition) {
        for(int i = 0; i < this.getLenght(); i++) {
            melody.set(i, Manipulations.pcNoteTrasposition(melody.get(i), pcTrasposition));    
        }
    }
    
    public void melodyCPCITrasposition(int pcTrasposition) {
        for(int i = 0; i < this.getLenght(); i++) {
            melody.set(i, Manipulations.cpcNoteTrasposition(melody.get(i), pcTrasposition));
        } 
    }
    
    public void melodyNCITrasposition(short pcTrasposition) {
        for(int i = 0; i < this.getLenght(); i++) {
            melody.set(i, Manipulations.ncNoteTrasposition(melody.get(i), pcTrasposition));
        } 
    }
}
