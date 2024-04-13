package is.hi.hbv202g.blackJack;

import java.util.List;

public interface PlayerInterface {
    int getScore();
    List<Card> getCards();

    /**
     * Leikmaður má hafa mest max fjölda spila á hendi.
     * Gefur leikmanni spil s ef leikmaður ef max er ekki náð.
     * Uppfærir samtölu spila
     *
     * @param s spilið sem á að gefa leikmanni
     */
    void drawCard(Card s);

    /**
     * Athugar hvort d hefur unnið leikmanninn
     *
     * @param d dealer
     * @return satt ef d hefur unnið, annars false
     */
    boolean whoWins(Player d);

    /**
     * Athugar hvort leikmaður er sprunginn
     * 
     * @return - true ef leikmaður er sprunginn, annars false
     */
    boolean isBust();

    /**
     * Athugar hvort leikmaður fékk 21
     * 
     * @return - true ef leikmaður fékk 21, annars false
     */
    boolean hasTwentyOne();

    /**
     * Leikmaður tekur þátt í nýjum leik, samtala og fjöldi spila á hendi er 0
     */
    void newGame();
}
