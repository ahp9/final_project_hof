package hi.verkefni.vinnsla;

public interface LeikmadurInterface {
    int getSamtals();

    String getNafn();

    void setNafn(String nafn);

    /**
     * Leikmaður má hafa mest max fjölda spila á hendi.
     * Gefur leikmanni spil s ef leikmaður ef max er ekki náð.
     * Uppfærir samtölu spila
     *
     * @param s spilið sem á að gefa leikmanni
     */
    void gefaSpil(SpilV s);

    /**
     * Athugar hvort d hefur unnið leikmanninn
     *
     * @param d dealer
     * @return satt ef d hefur unnið, annars false
     */
    boolean vinnurDealer(Leikmadur d);

    /**
     * Skila hvaða leikmaður vann d (dealerinn) eða þessi leikmaður
     *
     * @param d - andstæðingur leikmannsins
     * @return - skilar þeim leikmanni sem vann  - null ef hvorugur vann
     */
    Leikmadur hvorVann(Leikmadur d);

    /**
     * Leikmaður tekur þátt í nýjum leik, samtala og fjöldi spila á hendi er 0
     */
    void nyrLeikur();
}
