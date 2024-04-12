package hi.verkefni.vinnsla;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

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
}
