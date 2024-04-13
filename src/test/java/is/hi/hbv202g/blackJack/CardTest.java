package is.hi.hbv202g.blackJack;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testar Card klasan
 */
public class CardTest {

    /**
     * Býr til nýjan stokk.
     * Prófar hvort að stokkurinn sé nokkuð tómur.
     * Prófar hvort suit sé það sama og getSuit, sama með value og getValue.
     */
    @Test
    public void createCard() {
        Suit suit = Suit.HJARTA;
        Value value = Value.AS;
        Card card = new Card(suit, value);

        assertNotNull(card);
        assertEquals(suit, card.getSuit());
        assertEquals(value, card.getValue());
    }

    /**
     * Prófar hvort að númer og tegund af spili samsvarar réttu gildi frá getNumber.
     */
    @Test
    public void testGetNumber() {
        Card card = new Card(Suit.SPADI, Value.TVISTUR);
        assertEquals(2, card.getNumber());
    }
    
}

