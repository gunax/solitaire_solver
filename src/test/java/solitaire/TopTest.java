package solitaire;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopTest {

    @Test
    public void full() {
        Top top = new Top(new int[]{13,13,13,13,13,13,13,13});
        assertTrue(top.full());
    }

    @Test
    public void notFull() {
        Top top = new Top(new int[]{12,13,13,13,13,13,13,13});
        assertFalse(top.full());
    }

    @Test
    public void addClubs() {
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 1)));
        assertTrue(top.canAdd(new Card(Card.Suit.Clubs, 2)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 3)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 4)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 5)));
        assertTrue(top.canAdd(new Card(Card.Suit.Clubs, 6)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 7)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 8)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 9)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 10)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 11)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 12)));
        assertFalse(top.canAdd(new Card(Card.Suit.Clubs, 13)));
    }

    @Test
    public void addDiamonds() {
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 1)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 2)));
        assertTrue(top.canAdd(new Card(Card.Suit.Diamonds, 3)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 4)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 5)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 6)));
        assertTrue(top.canAdd(new Card(Card.Suit.Diamonds, 7)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 8)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 9)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 10)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 11)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 12)));
        assertFalse(top.canAdd(new Card(Card.Suit.Diamonds, 13)));
    }

    @Test
    public void addHearts() {
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 1)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 2)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 3)));
        assertTrue(top.canAdd(new Card(Card.Suit.Hearts, 4)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 5)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 6)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 7)));
        assertTrue(top.canAdd(new Card(Card.Suit.Hearts, 8)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 9)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 10)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 11)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 12)));
        assertFalse(top.canAdd(new Card(Card.Suit.Hearts, 13)));
    }

    @Test
    public void addSpades() {
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 1)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 2)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 3)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 4)));
        assertTrue(top.canAdd(new Card(Card.Suit.Spades, 5)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 6)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 7)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 8)));
        assertTrue(top.canAdd(new Card(Card.Suit.Spades, 9)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 10)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 11)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 12)));
        assertFalse(top.canAdd(new Card(Card.Suit.Spades, 13)));
    }

    @Test
    public void testAddCard() throws Exception{
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        Top addedTop = new Top(top, new Card(Card.Suit.Clubs, 2));

        assertArrayEquals(new int[]{2,2,3,4,5,6,7,8}, addedTop.getTop());
    }

    @Test
    public void testAddCardSecondHalf() throws Exception{
        Top top = new Top(new int[]{1,2,3,4,5,6,7,8});
        Top addedTop = new Top(top, new Card(Card.Suit.Clubs, 6));

        assertArrayEquals(new int[]{1,2,3,4,6,6,7,8}, addedTop.getTop());
    }

    @Test
    public void equalsTest() {
        Top top1 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});

        assertEquals(top1, top2);
    }

    @Test
    public void notEqualsTest() {
        Top top1 = new Top(new int[]{1,2,4,3,5,6,7,8});
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});

        assertNotEquals(top1, top2);
    }

}
