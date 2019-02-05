package progetto;

public class Progetto {


    public static void main(String[] args) {
        // TODO code application logic here
        Melody m = new Melody(); 
        Note n1 = new Note("C#", 4);
        Note n2 = new Note("db", 8);
        Note n3 = new Note(103);
        m.add(n1);
        m.add(n2);
        m.add(n3);

        for (int i = 0; i < m.getLenght(); i++) {
            System.out.println("Nota:" + m.getNoteAt(i).getNote());
            System.out.println("Ottava:" + m.getNoteAt(i).getOctave());
            System.out.println(m.getNoteAt(i).getHelmholtz());
            System.out.println("Scientifica: " + m.getNoteAt(i).getScientificNotation());
            System.out.println("Neolatina: " + m.getNoteAt(i).getNeolatinNotation());
            System.out.println("MIDI: " + m.getNoteAt(i).getMIDIPitch());
            System.out.println("Frequenza: " + m.getNoteAt(i).getFreq());
            
            System.out.println("//////////////////////////////////////");
        }
        
        Note n4 = new Note("Dox4", "neolAtina");
        m.add(n4);
        System.out.println("ConversioneInInput: " + m.getNoteAt(3).getNote() + "" + m.getNoteAt(3).getOctave());
        Note n5 = new Note("Cx4", "AnGlosassone");
        m.add(n5);
        System.out.println("ConversioneInInput: " + m.getNoteAt(4).getNote() + "" + m.getNoteAt(4).getOctave());
    }
    
}
