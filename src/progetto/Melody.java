package progetto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Melody {
    ArrayList<Note> melody;
    int bpm;
            
    public Melody() {
        melody = new ArrayList<>();
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
        return (Note) melody.get(i);
    }
    
    public void resetMelody() {
        melody.clear();
    }
    
    //Method to calc the lowest pitch of a melody converting it's note to midi
    public Note getLowestNote() {
        if(this.getLenght() > 0) {
            int minMidiPitch = 127;
            for (int i = 0; i < this.getLenght(); i++) {
                if(ConversionsTo.convertsionToMIDI(this.getNoteAt(i)) < minMidiPitch)
                    minMidiPitch = ConversionsTo.convertsionToMIDI(this.getNoteAt(i));
            }
            String pitchOctave = ConversionsFrom.conversionFromMidiPitch(minMidiPitch);
            String pitch = pitchOctave.split("/")[0];
            int octave = 0;
            try {
                octave = Integer.parseInt(pitchOctave.split("/")[1]);
            } catch (NumberFormatException e) {
                //System.out.println("Valore Continuous Pitch Class errato (" + nota + ")");
                throw new IllegalArgumentException("Valore ottava errato (" + pitchOctave.split("/")[1] + ")");
            }
            
            Note n = new Note(pitch, octave);
            return n;
        }
        else
            return null;
    }
    
    //Method to calc the highest pitch of a melody converting it's note to midi
    public Note getHighestNote() {
        if(this.getLenght() > 0) {
            int maxMidiPitch = 0;
            for (int i = 0; i < this.getLenght(); i++) {
                //String note = melody.getNoteAt(i).getNote();
                //int octave = melody.getNoteAt(i).getOctave();
                if(ConversionsTo.convertsionToMIDI(this.getNoteAt(i)) > maxMidiPitch)
                    maxMidiPitch = ConversionsTo.convertsionToMIDI(this.getNoteAt(i));
            }
            String pitchOctave = ConversionsFrom.conversionFromMidiPitch(maxMidiPitch);
            String pitch = pitchOctave.split("/")[0];
            int octave = Integer.parseInt(pitchOctave.split("/")[1]);
            Note n = new Note(pitch, octave);
            return n;
        }
        else
            return null;
    }
    
    //Method to calc the middle pitch (note at same distance from lowest and highest) 
    //of a melody converting it's note to midi
    public Note getMiddleNote() {
        Note highest = getHighestNote();
        Note lowest = getLowestNote();
        
        int highMidi = highest.getMIDIPitch();
        int lowMidi = lowest.getMIDIPitch();
        int middlePitch = (short) ((highMidi + lowMidi) / 2);
        String note = ConversionsFrom.conversionFromMidiPitch(middlePitch);
        String pitch = note.split("/")[0];
        int octave;
        try {
                octave = Integer.parseInt(note.split("/")[1]);
            } catch (NumberFormatException e) {
                //System.out.println("Valore Continuous Pitch Class errato (" + nota + ")");
                throw new IllegalArgumentException("Valore ottava errato (" + note.split("/")[1] + ")");
            }
        Note n = new Note(pitch, octave);
        return n;
    }
    
    //Methods to calculate the transposition (in different notation) of the melody by calling Manipulations.pcNoteTrasposition
    //for each note
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
    
    public void melodyBinomialTrasposition(String binTrasposition) {
        for(int i = 0; i < this.getLenght(); i++) {
            melody.set(i, Manipulations.binomialTrasposition(melody.get(i), binTrasposition));
        }
    }
    
    public void melodyCBRDiatonicTrasposition(String cbrTrasposition) {
        for(int i = 0; i < this.getLenght(); i++) {
            melody.set(i, Manipulations.cbrDiatonicTrasposition(melody.get(i), cbrTrasposition));
        }
    }
    
    //Method to count the number of rest in the melody
    public int countRest() {
        int cont = 0;
        for(int i = 0; i < this.getLenght(); i++) {
            if(melody.get(i).getNote().equalsIgnoreCase("-"))
                cont++;
        }
        return cont;
    }
    
    //Method to count the number of note in the melody
    public int countNotes() {
       int cont = 0;
        for(int i = 0; i < this.getLenght(); i++) {
            if(!melody.get(i).getNote().equalsIgnoreCase("-"))
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
    
    //Method to calc the time of a melody considering bpm and note/pause duration
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
    
    //Method that build a random melody from midiPitch
    public static Melody randomMelody(int numberOfNotes) {
        int random;
        Melody m1 = new Melody();
        for (int i = 0; i < numberOfNotes; i++) {
            random = (int) Math.floor(Math.random() * 127) + 1;
            String note = ConversionsFrom.conversionFromMidiPitch(random);
            int octave;
            try {
                octave = Integer.parseInt(note.split("/")[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Valore ottava errato (" + note.split("/")[1] + ")");
            }
            Note n = new Note(note.split("/")[0], octave);
            m1.add(n);
        } 
        return m1;
    }

    @Override
    public String toString() {
        String str = "Melody{" + "bpm=" + bpm + ", dimensions=" + melody.size() + "}\n";
        for (int i = 0; i < melody.size(); i++) {
            Note n = melody.get(i);
            //str += "Nota: " + melody1.getNote() + ", Ottava: " +melody1.getOctave() + "\n";
            if(i + 1 == melody.size())
                str += n.toString();
            else
                str += n.toString()+ "\n";
        }
        return str;
    }
    
    

}
