package solitaire;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MatchTest {

    Deck deck;
    Top top;
    Board board;
    Match match;
    Card clubsAce, diamonds2, hearts3, spades4, spades5, spades6;

    @Before
    public void setup() {
        clubsAce = new Card(Card.Suit.Clubs, 1);
        diamonds2 = new Card(Card.Suit.Diamonds, 2);
        hearts3 = new Card(Card.Suit.Hearts, 3);
        spades4 = new Card(Card.Suit.Spades, 4);
        spades5 = new Card(Card.Suit.Spades, 5);
        spades6 = new Card(Card.Suit.Spades, 6);

        top = new Top(new int[]{1,2,3,4,5,6,7,8});
        deck = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, spades5}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        board = new Board(columns);

        match = new Match(top, board, deck, 0);
    }

    @Test
    public void equals_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, spades5}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2, 1);

        assertEquals(match, match2);
    }

    @Test
    public void differentTopNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,5,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{clubsAce, diamonds2, hearts3, clubsAce, hearts3}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2, 1);

        assertNotEquals(match, match2);
    }

    @Test
    public void differentBoardNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, spades5}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, spades5};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2, 1);

        assertNotEquals(match, match2);
    }

    @Test
    public void differentDeckNotEqual_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{clubsAce, hearts3, diamonds2, spades4, spades5}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2, 0);

        assertNotEquals(match, match2);
    }

    @Test
    public void hashEquals_Test() {
        Top top2 = new Top(new int[]{1,2,3,4,5,6,7,8});
        Deck deck2 = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, spades5}, 2);

        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        for (int i = 2; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board board2 = new Board(columns);
        Match match2 = new Match(top2, board2, deck2, 0);

        assertEquals(match.hashCode(), match2.hashCode());
    }

    @Test
    public void childrenMoveDeckToEmptyBoardSlot_Test() {
        Board goalBoard = new Board(board, hearts3, 0);
        Deck goalDeck = deck.draw();
        Match goalMatch = new Match(top, goalBoard, goalDeck, 1);
        List<Match> children = match.moves();

        assertTrue(children.contains(goalMatch));
    }

    @Test
    public void childrenMoveDeckToCompatibleBoardSlot_Test() {
        Deck startDeck = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, new Card(Card.Suit.Spades, 3)}, 4);
        Match startMatch = new Match(top, board, startDeck, 1);

        Board goalBoard = new Board(board, new Card(Card.Suit.Spades, 3), 9);
        Deck goalDeck = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4}, 3);
        Match goalMatch = new Match(top, goalBoard, goalDeck, 1);
        List<Match> children = startMatch.moves();

        assertTrue(children.contains(goalMatch));
    }

    @Test
    public void childrenMoveDeckToTop_Test() {
        Top startTop = new Top(new int[]{1,2,3,2,5,6,7,8});
        Deck startDeck = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4, new Card(Card.Suit.Spades, 3)}, 4);
        Match startMatch = new Match(startTop, board, startDeck, 1);

        Top goalTop = new Top(new int[]{1,2,3,3,5,6,7,8});
        Board goalBoard = new Board(board, new Card(Card.Suit.Spades, 3), 9);
        Deck goalDeck = new Deck(new Card[]{clubsAce, diamonds2, hearts3, spades4}, 3);
        Match goalMatch = new Match(goalTop, board, goalDeck, 2);
        List<Match> children = startMatch.moves();

        assertTrue(children.contains(goalMatch));
    }

    @Test
    public void rearrangeBoardMoves_Test() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        columns[2] = new Card[]{spades5};
        for (int i = 3; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board startBoard = new Board(columns);

        Card[][] goalColumns = new Card[10][];
        goalColumns[0] = new Card[]{clubsAce, diamonds2};
        goalColumns[1] = new Card[]{hearts3};
        goalColumns[2] = new Card[]{spades5, spades4};
        for (int i = 3; i < 10; i ++){
            goalColumns[i] = new Card[0];
        }
        Board goalBoard = new Board(goalColumns);

        Match startMatch = new Match(top, startBoard, deck, 0);
        Match goalMatch = new Match(top, goalBoard, deck, 1);

        List<Match> result = startMatch.moves();

        assertTrue(result.contains(goalMatch));
    }

    @Test
    public void boardToTopMoves_Test() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        columns[2] = new Card[]{spades5};
        for (int i = 3; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board startBoard = new Board(columns);
        Top startTop = new Top(new int[]{1,1,3,4,5,6,7,8});

        Card[][] goalColumns = new Card[10][];
        goalColumns[0] = new Card[]{clubsAce};
        goalColumns[1] = new Card[]{hearts3, spades4};
        goalColumns[2] = new Card[]{spades5};
        for (int i = 3; i < 10; i ++){
            goalColumns[i] = new Card[0];
        }
        Board goalBoard = new Board(goalColumns);
        Top goalTop = new Top(new int[]{1,2,3,4,5,6,7,8});

        Match startMatch = new Match(startTop, startBoard, deck, 0);
        Match goalMatch = new Match(goalTop, goalBoard, deck, 1);

        List<Match> result = startMatch.moves();

        assertTrue(result.contains(goalMatch));
    }

    @Test
    public void boardToTopMovesCannotMoveCoveredCards_Test() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{clubsAce, diamonds2};
        columns[1] = new Card[]{hearts3, spades4};
        columns[2] = new Card[]{spades5};
        for (int i = 3; i < 10; i ++){
            columns[i] = new Card[0];
        }
        Board startBoard = new Board(columns);
        Top startTop = new Top(new int[]{1,1,2,4,5,6,7,8});

        Card[][] goalColumns = new Card[10][];
        goalColumns[0] = new Card[]{clubsAce, diamonds2};
        goalColumns[1] = new Card[]{spades4};
        goalColumns[2] = new Card[]{spades5};
        for (int i = 3; i < 10; i ++){
            goalColumns[i] = new Card[0];
        }
        Board goalBoard = new Board(goalColumns);
        Top goalTop = new Top(new int[]{1,1,3,4,5,6,7,8});

        Match startMatch = new Match(startTop, startBoard, deck, 0);
        Match goalMatch = new Match(goalTop, goalBoard, deck, 1);

        List<Match> result = startMatch.moves();

        assertTrue(!result.contains(goalMatch));
    }
}
