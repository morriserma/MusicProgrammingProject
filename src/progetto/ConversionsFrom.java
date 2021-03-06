package progetto;

//Class that manage possible Note input formats
public class ConversionsFrom {
    //Conversion from NeoLatin rappresentation
    public static String conversionFromNeolatin(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        String alteration = "";
        int octave = 0;
        
        try {
            octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        } catch (NumberFormatException e) {
            //System.out.println("Numero dell'ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
            throw new IllegalArgumentException("Valore ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
        }
        
        if(pitch.length() > 2) {
            for (int i = 2; i < pitch.length(); i++) {
                alteration += pitch.charAt(i);
            }
            pitch = pitch.substring(0, 2);
        }
        switch (pitch) {
            case "do": return "c" + alteration + "/" + octave;
            case "re": return "d" + alteration + "/" + octave;
            case "mi": return "e" + alteration + "/" + octave;
            case "fa": return "f" + alteration + "/" + octave;
            case "sol": return "g" + alteration + "/" + octave;
            case "la": return "a" + alteration + "/" + octave;
            case "si": return "b" + alteration + "/" + octave;
            default: return "";
        }
    }
    
    //Conversion from English rappresentation
    public static String conversionFromAnglo(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        int octave = 0;
        try {
            octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        } catch (NumberFormatException e) {
            //System.out.println("Numero dell'ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
            throw new IllegalArgumentException("Valore ottava errato (" + note.split("(?<=\\D)(?=\\d)")[1] + ")");
        }
        return pitch + "/" + octave;
    }
    
    //Conversion from Midi rappresentation
    public static String conversionFromMidiPitch(int midiPitch) {
        String[] notes = {"c", "c#", "d", "eb", "e", "f", "f#", "g", "g#", "a", "bb", "b"};
        if(midiPitch >= 0 && midiPitch <= 127) {
            String pitch = notes[midiPitch % 12];
            int octave = midiPitch / 12 - 1;
            String pitchOctave = pitch + "/" + octave;
            String[] parts = pitchOctave.split("/");
            return parts[0] + "/" + parts[1];
        }
        else
            throw new IllegalArgumentException("Valore Midi (" + midiPitch + ") non valido");
    }
    
    //Conversion from Helmholtz rappresentation
    public static String conversionFromHelmholtz(String note) {
        String pitch;
        int octave;
        
        if(Character.isUpperCase(note.charAt(0))) {
            int i = 0;
            for(int j = 0; j < note.length(); j++) {
                if(note.charAt(j) == ',')
                    i++;
            }

            pitch = note.substring(0, note.length() - i);
            if(i == 0)
                octave = 3;
            else
                octave = 3 - i; 
        }
        else {
            int i = 0;
            for(int j = 0; j < note.length(); j++) {
                if(note.charAt(j) == '\'')
                    i++;
            }
            
            pitch = note.substring(0, note.length() - i);
            if(i == 0)
                octave = 4;
            else
                octave = 4 + i; 
        }
        
        if(checkNoteValidity(pitch, octave))
            return pitch + "/" + octave;
        else
            throw new IllegalArgumentException("Valore helmholtz (" + note + ") errato");
    }
    
    //Conversion from Frequency rappresentation
    public static String conversionFromFrequency(String nota) {   
        int midiPitch;
        double freq = 0;
        try {
            freq = Double.parseDouble(nota.replace(",","."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valore della frequenza errato (" + nota.replace(",",".") + ")");
        }
        if(freq > 16.351597 && freq < 31608.527)
            midiPitch = (int) (69 + 12*(Math.log(freq / 440)) / Math.log(2));
        else
             throw new IllegalArgumentException("Valore in Hz della nota inserita "
                     + "(" + nota + ") esce al range gestito (16.351597 - 31608.527)");
        
        return  conversionFromMidiPitch(midiPitch);
    }
    
    //Method to check the Note validity. Check if the pitch is correct (max 2 alterations)
    //and if the octave is in range 0-10
    public static boolean checkNoteValidity(String pitch, int octave) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        String[] parts;
        String p = pitch.toLowerCase();
        boolean b = false;
   
        if (octave >= -1 && octave <=10) {
            int i = 0;
            int j = 0;
            while (i < notes.length && b == false) {
                j = 0;
                parts = notes[i].split("/");
                while(j < parts.length && b == false) {
                    if (parts[j].equals(p))
                        b = true;
                    j++;
                }

                i++;
            }
        }
        else
            b = false;
        return b;
    }
    
    //Conversion from PitchClass rappresentation
    public static String conversionFromPC(String nota) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        String pitch = "";
        int index = 0;
        try {
            index = Integer.parseInt(nota);
            
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore Pitch Class errato (" + nota + ")");
        }
        if(index >= 0 && index <= 11)
            pitch = notes[index].split("/")[0];
        else
            throw new IllegalArgumentException("Indice non valido");
        return pitch + "/" + 4;
    }
    
    //Conversion from ContinuousPitchClass rappresentation
    public static String conversionFromCPC(String nota) {
        int octave = 0;
        short pc = 0;
        try {
            octave = Integer.parseInt(nota) / 12;
            pc = (short) (Integer.parseInt(nota) % 12);
        } catch (NumberFormatException e) {
            //System.out.println("Valore Continuous Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore Continuous Pitch Class errato (" + nota + ")");
        }
        
        return conversionFromPC("" + pc).split("/")[0] + "/" + octave;
    }
    
    //Conversion from NameClass rappresentation
    public static String conversionFromNC(String nota) {
        char[] notes = {'c', 'd', 'e', 'f', 'g', 'a', 'b'};
        
        char pitch;
        int index = 0;
        try {
            index = Integer.parseInt(nota);
            
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore Pitch Class errato (" + nota + ")");
        }
        if(index >= 0 && index <= 6)
            pitch = notes[index];
        else
            throw new IllegalArgumentException("Indice " + index + " non valido");
        return pitch + "/" + 4;
    }
    
    //Conversion from ContinuousNameClass rappresentation
    public static String conversionFromCNC(String nota) {
        int notaInt;
        try {
           notaInt = Integer.parseInt(nota);            
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore CNC errato (" + nota + ")");
        }
        int octave = notaInt / 7;
        int nc = notaInt % 7;
        
        return conversionFromNC("" + nc).split("/")[0] + "/" + octave;
    }
    
    //Conversion from Binomial rappresentation
    public static String conversionFromBinomial(String nota) {
        String[][] notes = {
            {"c","c#","cx","","","","","","","","cbb","cb"},
            {"dbb", "db", "d", "d#", "dx", "", "", "", "", "", "", ""},
            {"", "", "ebb", "eb", "e", "e#", "ex", "", "", "", "", ""},
            {"", "", "", "fbb", "fb", "f", "f#", "fx", "", "", "", ""},
            {"", "", "", "", "", "gbb", "gb", "g", "g#", "gx", "", ""},
            {"", "", "", "", "", "", "", "abb", "ab", "a", "a#", "ax"},
            {"b#", "bx", "", "", "", "", "", "", "", "bbb", "bb", "b"}};

        
        String pc = nota.split(",")[0].substring(1, nota.split(",")[0].length());
        String nc = nota.split(",")[1].substring(0, nota.split(",")[1].length() - 1);
        
        int pcInt;
        int ncInt;
        try {
           pcInt = Integer.parseInt(pc);    
           ncInt = Integer.parseInt(nc);  
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore pc o nc errato (" + nota + ")");
        }
        if(pcInt >=0 && pcInt <= 11 && ncInt >= 0 && ncInt <= 6 && !notes[ncInt][pcInt].equals(""))
            return notes[ncInt][pcInt] + "/" + 4;
        else
            throw new IllegalArgumentException("Valore pc o nc errato (" + nota + "). Sono supportate al massimo le doppie alterazioni");
    }
    
    //Conversion from IntegerBinomialRappresentation rappresentation
    public static String conversionFromBR(String nota) {
        int br;
        try {
            br = Integer.parseInt(nota);
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore BR errato (" + nota + ")");
        }
        if(br <= 116 && br >= 0) {
            int pc = br / 10;
            int nc = br % 10;
            if(pc >=0 && pc <= 11 && nc >= 0 && nc <= 6)
                return conversionFromBinomial("<" + pc + "," + nc + ">");
            else
                throw new IllegalArgumentException("Valore BR errato (" + nota + ")");
        }
        else
            throw new IllegalArgumentException("Valore BR errato (" + nota + ")");
    }
    
    //Conversion from ContinuousIntegerBinomialRappresentation rappresentation
    public static String conversionFromCBR(String nota) {
        int noteInt;
        try {
            noteInt = Integer.parseInt(nota);
        } catch (NumberFormatException e) {
            //System.out.println("Valore Pitch Class errato (" + nota + ")");
            throw new IllegalArgumentException("Valore CBR errato (" + nota + ")");
        }
        
        int octave = noteInt / 1000;
        int br = noteInt % 1000;
        int pc = br / 10;
        int nc = br % 10;
        String fromBin;
        if(pc >= 0 && pc <=11 && nc >= 0 && nc <= 6) {
            fromBin = conversionFromBinomial("<" + pc + "," + nc + ">");
            return fromBin.replaceAll("4", octave + "");
        }
        else
            throw new IllegalArgumentException("Valore PC o NC errato (" + pc + " o " + nc + ")");
        
        
    }
}
