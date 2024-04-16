package is.hi.hbv202g.blackJack;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testar Deck klasann
 */
public class DeckTest {
    private Deck deck;
    
    /**
     * Býr til nýjan stokk.
     */
    @Before
    public void createDeck() {
        deck = new Deck();
    }

    /**
     * Prófar hvort stokkurinn sé tómur
     */
    @Test
    public void testDeckConstructor() {
        assertNotNull(deck);
    }

    /**
     * Prófar hvort stokkur sé tómur þegar það er búið að draga öll spil
     */
    @Test
    public void testDrawCardEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            @SuppressWarnings("unused")
            Card card = deck.drawCard();
        }

        assertNull(deck.drawCard());
    }

    /**
     * Prófar hvort spil sé í stokki
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
