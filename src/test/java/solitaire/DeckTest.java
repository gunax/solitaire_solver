package solitaire;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {

    private Card card1, card2, card3;
    @Before
    public void setup() {
        card1 = new Card(Card.Suit.Clubs, 1);
        card2 = new Card(Card.Suit.Diamonds, 2);
        card3 = new Card(Card.Suit.Hearts, 3);
    }

    @Test
    public void sizeTest() {
        Deck deck = new Deck(new Card[]{card1, card2, card3},0);

        assertEquals(3, deck.size());
    }

    @Test
    public void getTop() {
        Deck deck = new Deck(new Card[]{card1, card2, card3}, 0);
        assertEquals(card1, deck.getTop());
    }

    @Test
    public void drawFirst() {
        Deck deck = new Deck(new Card[]{card1, card2, card3}, 0);
        Deck deck2 = deck.draw();

        assertEquals(2, deck2.size());
        assertTrue(new Card(Card.Suit.Diamonds, 2).equals(deck2.getTop()));
    }

    @Test
    public void drawSecond() {
        Deck deck = new Deck(new Card[]{card1, card2, card3}, 1);
        Deck deck2 = deck.draw();

        assertEquals(2, deck2.size());
        assertTrue(new Card(Card.Suit.Clubs, 1).equals(deck2.getTop()));
    }

    @Test
    public void drawLast() {
        Deck deck = new Deck(new Card[]{card1, card2, card3}, 2);
        Deck deck2 = deck.draw();

        assertEquals(2, deck2.size());
        assertTrue(new Card(Card.Suit.Diamonds, 2).equals(deck2.getTop()));
    }

    @Test
    public void hashcodeSame() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        assertEquals(deck1.hashCode(), deck2.hashCode());
    }

    @Test
    public void hashcodeDifferentPosNotEqual() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 3);

        assertNotEquals(deck1.hashCode(), deck2.hashCode());
    }

    @Test
    public void hashcodeDifferentOrderNotEqual() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card3, card1}, 3);

        assertNotEquals(deck1.hashCode(), deck2.hashCode());
    }

    @Test
    public void equals() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        assertEquals(deck1, deck2);
    }

    @Test
    public void DifferentPosNotEqual() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 3);

        assertNotEquals(deck1, deck2);
    }

    @Test
    public void DifferentOrderNotEqual() {
        Deck deck1 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card3, card1}, 3);

        assertNotEquals(deck1, deck2);
    }
}
