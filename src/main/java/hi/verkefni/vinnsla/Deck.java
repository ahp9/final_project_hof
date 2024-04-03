package hi.verkefni.vinnsla;

public class Deck {
    private final Card[] deck = new Card[52];
    private final boolean [] usedCard = new boolean[52];
    private int total=0;   // fjöldi spila sem búið er að draga úr stokknum

    /**
     * Upphafsstillir spilastokk með 52 spilum
     */
    public Deck() {
        int i = 0;
        for (Suit t : Suit.values()) {
            for (Value g : Value.values()) {
                deck[i++] = new Card(t, g);
            }
        }
    }

    /**
     * Dregur spil af handahófi. Gæti þess að sama spilið sé ekki dregið
     *
     * @return spil af handahófi ef eitthvert spil er eftir í stokknum annars null
     */
    public Card dragaSpil() {
        int nextCard;
        if (total == 52)
            return null;
        do {
            nextCard = (int) (Math.random() * 52);
        } while (usedCard[nextCard]);
        usedCard[nextCard] = true;
        total++;
        return deck[nextCard];
    }

}
