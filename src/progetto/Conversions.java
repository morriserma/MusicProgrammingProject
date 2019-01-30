package progetto;

/**
 *
 * @author Morris
 */
public class Conversions {
    
 
    public String conversionToNeolatin(String nota, int ottava) {
        String p = nota.toLowerCase();
        if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
                && p.charAt(0) != 'b') {
            
            System.out.println("Nome della nota errato (" + p + "). La nota deve essere espressa in notazione anglosassone");
            System.exit(1);
        }
        switch (p.charAt(0)) {
            case 'c': return p.replaceAll("c", "Do") + ottava;
            case 'd': return p.replaceAll("d", "Re") + ottava;
            case 'e': return p.replaceAll("e", "Mi") + ottava;
            case 'f': return p.replaceAll("f", "Fa") + ottava;
            case 'g': return p.replaceAll("g", "Sol") + ottava;
            case 'a': return p.replaceAll("a", "La") + ottava;
            case 'b': return p.replaceAll("b", "Si") + ottava;
        }
        return null;
    }
    
    public float conversionToFrequency(String nota, int ottava) {
        String[] notes = {"c", "c#/db", "d", "d#/eb", "e", "f", "f#/gb", "g", "g#/ab", "a", "a#/bb", "b"};
        String p = nota.toLowerCase();
        int n = 0;
        String[] parts;
        int i = 0;
        while (i < notes.length) {
            if (notes[i].length() > 1) {
                parts = notes[i].split("/");
                if (parts[0].equals(p) || parts[1].equals(p))
                    n = i;
            }
            else
                if (notes[i].equals(p))
                    n = i;
            i++;
        }
        int midi = 12*ottava + 12 + n;
        return 440.0f * (float)Math.pow(2.0f, (midi - 69f) / 12.0f);
    }
    
    public String conversionToScientificNotation(String nota, int ottava){
        String p = nota.toLowerCase();
        if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
                && p.charAt(0) != 'b') {
            
            System.out.println("Nome della nota errato (" + p + "). La nota deve essere espressa in notazione anglosassone");
            System.exit(1);
        }
        return nota + ottava;
    }
    
    public String conversionToHelm(String nota, int ottava) {
        String p = nota.toLowerCase();
        if (p.charAt(0) != 'c' && p.charAt(0) != 'd' && p.charAt(0) != 'e' 
                && p.charAt(0) != 'f' && p.charAt(0) != 'g' && p.charAt(0) != 'a'
                && p.charAt(0) != 'b') {
            System.out.println("Nome della nota errato (" + p 
                    + "). La nota deve essere espressa in notazione anglosassone");
            System.exit(1);
        }
        
        String result = "";
        if (ottava >= 3) {
            result += p;
            for (int i = 3; i < ottava; i++)
                result += "'";
        } else {
            result += p.toUpperCase();
            for (int i = 1; i >= ottava; i--)
                result += ",";
        }
        return result;
    }
}
