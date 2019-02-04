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
    
    public int getLenght() {
        return melody.size();
    }
    
    public Note getNoteAt(int i) {
        return melody.get(i);
    }
    
    public void resetMelody() {
        melody.clear();
    }
}
