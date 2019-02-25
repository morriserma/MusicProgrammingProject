package progetto;

import java.math.BigDecimal;

//Class that manage some type of Note manipulations that aren't converstions.
//For example lowest, highest pitch of a melody, Note traspositions, ecc.
public class Manipulations {
        
    //Method used by melody class to find the middle pitch of melody. (Done with midiPitch)
    public static short pitchClassInterval(Note n1, Note n2) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        String pitch1 = n1.getNote();
        String pitch2 = n2.getNote();
        short i = 0;
        boolean check = false;
        //Pc is the index of notes array where oneNote[j].equals(pitch1)
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
    
    //Method to invert pci
    public static short pitchClassIntervalInversion(Note n1, Note n2) {
        short pci = pitchClassInterval(n1, n2);
        short invertedPCI = (short) ((12 - pci) % 12);
        return invertedPCI;
    }
       
    //Method used to assign a Name to each pci
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
    
    //Method to calculate the intervalClass
    public static short intervalClass(Note n1, Note n2) {
        short pci = pitchClassInterval(n1, n2);
        if(pci <= 6)
            return pci;
        else
            return (short) (12 - pci);
    }
    
    //Method used to do a note trasposition according to pcInterval
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
    
    //Method used to calculate pc interval
    public static int continuousPitchClassInterval(Note n1, Note n2) {
        int cpc1 = n1.getContinuousPitchCode();
        int cpc2 = n2.getContinuousPitchCode();
        return cpc2 - cpc1;
    }
    
