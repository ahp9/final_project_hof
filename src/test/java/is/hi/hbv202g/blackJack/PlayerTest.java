package is.hi.hbv202g.blackJack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testar Player klasan
 */
public class PlayerTest {

    private Player player;
    private Player player1;
    private Player dealer;

    /**
     * Býr til nýja leikmenn.
     */
    @Before
    public void constructionPlayerTestObjects() {
        player = new Player();
        player1 = new Player();
        dealer = new Player();
    }

    /**
     * Prófar hvort spilið sem leikmaður dregur samsvarar réttum stigafjölda.
     */
    @Test
    public void testGetScore() {
        player.drawCard(new Card(Suit.SPADE, Value.FIVE));
        assertEquals(5, player.getScore());
    }

    /**
     * Prófar hvort leikmaður vinnur ef hann er með hærra spil en samt undir 21.
     */
    @Test
    public void testWhoWins_PlayerWins() {
        player1.drawCard(new Card(Suit.HEART, Value.TEN));
        dealer.drawCard(new Card(Suit.HEART, Value.FIVE));
        assertTrue(player1.whoWins(dealer));
    }

    /**
     * Prófar hvort dealer vinnur ef hann er með hærra spil en samt undir 21.
     */
    @Test
    public void testWhoWins_DealerWins() {
        player1.drawCard(new Card(Suit.HEART, Value.TEN));
        dealer.drawCard(new Card(Suit.HEART, Value.QUEEN));
        assertFalse(player1.whoWins(dealer));
    }

    /**
     * Prófar hvort stigin núllstillast viði nýjan leik.
     */
    @Test
    public void testNewGame() {
        player.drawCard(new Card(Suit.HEART, Value.TEN));
        player.newGame();
        assertEquals(0, player.getScore());
    }

    /**
     * Prófar hvort leikmaður sé sprunginn ef stigafjöldinn er undir 21.
     */
    @Test 
    public void testIsBust_False() {
        player.drawCard(new Card(Suit.HEART, Value.THREE));
        assertFalse(player.isBust());
    }

    /**
     * Prófar hvort leikmaður fær 21 ef spilin samsvara 21.
     */
    @Test 
    public void testHasTwentyOne_True() {
        player.drawCard(new Card(Suit.HEART, Value.TEN));
        player.drawCard(new Card(Suit.HEART, Value.ACE));
        assertTrue(player.hasTwentyOne());
    }

    /**
     * Prófar hvort leikmaður fær 21 ef spilin samsvara ekki 21.
     */
    @Test 
    public void testHasTwentyOne_False() {
        player.drawCard(new Card(Suit.HEART, Value.TEN));
        assertFalse(player.hasTwentyOne());
    }

    
}