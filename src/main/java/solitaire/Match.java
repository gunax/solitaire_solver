package solitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Match {
    private Top top;
    private Board board;
    private Deck deck;
    private int num_moves;

    public Match(Top top, Board board, Deck deck, int num_moves) {
        this.top = top;
        this.board = board;
        this.deck = deck;
        this.num_moves = num_moves;
    }

    public List<Match> moves() {
        ArrayList<Match> moves = new ArrayList<>();

        moves.addAll(deckToBoardMoves());
        moves.addAll(deckToTopMoves());
        moves.addAll(boardRearrangeMoves());
        moves.addAll(boardToTopMoves());

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
                        moves.add(new Match(top, new Board(board, deck.getTop(), i), deck.draw(), this.num_moves + 1));
                        emptyColumnMovedTo = true;
                    }
                }
                else {
                    Card bottomCard = col[col.length-1];
                    if (bottomCard.getSuit() == deck.getTop().getSuit() && bottomCard.getValue() == deck.getTop().getValue() + 1) {
                        moves.add(new Match(top, new Board(board, deck.getTop(), i), deck.draw(), this.num_moves + 1));
                    }
                }

            }
        }
        return moves;
    }

    private List<Match> deckToTopMoves() {
        ArrayList<Match> moves = new ArrayList<>();

        if (deck.getTop() != null) {
            if (top.canAdd(deck.getTop())) {
                try {
                    moves.add(new Match(new Top(top, deck.getTop()), board, deck.draw(), num_moves + 1));
                }
                catch(Exception e) {
                    //just don't add it... (shrug)
                    System.out.println("I tried to move an invalid deck card to the top. This should never happen.");
                }
            }
        }
        return moves;
    }

    private List<Match> boardRearrangeMoves() {
        ArrayList<Match> moves = new ArrayList<>();

        Card[][] columns = board.getColumns();

        //for each column, take bottom card and add it to future columns
        for (int startCol = 0; startCol < 10; startCol++) {
            boolean movedToEmpty = false;
            if (columns[startCol].length == 0) {
                continue;
            }
            Card the_card = columns[startCol][columns[startCol].length - 1];
            for (int endCol = 0; endCol < 10; endCol++) {
                if (startCol == endCol) {
                    continue;
                }
                if (columns[endCol].length == 0 && !movedToEmpty) {
                    Board new_board = Board.createRearrangedBoard(board, startCol, endCol);
                    moves.add(new Match(top, new_board, deck, num_moves + 1));
                    movedToEmpty = true;
                }
                else if (columns[endCol].length > 0){
                    Board new_board = Board.createRearrangedBoard(board, startCol, endCol);
                    moves.add(new Match(top, new_board, deck, num_moves + 1));
                }
            }
        }
        return moves;
    }

    private List<Match> boardToTopMoves() {
        ArrayList<Match> moves = new ArrayList<>();
        Card[][] columns = board.getColumns();
        for (int col = 0; col < 10; col++) {
            if (columns[col].length == 0) {
                continue;
            }
            Card bottom_card = columns[col][columns[col].length - 1];
            if (top.canAdd(bottom_card)){
                try {
                    Top new_top = new Top(top, bottom_card);
                    Board new_board = new Board(board, col);
                    moves.add(new Match(new_top, new_board, deck, num_moves + 1));
                }
                catch(Exception e) {
                    System.out.println("Tried to move a board card to invalid top. Oops.");
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

    public boolean finished() {
        return top.full();
    }
}
