package hi.verkefni.vinnsla;

public class Player extends Observable implements PlayerInterface{
    private int scoreOnHand;
    private int cardsOnHand;

    public Player(){
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    public int getScore() {
        return scoreOnHand;
    }

    public void drawCard(Card s) {
        if (cardsOnHand < 6){
            scoreOnHand += s.getNumber();
            cardsOnHand += 1;
            notifyObservers();
        }
    }

    public boolean whoWins(Player d) {
        if ( this.scoreOnHand == 21 || d.scoreOnHand > 21){
            return true;
        } else if (d.scoreOnHand == 21 || this.scoreOnHand > 21){
            return false;
        } else if ( d.scoreOnHand > this.scoreOnHand){
            return false;
        } else if (d.scoreOnHand < this.scoreOnHand){
            return true;
        }
        return false;
    }

    public void newGame() {
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    public boolean isBust() {
        return this.scoreOnHand > 21;
    }

    public boolean hasTwentyOne(){
        return this.scoreOnHand == 21;
    }
}
