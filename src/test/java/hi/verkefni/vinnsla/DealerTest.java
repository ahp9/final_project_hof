package hi.verkefni.vinnsla;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
    
    private Dealer dealer;

    /**
     * Býr til nýjan Dealer
     */
    @BeforeEach
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
