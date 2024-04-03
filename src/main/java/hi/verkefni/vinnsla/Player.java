package hi.verkefni.vinnsla;

public class Player  implements PlayerInterface{
    private int scoreOnHand;
    private int cardsOnHand;

    public Player(){
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    @Override
    public int getScore() {
        return scoreOnHand;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void drawCard(Card s) {
        if (cardsOnHand < 6){
            scoreOnHand += s.getNumber();
            cardsOnHand += 1;
            System.out.println(s);
        }
    }

    @Override
    public boolean dealerWins(Player d) {
        if ( this.scoreOnHand == 21 || d.scoreOnHand > 21){
            return false;
        } else if (d.scoreOnHand == 21 || this.scoreOnHand > 21){
            return true;
        } else if ( d.scoreOnHand > this.scoreOnHand){
            return true;
        } else if (d.scoreOnHand < this.scoreOnHand){
            return false;
        }
        return false;
    }

    @Override
    public Player whoWon(Player d) {
        if (this.scoreOnHand == 21 || d.scoreOnHand > 21){
            return this;
        } else if ( d.scoreOnHand == 21 || this.scoreOnHand > 21){
            return d;
        }
        return null;
    }

    @Override
    public void newGame() {
        this.scoreOnHand = 0;
        this.cardsOnHand = 0;
    }

    @Override
    public boolean isBust() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBust'");
    }
}
