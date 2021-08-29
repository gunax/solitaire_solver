package solitaire;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Top top;
    private Board board;
    private Deck deck;
    private int moves;

    public Match(Top top, Board board, Deck deck, int moves) {
        this.top = top;
        this.board = board;
        this.deck = deck;
        this.moves = moves;
    }

    public List<Match> moves() {
        ArrayList<Match> moves = new ArrayList<>();

        moves.addAll(deckToBoardMoves());

        return moves;
    }

    private List<Match> deckToBoardMoves() {
        ArrayList<Match> moves = new ArrayList<Match>();

        //try moving top of deck to board
        if (deck.getTop() != null) {
            boolean emptyColumnMovedTo = false;
            for (int i = 0; i < 10; i ++) {
                Card[] col = board.getColumns()[i];
                if (col.length == 0) {
                    if (!emptyColumnMovedTo) {
                        moves.add(new Match(top, new Board(board, deck.getTop(), i), deck.draw(), this.moves + 1));
                        emptyColumnMovedTo = true;
                    }
                }
                else {
                    Card bottomCard = col[col.length-1];
                    if (bottomCard.getSuit() == deck.getTop().getSuit() && bottomCard.getValue() == deck.getTop().getValue() + 1) {
                        moves.add(new Match(top, new Board(board, deck.getTop(), i), deck.draw(), this.moves + 1));
                    }
                }

            }
        }
        return moves;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Match)) {
            return false;
        }

        Match otherMatch = (Match) other;
        return this.top.equals(otherMatch.top) && this.deck.equals(otherMatch.deck) && this.board.equals(otherMatch.board);
    }

    @Override
    public int hashCode() {
        return top.hashCode() ^ board.hashCode() ^ deck.hashCode();
    }
}