    //Method used to calculate cpc trasposition
    public static Note cpcNoteTrasposition(Note n, int cpcInterval) {
        int cpc = n.getContinuousPitchCode();
        
        int trasposition = cpc + cpcInterval;
        String traspPitch = ConversionsFrom.conversionFromCPC("" + trasposition);
        n.setNote(traspPitch.split("/")[0]);
        int octave = 0;
        try {
            octave = Integer.parseInt(traspPitch.split("/")[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valore ottava errato (" + traspPitch.split("/")[1] + ")");
        }
        n.setOctave(octave);
        return n;
    }
    
    //Method used to calculate nc interval
    public static short ncInterval(Note n1, Note n2) {
        short nc1 = n1.getNameClass();
        short nc2 = n2.getNameClass();
        
        short nci = (short) ((nc2 - nc1) % 7);

        if(nci < 0)
            return (short) (nci + 7);
        else
            return nci;
            
    }
    
    //Method used to calculate nc interval inversion
    public static short nameClassIntervalInversion(Note n1, Note n2) {
        return (short) ((7 - ncInterval(n1, n2)) % 7);
    }
    
    //Method used to calculate nc Note trasposition
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
    
    //Method used to calculate nameIntervalClass
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
    
    //Method to find intervals name
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
    
    //Method used to calculate note time based on bpm
    public static BigDecimal noteTime(int bpm, Note n) {
        if(bpm > 0) {
            double pulseForSecond = (double)60 / bpm;
            double time = 0;
            
            time = time + (pulseForSecond / n.getNoteRestGetNumericDuration());
            
            BigDecimal finalTime = new BigDecimal("" + time + "");
            finalTime = finalTime.setScale(2, BigDecimal.ROUND_HALF_UP);
            return finalTime;
        }
        else
            throw new IllegalArgumentException("Valore BPM (" + bpm + ") non valido");
    }
    
    //Method used to calculate binomial interval between 2 notes
    //PC and NC rapresents the column the row of the matrix
    public static String binomialInterval(Note n1, Note n2) {
        String[][] br_intervals = {
            {"P1", "A1", "2A1", "3A1", "4A1", "5A1", "6A1", "5d1", "4d1", "3d1", "2d1", "d1"},
            {"d2", "m2", "M2", "A2", "2A2", "3A2", "4A2", "5A2", "5d2", "4d2", "3d2", "2d2"},
            {"3d3", "2d3", "d3", "m3", "M3", "A3", "2A3", "3A3", "4A3", "5A3", "5d3", "4d3"},
            {"5d4", "4d4", "3d4", "2d4", "d4", "P4", "A4", "2A4", "3A4", "4A4", "5A4", "6A4"},
            {"5A5", "6A5", "5d5", "4d5", "3d5", "2d5", "d5", "P5", "A5", "2A5", "3A5", "4A5"},
            {"3A6", "4A6", "5A6", "5d6", "4d6", "3d6", "2d6", "d6", "m6", "M6", "A6", "2A6"},
            {"A7", "2A7", "3A7", "4A7", "5A7", "5d7", "4d7", "3d7", "2d7", "d7", "m7", "M7"},};
        
        short pc1 = n1.getPitchClass();
        short nc1 = n1.getNameClass();
        short pc2 = n2.getPitchClass();
        short nc2 = n2.getNameClass();
        
        short pc =  (short) ((short) (pc1 - pc2) % 12);
        short nc = (short) ((short) (nc1 - nc2) % 7);
        if(pc < 0)
            pc += 12;
        if(nc < 0)
            nc += 7;
        return br_intervals[nc][pc];
    }
    
    //Method used to calculate interval inversion
    public static String intervalInversion(String interval) {
        String[][] intervals = {
            {"P1", "A1", "2A1", "", "", "", "", "", "", "", "2d1", "d1"},
            {"d2", "m2", "M2", "A2", "2A2", "", "", "", "", "", "", ""},
            {"", "", "d3", "m3", "M3", "A3", "2A3", "", "", "", "", ""},
            {"", "", "", "2d4", "d4", "P4", "A4", "2A4", "", "", "", ""},
            {"", "", "", "", "", "2d5", "d5", "P5", "A5", "2A5", "", ""},
            {"", "", "", "", "", "", "", "d6", "m6", "M6", "A6", "2A6"},
            {"A7", "2A7", "", "", "", "", "", "", "", "d7", "m7", "M7"},};
        short pc = 0;
        short nc = 0;
        boolean check = false;
       while (nc < intervals.length && check == false) {
           pc = 0;
            while(pc < intervals[nc].length && check == false) {
                
                if(interval.equalsIgnoreCase(intervals[nc][pc]))
                    check = true;
                if(check == false)
                    pc++;
            }
            if(check == false)
                nc++;
            
        }
       
        if(check == false)
            throw new IllegalArgumentException("Intervallo inserito (" + interval + ") non valido");

        int invertedPC = (12 - pc) % 12;
        int invertedNC = (7 - nc) % 7;
        if(pc < 0)
            pc += 12;
        if(nc < 0)
            nc += 7;
        
        return intervals[invertedNC][invertedPC];
    }
    
    //Method used to calculate the note at "interval" distance from the note n1
    public static Note noteAtDistance(Note n1, String interval) {
        String[][] intervals = {
            {"P1", "A1", "2A1", "", "", "", "", "", "", "", "2d1", "d1"},
            {"d2", "m2", "M2", "A2", "2A2", "", "", "", "", "", "", ""},
            {"", "", "d3", "m3", "M3", "A3", "2A3", "", "", "", "", ""},
            {"", "", "", "2d4", "d4", "P4", "A4", "2A4", "", "", "", ""},
            {"", "", "", "", "", "2d5", "d5", "P5", "A5", "2A5", "", ""},
            {"", "", "", "", "", "", "", "d6", "m6", "M6", "A6", "2A6"},
            {"A7", "2A7", "", "", "", "", "", "", "", "d7", "m7", "M7"},};
        
        int i = 0;
        int j = 0;
        boolean check = false;
        while(i < intervals.length && check == false) {
            j = 0;
            while(j < intervals[i].length && check == false) {
                if(intervals[i][j].equals(interval))
                    check = true;
                else
                    j++;
            }
            if(check == false)
                i++;       
        }
        
        if(check == false)
            throw new IllegalArgumentException("Intervallo inserito (" + interval + ") non valido");
        
        int pcNoteInterval = j;
        int ncNoteInterval = i;
        int nc_end = (n1.getNameClass() + ncNoteInterval) % 7;
        int pc_end = (n1.getPitchClass() + pcNoteInterval) % 12;
        
        String note = ConversionsFrom.conversionFromBinomial("<" + pc_end + "," + nc_end + ">");
       
        Note n = new Note(note.split("/")[0]);
        if(n.n != null) {
            int denExp = (int) (Math.log(n.getDenominator()) / Math.log(2));
            n1.setNoteRest(n.getNumerator(), denExp);
        }
        return n;
    }
    
    //Method used to calculate the trasposition between Note n and BinomialInterval trasposition name "binTrasp"
    public static Note binomialTrasposition(Note n, String binTrasp) {
        String nBin = n.getBinomial();
        int nPC = Integer.parseInt(nBin.substring(1, nBin.length()-1).split(",")[0]);
        int nNC = Integer.parseInt(nBin.substring(1, nBin.length()-1).split(",")[1]);
        int binTraspPC = Integer.parseInt(binTrasp.substring(1, binTrasp.length()-1).split(",")[0]);
        int binTraspNC = Integer.parseInt(binTrasp.substring(1, binTrasp.length()-1).split(",")[1]);
        
        int traspPC = (binTraspPC + nPC) % 12;
        int traspNC = (binTraspNC + nNC) % 7;
        Note n1 = new Note("<" + traspPC + "," + traspNC + ">", "binomial");
        if(n.n != null) {
            int denExp = (int) (Math.log(n.getDenominator()) / Math.log(2));
            n1.setNoteRest(n.getNumerator(), denExp);
        }
        return n1;
    }
    
    //Method used to calculate binomial inversion
    public static Note binomialInversion(Note n) {
        String nBin = n.getBinomial();
        int nPC = Integer.parseInt(nBin.substring(1, nBin.length()-1).split(",")[0]);
        int nNC = Integer.parseInt(nBin.substring(1, nBin.length()-1).split(",")[1]);
        int invertedPC = (12 - nPC) % 12;
        int invertedNC = (7 - nNC) % 7;
        Note n1 = new Note("<" + invertedPC + "," + invertedNC + ">", "binomial");
        if(n.n != null) {
            int denExp = (int) (Math.log(n.getDenominator()) / Math.log(2));
            n1.setNoteRest(n.getNumerator(), denExp);
        }
        return n1;
    }
    
    //Method used to find all homophone of a notes
    public static String getHomophony(Note n) {
        String[][] notes = {
            {"c","c#","cx","","","","","","","","cbb","cb"},
            {"dbb", "db", "d", "d#", "dx", "", "", "", "", "", "", ""},
            {"", "", "ebb", "eb", "e", "e#", "ex", "", "", "", "", ""},
            {"", "", "", "fbb", "fb", "f", "f#", "fx", "", "", "", ""},
            {"", "", "", "", "", "gbb", "gb", "g", "g#", "gx", "", ""},
            {"", "", "", "", "", "", "", "abb", "ab", "a", "a#", "ax"},
            {"b#", "bx", "", "", "", "", "", "", "", "bbb", "bb", "b"}};
        String omophony = "";
        int pc = n.getPitchClass();
        for(int i = 0; i < notes.length; i++) {
            omophony += notes[i][pc];
            if(!"".equals(notes[i][pc]) && i + 1 < notes.length)
                omophony += ",";
        }
        
        return omophony;
    }
    
    public static Note cbrDiatonicTrasposition(Note n, String cbrTrasp) {
        int cbr;
        try {
            cbr = Integer.parseInt(cbrTrasp);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valore cbr errato (" + cbrTrasp + ")");
        }
        
        int[] halftoneOffset = {0, 2, 4, 5, 7, 9, 11};
        // calcolo dell'ottava
        int newOctave = n.getOctave() + cbr / 7;
        // calcolo della trasposizione a meno delle ottave
        int currentAmount = cbr - 7 * (cbr / 7);
        // calcolo dell'nc
        int currentNc = n.getNameClass();
        // calcolo del nuovo NC a meno delle ottave
        int newNc = currentNc + currentAmount;
        // oltre i limiti 0..7: ricalcolo NC e modifica nuova ottava
        if (newNc > 6) {
            newNc -= 7;
            newOctave++;
        } else if (newNc < 0) {
            newNc += 7;
            newOctave--;
        }
        // calcolo del pc
        int currentPc = n.getPitchClass();
        // spiazzamento rispetto alla nota naturale originale + spiazzamento nuova nota naturale
        int newPc = currentPc - halftoneOffset[currentNc] + halftoneOffset[newNc];
        // oltre i limiti 0..11: ricalcolo PC
        if (newPc > 11)
            newPc -= 12;
        else if (newPc < 0)
            newPc += 12;
        // ricostruzione del valore cbr
        Note n1 = new Note("" + (newOctave * 1000 + newPc * 10 + newNc), "cbr");
        if(n.n != null) {
            int denExp = (int) (Math.log(n.getDenominator()) / Math.log(2));
            n1.setNoteRest(n.getNumerator(), denExp);
        }
        return n1;
    }
        
}
