package progetto;

enum BritishNoteValue {

    SEMIBREVE, MINIM, CROTCHET, QUAVER, SEMIQUAVER, DEMISEMIQUAVER, HEMIDEMISEMIQUAVER, SEMIHEMIDEMISEMIQUAVER, DEMISEMIHEMIDEMISEMIQUAVER
}

enum AmericanNoteValue {

    WHOLE, HALF, QUARTER, EIGHTH, SIXTEENTH, THIRTYSECOND, SIXTYFOURTH, HUNDREDTWENTYEIGHTH, TWOHUNDREDFIFTYSIXTH
}

enum ItalianNoteValue {
    SEMIBREVE, MINIMA, SEMIMINIMA, CROMA, SEMICROMA, BISCROMA, SEMIBISCROMA, FUSA, SEMIFUSA
}

// ------------------------------------------------------------------------
class NoteRest {

    protected int numerator;
    protected int denominator;

    public NoteRest() {
        numerator = 1;
        denominator = 1;
    }

    public NoteRest(int n, int expd) {
        numerator = Math.abs(n);
        expd = Math.max(0, expd);
        expd = Math.min(expd, 8);
        denominator = (int) (Math.pow(2, expd)); // pow returns a double
    }

    public NoteRest(int expd) {
        numerator = 1;
        expd = Math.max(0, expd);
        expd = Math.min(expd, 8);
        denominator = (int) (Math.pow(2, expd)); // pow returns a double
    }

    public NoteRest(String value, String notationLanguage) {
        numerator = 1;
        switch(notationLanguage.toLowerCase()){
            case "british":  
                try{
                    denominator = (int) (Math.pow(2, BritishNoteValue.valueOf(value.toUpperCase()).ordinal()));
                    break;
                }
                catch(Exception e){
                    throw new IllegalArgumentException("Valore (" + value + ") non valido");
                }
                    
            case "italian": 
                try{
                    denominator = (int) (Math.pow(2, ItalianNoteValue.valueOf(value.toUpperCase()).ordinal()));
                    break;
                }
                catch(Exception e){
                    throw new IllegalArgumentException("Valore (" + value + ") non valido");
                }
                
            case "american": 
                try{
                    denominator = (int) (Math.pow(2, AmericanNoteValue.valueOf(value.toUpperCase()).ordinal()));
                    break;
                }
                catch(Exception e){
                    throw new IllegalArgumentException("Valore (" + value + ") non valido");
                }  
                
            default: throw new IllegalArgumentException("Notazione (" + notationLanguage + ") non gestita");
        }
    }


    @Override
    public String toString() {
        return ("- [" + numerator + "/" + denominator + "]");
    }

    public void halveValue() {
        denominator *= 2;
    }

    public void doubleValue() {
        if (denominator > 1)
            denominator /= 2;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public double getNumericDuration() {
        double e = (double) numerator / denominator;
        return (double) numerator / denominator;
    }
}