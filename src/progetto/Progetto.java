package progetto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Morris
 */
public class Progetto {


    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Note> melody = new ArrayList<Note>(); 
        Note n1 = new Note("Cbb", 4);
        Note n2 = new Note("d", 5);
        melody.add(n1);
        melody.add(n2);
        for (int i = 0; i < melody.size(); i++) {
            System.out.println("Nota:" + melody.get(i).getNote());
            System.out.println("Ottava:" + melody.get(i).getOctave());
            //System.out.println(melody.get(i).getHelmholtz());
            System.out.println("Scientifica: " + melody.get(i).getScientificNotation());
            System.out.println("Neolatina: " + melody.get(i).getNeolatinNotation());
            
            
            
        }
    }
    
}
