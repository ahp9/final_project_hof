package is.hi.hbv202g.blackJack;


public class Card {
    private final Suit suit;
    private final Value value;


    public Card(Suit s, Value v) {
        suit = s;
        value = v;
    }

    public Value getValue() {
        return value;
    }

    /**
     * Heiltölu gildi á bak við spilið
     * @return heiltölugildi spils
     */
    public int getNumber() {
        return value.getVALUE();
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        return value+" "+suit.name();
    }
}
