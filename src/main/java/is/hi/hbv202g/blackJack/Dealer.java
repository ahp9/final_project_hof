package is.hi.hbv202g.blackJack;


public class Dealer extends Player {
    private final int SEVENTEEN = 17;

    public Dealer() {
        super();
    }

    /**
     * Athugar hvort dealer hefur fleiri eða jafnt og 17 stig
     * 
     * @return skilar true ef dealer hefur jafnt eða meira en 17 stig, annars false
     */
    public boolean hasSeventeen() {
        return getScore() >= SEVENTEEN;
    }

}
