package is.hi.hbv202g.blackJack;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    public void testDrawCardEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
        }

        assertNull(deck.drawCard());
    }

    /**
     * Prófar hvort spil sé í stokki, .....
     */
    @Test
    public void testDrawCardInDeck(){
        Card card = new Card(Suit.SPADE, Value.TWO);

        for(int i = 0; i < 52; i++){
            Card drawnCard = deck.drawCard();
            if(drawnCard.equals(card)){
                assertTrue(drawnCard.equals(card));
            }
        }
    }
    
}
