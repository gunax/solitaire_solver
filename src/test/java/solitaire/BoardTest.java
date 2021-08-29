package solitaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Card card1, card2, card3, card4, card5, card6;

    @Before
    public void setup() {
        card1 = new Card(Card.Suit.Clubs, 1);
        card2 = new Card(Card.Suit.Diamonds, 2);
        card3 = new Card(Card.Suit.Hearts, 3);
        card4 = new Card(Card.Suit.Spades, 4);
        card5 = new Card(Card.Suit.Spades, 5);
        card6 = new Card(Card.Suit.Spades, 6);
    }

    @Test
    public void NewBoardTest() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board = new Board(columns);

        Card[][] get_columns = board.getColumns();
        assertEquals(get_columns[8][0], card1);
        assertEquals(get_columns[8][1], card2);
        assertEquals(get_columns[9][0], card3);
        assertEquals(get_columns[9][1], card4);
        assertEquals(0, get_columns[7].length);
    }

    @Test
    public void addCardConstructorTest() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{card1, card2};
        columns[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board = new Board(columns);
        Board board2 = new Board(board, card5, 3);
        Card[][] get_columns = board2.getColumns();
        assertEquals(card1, get_columns[7][0]);
        assertEquals(card2, get_columns[7][1]);
        assertEquals(card3, get_columns[9][0]);
        assertEquals(card4, get_columns[9][1]);
        assertEquals(0, get_columns[2].length);
        assertEquals(card5, get_columns[8][0]);
    }

    @Test
    public void boardEqualsTest() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        Card[][] columns2 = new Card[10][];
        columns2[0] = new Card[]{new Card(Card.Suit.Clubs, 1), card2};
        columns2[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns2[i] = new Card[0];
        }
        Board board2 = new Board(columns2);

        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
    }

    @Test
    public void boardEqualsDifferentOrderTest() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        Card[][] columns2 = new Card[10][];
        columns2[1] = new Card[]{new Card(Card.Suit.Clubs, 1), card2};
        columns2[0] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns2[i] = new Card[0];
        }
        Board board2 = new Board(columns2);

        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
    }

    @Test
    public void boardCardsInsideColumnDifferentOrder_Test() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        Card[][] columns2 = new Card[10][];
        columns2[0] = new Card[]{card2, new Card(Card.Suit.Clubs, 1)};
        columns2[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns2[i] = new Card[0];
        }
        Board board2 = new Board(columns2);

        assertFalse(board1.equals(board2));
        assertFalse(board2.equals(board1));
    }

    @Test
    public void column8Value() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        assertEquals(14l ,Board.columnValue(columns1[8]));
    }

    @Test
    public void column9Value() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        assertEquals(card3.getOrdinal() * 52 + card4.getOrdinal() ,Board.columnValue(columns1[9]));
    }

    @Test
    public void columnEmptyValue() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        assertEquals(0l ,Board.columnValue(columns1[0]));
    }

    @Test
    public void boardsInDifferentOrderSameHash() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        Card[][] columns2 = new Card[10][];
        columns2[0] = new Card[]{card2, new Card(Card.Suit.Clubs, 1)};
        columns2[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns2[i] = new Card[0];
        }
        Board board2 = new Board(columns2);

        assertNotEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void boardDifferentOrderSameHashTest() {
        Card[][] columns1 = new Card[10][];
        columns1[0] = new Card[]{card1, card2};
        columns1[1] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns1[i] = new Card[0];
        }
        Board board1 = new Board(columns1);

        Card[][] columns2 = new Card[10][];
        columns2[1] = new Card[]{new Card(Card.Suit.Clubs, 1), card2};
        columns2[0] = new Card[]{card3, card4};
        for (int i = 2; i < 10; i ++){
            columns2[i] = new Card[0];
        }
        Board board2 = new Board(columns2);

        assertEquals(board1.hashCode(), board2.hashCode());
    }
}
