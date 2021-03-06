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
            System.out.println("Helmholtz:" + m.getNoteAt(i).getHelmholtz());
            System.out.println("Scientifica: " + m.getNoteAt(i).getScientificNotation());
            System.out.println("Neolatina: " + m.getNoteAt(i).getNeolatinNotation());
            System.out.println("MIDI: " + m.getNoteAt(i).getMIDIPitch());
            System.out.println("Frequenza: " + m.getNoteAt(i).getFreq());
            System.out.println("PC: " + m.getNoteAt(i).getPitchClass());
            System.out.println("Binomial: " + m.getNoteAt(i).getBinomial());
            System.out.println("BR: " + m.getNoteAt(i).getBR());
            System.out.println("CBR: " + m.getNoteAt(i).getCBR());
            System.out.println("CNC: " + m.getNoteAt(i).getContinuousNameCode());
            System.out.println("CPC: " + m.getNoteAt(i).getContinuousPitchCode());
            System.out.println("NC: " + m.getNoteAt(i).getNameClass());

            
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
        System.out.println("PCI " + n8.getContinuousPitchCode() + " --> " + n8.getNote() + n8.getOctave());
        m.melodyPCITrasposition((short) (1));
        for(int i = 0; i < m.getLenght(); i++) {
            System.out.println("Melodia trasposta: " + m.getNoteAt(i).getNote() + "" + m.getNoteAt(i).getOctave());
        }
        Note n9 = new Note("48", "cpc");
        System.out.println("CPC" + n9.getNote() + " --> " + n9.getContinuousPitchCode() + " --> " 
                + n9.getNote() + "" + n9.getOctave());
        
        m.melodyCPCITrasposition(-1);
        for(int i = 0; i < m.getLenght(); i++) {
            System.out.println("Melodia trasposta con CPCI: " + m.getNoteAt(i).getNote() + "" + m.getNoteAt(i).getOctave());
        }
        
        Note n10 = new Note("c", 4);
        Note n11 = new Note("a", 3);
        System.out.println("CPCI tra c4 e a3: " + Manipulations.continuousPitchClassInterval(n10, n11));
        
        Note n12 = new Note("63", "cpc");
        Note n13 = new Note("38", "cpc");
        System.out.println("CPCI tra 63 e 38: " + Manipulations.continuousPitchClassInterval(n12, n13));
        
        Note n14 = new Note("0", "nc");
        Note n15 = new Note("5", "nc");
        //System.out.println("5 NC --> " + n14.getNote() + "" +n14.getOctave());
        System.out.println(n12.getNote() + "" + n12.getOctave() + " NC --> " + n12.getNameClass());
        
        System.out.println(n14.getNote() + "," +n15.getNote() + " interval " + Manipulations.ncInterval(n14, n15) + " --> " 
                +Manipulations.ncIntervalName(Manipulations.ncInterval(n14, n15)) + ", NIC: " 
                + Manipulations.nameIntervalClass(n14, n15));

        
        System.out.println(n14.getNote() + "," +n13.getNote() + " interval inversion " 
                + Manipulations.nameClassIntervalInversion(n14, n13));
        
        m.melodyNCITrasposition((short) (3));
        for(int i = 0; i < m.getLenght(); i++) {
            System.out.println("Melodia trasposta con NCI: " + m.getNoteAt(i).getNote() + "" + m.getNoteAt(i).getOctave());
        }
        System.out.println("CNC di " + n15.getNote() + "" + n15.getOctave() + " --> " + n15.getContinuousNameCode());
        
        Note n16 = new Note("28", "cnc");
        System.out.println("CNC --> " + n16.getNote() + "" + n16.getOctave());
        
        Note n17 = new Note("<10,0>", "binomial");
        Note n18 = new Note("22", "br");
        System.out.println("10 BR --> " + n18.getNote() + "" + n18.getOctave());
        System.out.println(n11.getNote() + " --> " + n11.getBR() + " BR");
        
        Note n19 = new Note("1115", "cbr");
        System.out.println("1115 --> " + n19.getNote() + "" + n19.getOctave());
        
        Note n20 = new Note("-");
        //m.add(n20);
        n20.setNoteRest("croma", "italian");
        System.out.println("Durata pausa: " + n20.getNoteRestToString());
        n19.setNoteRest(4);
        System.out.println(n19.getNote() + " " + n19.getNoteRestToString());
        
        System.out.println("Nella melodia sono presenti pause: " + m.countRest() + " e note:" + m.countNotes());
        System.out.println(n15.getNoteRestToString());
        
        m.setBpm(148);
        n1.setNoteRest(1);
        n2.setNoteRest(1, 1);
        n3.setNoteRest("half", "american");
        System.out.println("Durata melodia: " + m.melodyTime() + " secondi");
        
        System.out.println("La nota " + n3.getNote() + " da " + n3.getNoteRestToString() + " con 115 bpm dura " 
                + Manipulations.noteTime(115, n3) + " secondi");
        
        System.out.println("//////////////////////////////////////////////////");
        Melody m2 = new Melody();
        Note nn1 = new Note(61);
        Note nn5 = new Note(62);
        Note nn2 = new Note("b#'", "helmholtz");
        Note nn3 = new Note("7", "pc");
        Note nn4 = new Note("4", "nc");
        m2.add(nn1);
        m2.add(nn2);
        m2.add(nn3);
        m2.add(nn4);
        nn1.setNoteRest("croma", "italian");
        System.out.println(m2.toString());
        System.out.println("//////////////////////////////////////////////////");
        System.out.println("Interval from Binomial Note: " + Manipulations.binomialInterval(nn2, nn3));
        System.out.println("Nota iniziale: " + nn1.toString() + ", Intervallo: " + "d4" + " --> " + Manipulations.noteAtDistance(nn1, "d4").toString());
        System.out.println("Gli omofoni di " + nn1.getNote() + " sono: " + Manipulations.getHomophony(nn1));
        System.out.println("La trasposizione di " + nn5.getNote() + " di <4,2> è: " 
                + Manipulations.binomialTrasposition(nn5, "<4,2>"));
        System.out.println("L'inversione di " + nn5.getNote() + " è " + Manipulations.binomialInversion(nn5));
        System.out.println("L'inversione dell' intervallo P4 è " +
                " --> " + Manipulations.intervalInversion("p4"));
        System.out.println("HighestNote:" + m2.getHighestNote() + ", LowestNote:" 
                + m2.getLowestNote() + ", MiddleNote" + m2.getMiddleNote());
        System.out.println("//////////////////////////////////////////////////");
        System.out.println("Trasposizione binomiale della melodia " + m2.toString());
        System.out.println("di <1,1>");
        m2.melodyBinomialTrasposition("<1,1>");

        System.out.println(m2.toString());
        
        System.out.println("//////////////////////////////////////////////////");
        Melody m3 = Melody.randomMelody(5);
        System.out.println("Random melody: \n" + m3.toString());
        
        System.out.println("//////////////////////////////////////////////////");
        m2.melodyCBRDiatonicTrasposition("4");
        System.out.println("Melody m2 cbr trasposition:\n" + m2.toString());
    }
}
