package is.hi.hbv202g.blackJack;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {
    private Deck deck;

    @Before
    public void createDeck() {
        deck = new Deck();
    }

    @Test
    public void testDeckConstructor() {
        assertNotNull(deck);
    }

    @Test
    public void testDragaSpil() {
        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
        }

        assertNull(deck.drawCard());
    }
    
}
