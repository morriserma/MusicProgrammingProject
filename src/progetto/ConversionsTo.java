package progetto;

public class ConversionsTo {
     
    public static String conversionToNeolatin(Note n) {
        String p = n.getNote();
        /*if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
                && p.charAt(0) != 'b') {
            
            System.out.println("Nome della nota errato (" + p + "). La nota deve essere espressa in notazione anglosassone");
            System.exit(1);
        }*/
        switch (p.charAt(0)) {
            case 'c': return p.replaceAll("c", "Do") + n.getOctave();
            case 'd': return p.replaceAll("d", "Re") + n.getOctave();
            case 'e': return p.replaceAll("e", "Mi") + n.getOctave();
            case 'f': return p.replaceAll("f", "Fa") + n.getOctave();
            case 'g': return p.replaceAll("g", "Sol") + n.getOctave();
            case 'a': return p.replaceAll("a", "La") + n.getOctave();
            case 'b': return p.replaceAll("b", "Si") + n.getOctave();
        }
        return null;
    }
    
    public static float conversionToFrequency(Note n) {        
        return 440.0f * (float)Math.pow(2.0f, (convertsionToMIDI(n) - 69f) / 12.0f);
    }
    
    public static String conversionToScientificNotation(Note n){
        String p = n.getNote();
//        if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
//                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
//                && p.charAt(0) != 'b') {
//            
//            System.out.println("Nome della nota errato (" + p + "). La nota deve essere espressa in notazione anglosassone");
//            System.exit(1);
//        }
        return n.getNote() + n.getOctave();
    }
    
    public static int convertsionToMIDI(Note n) {
        String[] notes = {"c", "c#/db", "d", "d#/eb", "e", "f", "f#/gb", "g", "g#/ab", "a", "a#/bb", "b"};
        int c = 0;
        String[] parts;
        int i = 0;
        while (i < notes.length) {
            if (notes[i].length() > 1) {
                parts = notes[i].split("/");
                if (parts[0].equals(n.getNote()) || parts[1].equals(n.getNote()))
                    c = i;
            }
            else
                if (notes[i].equals(n.getNote()))
                    c = i;
            i++;
        }
        return 12*n.getOctave() + 12 + c;
    }
    
    public static String conversionMidiToPitchOctave(int midiPitch) {
        String[] notes = {"c", "c#", "d", "eb", "e", "f", "f#", "g", "g#", "a", "bb", "b"};
        String pitch = notes[midiPitch % 12];
        int octave = midiPitch / 12 - 1;
        return pitch + "/" + octave;
    }
    
    public static String conversionToHelm(Note n) {
        String p = n.getNote();
//        if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
//                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
//                && p.charAt(0) != 'b') {
//            System.out.println("Nome della nota errato (" + p 
//                    + "). La nota deve essere espressa in notazione anglosassone");
//            System.exit(1);
//        }
        
        String result = "";
        if (n.getOctave() >= 3) {
            result += p;
            for (int i = 3; i < n.getOctave(); i++)
                result += "'";
        } else {
            result += p.toUpperCase();
            for (int i = 1; i >= n.getOctave(); i--)
                result += ",";
        }
        return result;
    }
    
     public static short conversionToPitchClass(Note n) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        short i = 0;
        boolean check = false;
        while(i < notes.length && check == false) {
            String[] oneNote = notes[i].split("/");
            int j = 0;
            while(j < oneNote.length && check == false) {
                if(oneNote[j].equals(n.getNote()))
                    check = true;
                j++;
            }
            if(check == false)
                i++;
        }
        return i;
     }
     
     public static int conversionToCPC(Note n) {
         short pc = conversionToPitchClass(n);
         int cpc = n.getOctave() * 12 + pc;
         return cpc;
     }
     
     public static short conversionToNC(Note n) {
        char[] notes = {'c', 'd', 'e', 'f', 'g', 'a', 'b'};
        short i = 0;
        while(i < notes.length && n.getNote().charAt(0) != notes[i])
            i++;
        return i;
     }
     
     public static short converstionToCNC(Note n) {
         short cnc = (short) ((n.getOctave() * 7) + n.getNameClass());
         return cnc;
     }
}
