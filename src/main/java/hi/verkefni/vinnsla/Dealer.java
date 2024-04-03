package hi.verkefni.vinnsla;

public class Dealer extends Player {
    private final int SEVENTEEN = 17;

    public Dealer() {
        super();
    }

    public boolean hasSeventeen() {
        System.out.println("Score" + getScore());
        return getScore() >= SEVENTEEN;
    }

}
