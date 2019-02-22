package progetto;

//Class that manage possible Note output formats
public class ConversionsTo {
     
    //Conversions to NeoLatin Notation
    public static String conversionToNeolatin(Note n) {
        String p = n.getNote();
        switch (p.charAt(0)) {
            case 'c': return p.replaceAll("c", "Do") + n.getOctave();
            case 'd': return p.replaceAll("d", "Re") + n.getOctave();
            case 'e': return p.replaceAll("e", "Mi") + n.getOctave();
            case 'f': return p.replaceAll("f", "Fa") + n.getOctave();
            case 'g': return p.replaceAll("g", "Sol") + n.getOctave();
            case 'a': return p.replaceAll("a", "La") + n.getOctave();
            case 'b': return p.replaceAll("b", "Si") + n.getOctave();
            default: return null;
        }
    }
    
    //Conversion to Frequency
    public static float conversionToFrequency(Note n) {        
        return 440.0f * (float)Math.pow(2.0f, (convertsionToMIDI(n) - 69f) / 12.0f);
    }
    
    //Conversion to ScientificNotation
    public static String conversionToScientificNotation(Note n){
        return n.getNote() + n.getOctave();
    }
    
    //Conversion to Midi
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
        return 12 * n.getOctave() + 12 + c;
    }
    
    //Conversion to Helmholtz notation
    public static String conversionToHelm(Note n) {
        String p = n.getNote();
        
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
    
    //Conversion to PitchClass notation
    public static short conversionToPitchClass(Note n) {
        String[] notes = {"c/b#/dbb", "c#/db/bx/b##", "d/cx/c##/ebb", "d#/eb/fbb", "e/dx/d##/fb",
            "f/e#/gbb", "f#/gb/ex/e##", "g/fx/f##/abb", "g#/ab", "a/gx/g##/bbb", "a#/bb/cbb", "b/ax/a##/cb"};
        
        short i = 0;
        boolean check = false;
        
        //The index of array corresponds to PC value
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
     
    //Conversion to ContinuousPitchClass
     public static int conversionToCPC(Note n) {
         short pc = conversionToPitchClass(n);
         int cpc = n.getOctave() * 12 + pc;
         return cpc;
     }
     
     //Conversion to NameClass
     public static short conversionToNC(Note n) {
        //No octave info
        char[] notes = {'c', 'd', 'e', 'f', 'g', 'a', 'b'};
        short i = 0;
        //Looking for Note first char index
        while(i < notes.length && n.getNote().charAt(0) != notes[i])
            i++;
        return i;
     }
     
     //Conversion to ContinuousNameClass
     public static short converstionToCNC(Note n) {
         short cnc = (short) ((n.getOctave() * 7) + n.getNameClass());
         return cnc;
     }
     
     //Conversion to Binomial (<PC,NC>)
     public static String conversionToBinomial(Note n) {
         return "<" + n.getPitchClass() + "," + n.getNameClass() + ">";
     }
     
     //Conversion to IntegerBinomialRappresentation
     public static short conversionToBR(Note n) {
         return (short) ((n.getPitchClass() * 10) + n.getNameClass());
     }
     
     ////Conversion to ContinuosBinomialRappresentation
     public static int conversionToCBR(Note n) {
         return n.getOctave() * 1000 + n.getBR();
     }
}
