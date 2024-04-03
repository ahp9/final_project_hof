package hi.verkefni.vinnsla;

public interface PlayerInterface {
    int getScore();

    String getName();

    void setName(String nafn);

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
    boolean dealerWins(Player d);

    /**
     * Skila hvaða leikmaður vann d (dealerinn) eða þessi leikmaður
     *
     * @param d - andstæðingur leikmannsins
     * @return - skilar þeim leikmanni sem vann  - null ef hvorugur vann
     */
    Player whoWon(Player d);

    /**
     * Athugar hvort leikmaður er sprunginn
     * 
     * @return - true ef leikmaður er sprunginn, annars false
     */
    boolean isBust();

    /**
     * Leikmaður tekur þátt í nýjum leik, samtala og fjöldi spila á hendi er 0
     */
    void newGame();
}
