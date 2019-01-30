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
            case 'c': return "do"+ottava;
            case 'd': return "re"+ottava;
            case 'e': return "mi"+ottava;
            case 'f': return "fa"+ottava;
            case 'g': return "sol"+ottava;
            case 'a': return "la"+ottava;
            case 'b': return "si"+ottava;
        }
        return null;
    }
    
    public float conversionToFrequency(String nota, int ottava) {
        String[] notes = {"c", "c#/db", "d", "d#/eb", "e", "f", "f#/gb", "g", "g#/ab", "a", "a#/bb", "b"};
        String p = nota.toLowerCase();
        int n = 0;
        String[] parts;
        for (int i = 0; i < notes.length; i++) {
            if (notes[i].length() > 1) {
                parts = notes[i].split("/");
                if (parts[0].equals(p) || parts[1].equals(p))
                    n = i;
            }
            else
                if (notes[i].equals(p))
                    n = i;
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
        /*if (args.length != 2) {
            System.out.println("Numero di argomenti errato, uso: Helmholtz nota ottava");
            System.exit(1);
        }*/
        String p = nota.toLowerCase();
        if (!p.equals("c") && !p.equals("d") && !p.equals("e") && !p.equals("f")
                && !p.equals("g") && !p.equals("a") && !p.equals("b")) {
            System.out.println("Nome della nota errato (" + p 
                    + "). La nota deve essere espressa in notazione anglosassone");
            System.exit(1);
        }
        /*int octave = 0;
        try {
            octave = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Numero dell'ottava errato (" + p + ")");
            System.exit(1);
        }*/
        String result = nota + ottava;
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
