package progetto;

public class ConversionsFrom {
    public static String conversionFromNeolatin(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        String alteration = "";
        int octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        
        if(pitch.length() > 2) {
            for (int i = 2; i < pitch.length(); i++) {
                alteration += pitch.charAt(i);
            }
            pitch = pitch.substring(0, 2);
        }
        switch (pitch) {
            case "do": return "c" + alteration + "/" + octave;
            case "re": return "d" + alteration + "/" + octave;
            case "mi": return "e" + alteration + "/" + octave;
            case "fa": return "f" + alteration + "/" + octave;
            case "sol": return "g" + alteration + "/" + octave;
            case "la": return "a" + alteration + "/" + octave;
            case "si": return "b" + alteration + "/" + octave;
        }
        return "";
    }
    
    public static String conversionFromAnglo(String note) {
        String pitch = note.split("(?<=\\D)(?=\\d)")[0];
        int octave = Integer.parseInt(note.split("(?<=\\D)(?=\\d)")[1]);
        return pitch + "/" + octave;
    }
    
    public static String conversionFromMidiPitch(int midiPitch) {
        String pitchOctave = ConversionsTo.conversionMidiToPitchOctave(midiPitch);
        String[] parts = pitchOctave.split("/");
        return parts[0] + "/" + parts[1];
    }
}
