package progetto;

public class Progetto {


    public static void main(String[] args) {
        // TODO code application logic here
        Melody m = new Melody(); 
        Note n1 = new Note("G", 1);
        Note n2 = new Note("D", 4);
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
        
        Note n6 = new Note("E,,", "helmholtz");
        m.add(n6);
        System.out.println("ConversioneInInput: " + m.getNoteAt(5).getNote() + "" + m.getNoteAt(5).getOctave());
        
        Note n7 = new Note("739,99", "frequency");
        m.add(n7);
        System.out.println("ConversioneInInput: " + m.getNoteAt(6).getNote() + "" + m.getNoteAt(6).getOctave());
        
        System.out.println("La nota più alta della melodia è: " + m.getHighestNote().getScientificNotation());
        System.out.println("La nota più alta della melodia è: " + m.getLowestNote().getScientificNotation());
        System.out.println("Pitch Class: " + n7.getPitchClass());
        System.out.println("PCI: " + Manipulations.pitchClassInterval(n1, n2) + " --> PCI':" 
                + Manipulations.pitchClassIntervalName(Manipulations.pitchClassInterval(n1, n2)));
        System.out.println("PCI: " + Manipulations.pitchClassIntervalInversion(n1, n2) + " --> PCI':" 
                + Manipulations.pitchClassIntervalName(Manipulations.pitchClassIntervalInversion(n1, n2)));
        System.out.println("PCI: " + Manipulations.pitchClassInterval(n1, n2) + " --> CI:" 
                + Manipulations.intervalClass(n1, n2) + " --> " 
                + Manipulations.pitchClassIntervalName(Manipulations.intervalClass(n1, n2)));
        
        Note n8 = new Note("11", "Pc");
        System.out.println("PCI 10 --> " + n8.getNote() + n8.getOctave());
        m.melodyPCITrasposition((short) (1));
        for(int i = 0; i < m.getLenght(); i++) {
            System.out.println("Melodia trasposta: " + m.getNoteAt(i).getNote() + "" + m.getNoteAt(i).getOctave());
        }
        Note n9 = new Note("48", "cpc");
        System.out.println("CPC c4 --> " + n9.getContinuousPitchCode() + " --> " + n9.getNote() + "" + n9.getOctave());
        
        m.melodyCPCITrasposition(-1);
        for(int i = 0; i < m.getLenght(); i++) {
            System.out.println("Melodia trasposta: " + m.getNoteAt(i).getNote() + "" + m.getNoteAt(i).getOctave());
        }
        
        Note n10 = new Note("c", 4);
        Note n11 = new Note("a", 3);
        System.out.println("CPCI tra c4 e a3: " + Manipulations.continuousPitchClassInterval(n10, n11));
        
        Note n12 = new Note("63", "cpc");
        Note n13 = new Note("38", "cpc");
        System.out.println("CPCI tra 63 e 38: " + Manipulations.continuousPitchClassInterval(n12, n13));
        
        Note n14 = new Note("5", "nc");
        System.out.println("5 NC --> " + n14.getNote() + "" +n14.getOctave());
        System.out.println(n12.getNote() + "" + n12.getOctave() + " NC --> " + n12.getNameClass());
    }
    
    
}
