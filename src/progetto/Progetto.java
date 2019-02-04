package progetto;

import java.util.ArrayList;

/**
 *
 * @author Morris
 */
public class Progetto {


    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Note> melody = new ArrayList<Note>(); 
        Note n1 = new Note("C#", 4);
        Note n2 = new Note("db", 8);
        melody.add(n1);
        melody.add(n2);
        for (int i = 0; i < melody.size(); i++) {
            System.out.println("Nota:" + melody.get(i).getNote());
            System.out.println("Ottava:" + melody.get(i).getOctave());
            System.out.println(melody.get(i).getHelmholtz());
            System.out.println("Scientifica: " + melody.get(i).getScientificNotation());
            System.out.println("Neolatina: " + melody.get(i).getNeolatinNotation());
            System.out.println("MIDI: " + melody.get(i).getMIDI());
            System.out.println("Frequenza: " + melody.get(i).getFreq());
            
            System.out.println("//////////////////////////////////////");
        }
    }
    
}
