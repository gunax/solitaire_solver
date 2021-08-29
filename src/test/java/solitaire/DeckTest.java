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
}
