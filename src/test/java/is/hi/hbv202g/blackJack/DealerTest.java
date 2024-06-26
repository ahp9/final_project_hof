package is.hi.hbv202g.blackJack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Testar Dealer klasan
 */
public class DealerTest {
    
    private Dealer dealer;

    /**
     * Býr til nýjan Dealer
     */
    @Before
    public void createDealer(){
        dealer = new Dealer();
    }

    /**
     * Prófar hvort að Dealer sé með 0 stig og 0 spil
     */
    @Test
    public void dealerIsEmpty(){
        assertEquals(dealer.getScore(), 0);
        assertEquals(0, dealer.getCards().size());
    }

    /**
     * Prófar hvort að Dealer hefur jafnt eða meira en 17 stig.
     */
    @Test
    public void testNotSeventeen() {
        assertFalse(dealer.getScore() >= 17);
    }

    /**
     * Prófar hvort dealer hefur jafnt eða meira en 17 stig.
     */
    @Test
    public void testHasSeventeen() {
        dealer.drawCard(new Card(Suit.HEART, Value.TEN));
        dealer.drawCard(new Card(Suit.HEART, Value.SEVEN));
        assertTrue(dealer.hasSeventeen());
    }
}

