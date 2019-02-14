package progetto;

public class Manipulations {
    public static Note lowestPitchOfMelody(Melody melody) {
        if(melody.getLenght() > 0) {
            int minMidiPitch = 127;
            for (int i = 0; i < melody.getLenght(); i++) {
                //String note = melody.getNoteAt(i).getNote();
                //int octave = melody.getNoteAt(i).getOctave();
                if(ConversionsTo.convertsionToMIDI(melody.getNoteAt(i)) < minMidiPitch)
                    minMidiPitch = ConversionsTo.convertsionToMIDI(melody.getNoteAt(i));
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
    
    public static Note highestPitchOfMelody(Melody melody) {
        if(melody.getLenght() > 0) {
            int maxMidiPitch = 0;
            for (int i = 0; i < melody.getLenght(); i++) {
                //String note = melody.getNoteAt(i).getNote();
                //int octave = melody.getNoteAt(i).getOctave();
                if(ConversionsTo.convertsionToMIDI(melody.getNoteAt(i)) > maxMidiPitch)
                    maxMidiPitch = ConversionsTo.convertsionToMIDI(melody.getNoteAt(i));
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
    
    public static short pitchClassInterval(Note n1, Note n2) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        String pitch1 = n1.getNote();
        String pitch2 = n2.getNote();
        short i = 0;
        boolean check = false;
        while(i < notes.length && check == false) {
            String[] oneNote = notes[i].split("/");
            int j = 0;
            while(j < oneNote.length && check == false) {
                if(oneNote[j].equals(pitch1))
                    check = true;
                j++;
            }
            i++;
        }
        short pitchClass1 = i;
        i = 0;
        check = false;
        while(i < notes.length && check == false) {
            String[] oneNote = notes[i].split("/");
            int j = 0;
            while(j < oneNote.length && check == false) {
                if(oneNote[j].equals(pitch2))
                    check = true;
                j++;
            }
            i++;
        }
        short pitchClass2 = i;
        short pci = (short) ((pitchClass2 - pitchClass1) % 12);
        if(pci < 0)
            pci += 12;
        
        return pci;
    }
    
    public static short pitchClassIntervalInversion(Note n1, Note n2) {
        short pci = pitchClassInterval(n1, n2);
        short invertedPCI = (short) ((12 - pci) % 12);
        return invertedPCI;
    }
       
    public static String pitchClassIntervalName(short pci) {
        switch(pci){
            case 0: return "Unisono giusto/Ottava giusta";
            case 1: return "Seconda minore";
            case 2: return "Seconda maggiore";
            case 3: return "Terza minore";
            case 4: return "terza maggiore";
            case 5: return "Quarta giusta";
            case 6: return "Quarta eccedente/Quinta diminuita";
            case 7: return "Quinta giusta";
            case 8: return "Sesta minore";
            case 9: return "Sesta maggiore";
            case 10: return "Settima minore";
            case 11: return "Settima maggiore";
            default: throw new IllegalArgumentException("Il valore (" + pci + ") non è valido");
            }
    }
    
    public static short intervalClass(Note n1, Note n2) {
        short pci = pitchClassInterval(n1, n2);
        if(pci <= 6)
            return pci;
        else
            return (short) (12 - pci);
    }
    
    public static Note pcNoteTrasposition(Note n, int pcInterval) {
        if(pcInterval > 0 && pcInterval <= 12) {
            String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
                "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};

            String pitch1 = n.getNote();
            short i = 0;
            boolean check = false;
            while(i < notes.length && check == false) {
                String[] oneNote = notes[i].split("/");
                int j = 0;
                while(j < oneNote.length && check == false) {
                    if(oneNote[j].equals(pitch1))
                        check = true;
                    j++;
                }
                if(check == false)
                    i++;
            }
            short notePC = i;

            short trasposition = (short) ((notePC + pcInterval) % 12);
            String traspPitch = ConversionsFrom.conversionFromPC("" + trasposition);
            n.setNote(traspPitch.split("/")[0]);
            return n;
        }
        else
            throw new IllegalArgumentException("Il valore " + pcInterval + " non è valido");
    }
    
    public static int continuousPitchClassInterval(Note n1, Note n2) {
        int cpc1 = n1.getContinuousPitchCode();
        int cpc2 = n2.getContinuousPitchCode();
        return cpc2 - cpc1;
    }
    
    public static Note cpcNoteTrasposition(Note n, int cpcInterval) {
        int cpc = n.getContinuousPitchCode();
        
        int trasposition = cpc + cpcInterval;
        String traspPitch = ConversionsFrom.conversionFromCPC("" + trasposition);
        n.setNote(traspPitch.split("/")[0]);
        int octave = 0;
        try {
            octave = Integer.parseInt(traspPitch.split("/")[1]);
        } catch (NumberFormatException e) {
            //System.out.println("Valore Continuous Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore ottava errato (" + traspPitch.split("/")[1] + ")");
        }
        n.setOctave(octave);
        return n;
    }
    
    public static short ncInterval(Note n1, Note n2) {
        short nc1 = n1.getNameClass();
        short nc2 = n2.getNameClass();
        
        short nci = (short) ((nc2 - nc1) % 7);

        if(nci < 0)
            return (short) (nci + 7);
        else
            return nci;
            
    }
    
    public static short nameClassIntervalInversion(Note n1, Note n2) {
        return (short) ((7 - ncInterval(n1, n2)) % 7);
    }
    
    public static Note ncNoteTrasposition(Note n, short ncInterval) {
        if(ncInterval > 0 && ncInterval <= 7) {
            char[] notes = {'c', 'd', 'e', 'f', 'g', 'a', 'b'};

            int i = 0;
            while(i < notes.length && n.getNote().charAt(0) != notes[i])
                i++;
            short trasposition = (short) ((i + ncInterval) % 7);
            String traspPitch = ConversionsFrom.conversionFromNC("" + trasposition);
            n.setNote(traspPitch.split("/")[0]);
            return n;
        }
        else
            throw new IllegalArgumentException();        
    }
    
    public static short nameIntervalClass(Note n1, Note n2) {
        short nc1 = n1.getNameClass();
        short nc2 = n2.getNameClass();
        
        short nci = (short) Math.abs((nc2 - nc1) % 7);

        if(nci <= 3)
            return nci;
        else {
            return (short) (7 - nci);
        }
    }
    
    public static String ncIntervalName(short nci) {
        switch(Math.abs(nci)){
            case 0: return "Unisono/Ottava";
            case 1: return "Seconda";
            case 2: return "Terza";
            case 3: return "Quarta";
            case 4: return "Quinta";
            case 5: return "Sesta";
            case 6: return "Settima";
            default: throw new IllegalArgumentException("Il valore " + nci + " non è valido");
        }
    }
}
