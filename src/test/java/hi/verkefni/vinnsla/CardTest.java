package hi.verkefni.vinnsla;

import static org.junit.Assert.*;
import org.junit.Test;

public class CardTest {

    @Test
    public void createCard() {
        Suit suit = Suit.HJARTA;
        Value value = Value.AS;
        Card card = new Card(suit, value);

        assertNotNull(card);
        assertEquals(suit, card.getSuit());
        assertEquals(value, card.getValue());
    }

    @Test
    public void testGetNumber() {
        Card card = new Card(Suit.SPADI, Value.TVISTUR);
        assertEquals(2, card.getNumber());
    }
    
}
