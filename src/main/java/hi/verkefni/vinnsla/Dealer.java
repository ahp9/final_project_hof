package hi.verkefni.vinnsla;

public class Dealer extends Player {
    private final int SEVENTEEN = 17;

    public Dealer() {
        super();
    }

    /**
     * Athugar hvort dealer hefur fleiri eða jafnt og 17 stig
     * 
     * @return skilar true ef dealer hefur jafnt eða meira 17 stig, annars false 
     */
    public boolean hasSeventeen() {
        System.out.println("Score" + getScore());
        return getScore() >= SEVENTEEN;
    }

}
