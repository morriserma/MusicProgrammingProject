package progetto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Melody {
    ArrayList<Note> melody;
    int bpm;
            
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
        return (Note) melody.get(i);
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
    
    public int countRest() {
        int cont = 0;
        for(int i = 0; i < this.getLenght(); i++) {
            if(melody.get(i).getNote().equals("-"))
                cont++;
        }
        return cont;
    }
    
    public int countNotes() {
       int cont = 0;
        for(int i = 0; i < this.getLenght(); i++) {
            if(!melody.get(i).getNote().equals("-"))
                cont++;
        }
        return cont; 
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
    
    public BigDecimal melodyTime() {
        if(getBpm() > 0) {
            double pulseForSecond = (double)60 / getBpm();
            double time = 0;
            for(int i = 0; i < this.getLenght(); i++) {
                //if(!melody.get(i).getNote().equals("-"))
                if(melody.get(i).getNoteRestGetNumericDuration() != 0)
                    time = time + (pulseForSecond / melody.get(i).getNoteRestGetNumericDuration());
            }
            BigDecimal finalTime = new BigDecimal("" + time + "");
            finalTime = finalTime.setScale(2, BigDecimal.ROUND_HALF_UP);
            return finalTime;
        }
        else
            throw new IllegalArgumentException("Valore BPM (" + getBpm()+ ") non valido");
    }

}
