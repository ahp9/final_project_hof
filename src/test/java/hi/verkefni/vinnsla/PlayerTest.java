package hi.verkefni.vinnsla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;
    private Player player1;
    private Player player2;

    @Before
    public void constructionPlayerTestObjects() {
        player = new Player();
        player1 = new Player();
        player2 = new Player();
    }

    @Test
    public void testGetScore() {
        player.drawCard(new Card(Suit.SPADI, Value.FIMMA));
        assertEquals(5, player.getScore());
    }

    @Test
    public void testWhoWins_PlayerWins() {
        player1.drawCard(new Card(Suit.HJARTA, Value.TIA));
        player2.drawCard(new Card(Suit.HJARTA, Value.FIMMA));
        assertTrue(player1.whoWins(player2));
    }

    @Test
    public void testWhoWins_DealerWins() {
        player1.drawCard(new Card(Suit.HJARTA, Value.TIA));
        player2.drawCard(new Card(Suit.HJARTA, Value.DROTTNING));
        assertFalse(player1.whoWins(player2));
    }

    @Test
    public void testNewGame() {
        player.drawCard(new Card(Suit.HJARTA, Value.TIA));
        player.newGame();
        assertEquals(0, player.getScore());
    }

    @Test 
    public void testIsBust_False() {
        player.drawCard(new Card(Suit.HJARTA, Value.THRISTUR));
        assertFalse(player.isBust());
    }

    @Test 
    public void testHasTwentyOne_True() {
        player.drawCard(new Card(Suit.HJARTA, Value.TIA));
        player.drawCard(new Card(Suit.HJARTA, Value.AS));
        assertTrue(player.hasTwentyOne());
    }

    @Test 
    public void testHasTwentyOne_False() {
        player.drawCard(new Card(Suit.HJARTA, Value.TIA));
        assertFalse(player.hasTwentyOne());
    }

    
}
