package progetto;

public class ConversionsFrom {
    public static String conversionFromNeolatin(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        String alteration = "";
        int octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        
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
        }
        return "";
    }
    
    public static String conversionFromAnglo(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        int octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        return pitch + "/" + octave;
    }
    
    public static String conversionFromMidiPitch(int midiPitch) {
        String pitchOctave = ConversionsTo.conversionMidiToPitchOctave(midiPitch);
        String[] parts = pitchOctave.split("/");
        return parts[0] + "/" + parts[1];
    }
    
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
            throw new IllegalArgumentException();
    }
    
    public static String conversionFromFrequency(String nota) {   
        int midiPitch;
        double freq = Double.parseDouble(nota.replace(",","."));
        if(freq > 16.351597 && freq < 31608.527)
            midiPitch = (int) (69 + 12*(Math.log(freq / 440)) / Math.log(2));
        else
             throw new IllegalArgumentException();
        
        return  conversionFromMidiPitch(midiPitch);
    }
    
    public static boolean checkNoteValidity(String pitch, int octave) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        String[] parts;
        String p = pitch.toLowerCase();
        boolean b = false;
   
        if (octave >= 0 && octave <=10) {
            int i = 0;
            while (b == false && i < notes.length) {
                if (notes[i].length() > 1) {
                    parts = notes[i].split("/");
                    if (parts[0].equals(p) || parts[1].equals(p))
                        b = true;
                }
                else {
                    if (notes[i].equals(p))
                        b = true;
                    else
                        b = false;
                }
                i++;
            }
        }
        else
            b = false;
        return b;
    }
}