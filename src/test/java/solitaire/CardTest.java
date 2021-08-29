package solitaire;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
    
    @Test
    public void testToString() {
        Card card = new Card(Card.Suit.Clubs, 5);
        assertEquals( "5 of Clubs", card.toString());
    }

    @Test
    public void testToStringAce() {
        Card card = new Card(Card.Suit.Clubs, 1);
        assertEquals( "Ace of Clubs", card.toString());
    }

    @Test
    public void testToStringQueen() {
        Card card = new Card(Card.Suit.Clubs, 12);
        assertEquals( "Queen of Clubs", card.toString());
    }

    @Test
    public void testEqualsTrue() {
        Card card1 = new Card(Card.Suit.Diamonds, 1);
        Card card2 = new Card(Card.Suit.Diamonds, 1);

        assertTrue(card1.equals(card2));
        assertTrue(card2.equals(card1));
    }

    @Test
    public void testEqualsDifferentSuit() {
        Card card1 = new Card(Card.Suit.Spades, 1);
        Card card2 = new Card(Card.Suit.Diamonds, 1);

        assertFalse(card1.equals(card2));
        assertFalse(card2.equals(card1));
    }

    @Test
    public void testEqualsDifferentValue() {
        Card card1 = new Card(Card.Suit.Spades, 4);
        Card card2 = new Card(Card.Suit.Spades, 5);

        assertFalse(card1.equals(card2));
        assertFalse(card2.equals(card1));
    }

    @Test
    public void ordinalLo() {
        Card card = new Card(Card.Suit.Clubs, 1);
        assertEquals(1, card.getOrdinal());
    }

    @Test
    public void ordinalHi() {
        Card card = new Card(Card.Suit.Spades, 13);
        assertEquals(52, card.getOrdinal());
    }

    @Test
    public void hashcodeTest() {
        Card card1 = new Card(Card.Suit.Spades, 13);
        Card card2 = new Card(Card.Suit.Spades, 13);
        assertEquals(card1.hashCode(), card2.hashCode());
    }
}
