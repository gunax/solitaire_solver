package solitaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MatchTest {

    Deck deck;
    Top top;
    Board board;
    Match match;
    Card card1, card2, card3, card4, card5, card6;

    @Before
    public void setup() {
        card1 = new Card(Card.Suit.Clubs, 1);
        card2 = new Card(Card.Suit.Diamonds, 2);
        card3 = new Card(Card.Suit.Hearts, 3);
        card4 = new Card(Card.Suit.Spades, 4);
        card5 = new Card(Card.Suit.Spades, 5);
        card6 = new Card(Card.Suit.Spades, 6);

        top = new Top(new int[]{1,2,3,4,5,6,7,8});
        deck = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        board = new Board(columns);

        match = new Match(top, board, deck);
    }

    @Test
    public void equals_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2);

        assertEquals(match, match2);
    }

    @Test
    public void differentTopNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,5,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2);

        assertNotEquals(match, match2);
    }

    @Test
    public void differentBoardNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card5};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2);

        assertNotEquals(match, match2);
    }

    @Test
    public void differentDeckNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{card1, card3, card2, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2);

        assertNotEquals(match, match2);
    }

    @Test
    public void hashEquals_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{card1, card2, card3, card1, card3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2);

        assertEquals(match.hashCode(), match2.hashCode());
    }
}
