package is.hi.hbv202g.blackJack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testar Card klasann
 */
public class CardTest {

    private Card card;
    private Suit suit;
    private Value value;


    /**
     * Býr til nýjan stokk.
     * Prófar hvort að stokkurinn sé nokkuð tómur.
     * Prófar hvort suit sé það sama og getSuit, sama með value og getValue.
     */
    @Before
    public void createCard() {
        suit = Suit.HEART;
        value = Value.ACE;
        card = new Card(suit, value);
    }

    /**
     * Prófar að spil sé ekki null
     */
    @Test
    public void cardIsNotNull() {
        assertNotNull(card);
    }

    /**
     * Prófar hvort að tegund af spili samsvarar getSuit.
     */
    @Test 
    public void testCardsSuitEqualsSuit(){
        assertEquals(suit, card.getSuit());
    }

    /**
     * Prófar hvort að gildi af spili samsvarar getValue.
     */
    @Test
    public void testCardsValueEqualsValue(){
        assertEquals(value, card.getValue());
    }

    /**
     * Prófar hvort að tegund af spili samsvarar ekki getSuit.
     */
    @Test 
    public void testCardsSuitNotEqualsSuit(){
        assertNotEquals(Suit.CLUB, card.getSuit());
    }

    /**
     * Prófar hvort að gildi af spili samsvarar ekki getValue.
     */
    @Test
    public void testCardsValueNotEqualsValue(){
        assertNotEquals(Value.TEN, card.getValue());
    }

    /**
     * Prófar hvort að númer og tegund af spili samsvarar réttu gildi frá getNumber.
     */
    @Test
    public void testNumberEqualsGetNumber() {
        Card card = new Card(Suit.SPADE, Value.TWO);
        assertEquals(Value.TWO.getVALUE(), card.getNumber());
    }

    /**
     * Prófar hvort að nýja spili samsvarar ekki gildi frá getNumber.
     */
    @Test
    public void testNumberNotEqualsGetNumber() {
        Card card = new Card(Suit.SPADE, Value.TWO);
        assertNotEquals(Value.EIGHT.getVALUE(), card.getNumber());
    }
    
}

