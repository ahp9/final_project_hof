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
     * Prófar hvort að Dealer sé með 0 stig.
     */
    @Test
    public void dealerIsEmpty(){
        assertEquals(dealer.getScore(), 0);
    }

    @Test
    public void testNotSeventeen() {
        assertFalse(dealer.getScore() >= 17);
    }

    @Test
    public void testHasSeventeen() {
        dealer.drawCard(new Card(Suit.HJARTA, Value.TIA));
        dealer.drawCard(new Card(Suit.HJARTA, Value.SJOA));
        assertTrue(dealer.hasSeventeen());
    }
}

