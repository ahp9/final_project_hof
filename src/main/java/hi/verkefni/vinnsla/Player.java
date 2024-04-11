package hi.verkefni.vinnsla;

public class Player extends Observable implements PlayerInterface {
    private int scoreOnHand;
    private int cardsOnHand;

    public Player() {
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    /**
     * Leikmaður má hafa mest max fjölda spila á hendi.
     * Gefur leikmanni spil s ef max er ekki náð
     * Uppfærir samtölu spila
     * 
     * @param s spilið sem á að gefa leikmanni
     */
    public void drawCard(Card s) {
        if (cardsOnHand < 6) {
            scoreOnHand += s.getNumber();
            cardsOnHand += 1;
            notifyObservers();
        }
    }

    /**
     * Athugar hvort d hefur unnið leikmanninn
     * 
     * @param d dealer
     * @return true ef d hefur unnið, annars false
     */
    public boolean whoWins(Player d) {
        if (this.scoreOnHand == 21 || d.scoreOnHand > 21) {
            return true;
        } else if (d.scoreOnHand == 21 || this.scoreOnHand > 21) {
            return false;
        } else if (d.scoreOnHand > this.scoreOnHand) {
            return false;
        } else if (d.scoreOnHand < this.scoreOnHand) {
            return true;
        }
        return false;
    }

    /**
     * Leikmaður tekur þátt í nýjum leik, samtala og fjöldi spila á hendi er 0
     */
    public void newGame() {
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    /**
     * Athugar hvort leikmaður er sprunginn
     * 
     * @return true ef leikmaður er sprunginn, annars false
     */
    public boolean isBust() {
        return this.scoreOnHand > 21;
    }

    /**
     * Athugar hvort leikmaður fékk 21
     * 
     * @return true ef leikmaður fékk 21, annars false
     */
    public boolean hasTwentyOne(){
        return this.scoreOnHand == 21;
    }
    
    public int getScore() {
        return scoreOnHand;
    }
}
