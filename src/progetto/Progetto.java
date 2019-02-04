package progetto;

public class Progetto {


    public static void main(String[] args) {
        // TODO code application logic here
        Melody m = new Melody(); 
        Note n1 = new Note("C#", 4);
        Note n2 = new Note("db", 8);
        m.add(n1);
        m.add(n2);

        for (int i = 0; i < m.getLenght(); i++) {
            System.out.println("Nota:" + m.getNoteAt(i).getNote());
            System.out.println("Ottava:" + m.getNoteAt(i).getOctave());
            System.out.println(m.getNoteAt(i).getHelmholtz());
            System.out.println("Scientifica: " + m.getNoteAt(i).getScientificNotation());
            System.out.println("Neolatina: " + m.getNoteAt(i).getNeolatinNotation());
            System.out.println("MIDI: " + m.getNoteAt(i).getMIDI());
            System.out.println("Frequenza: " + m.getNoteAt(i).getFreq());
            
            System.out.println("//////////////////////////////////////");
        }
    }
    
}
