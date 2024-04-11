package hi.verkefni.vinnsla;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DealerTest {
    
    private Dealer dealer;

    @Before
    public void createDealer(){
        dealer = new Dealer();
    }

    @Test
    public void dealerIsEmpty(){
        assertEquals(dealer.getScore(), 0);
    }
}
