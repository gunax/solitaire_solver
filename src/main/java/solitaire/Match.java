package solitaire;

public class Match {
    private Top top;
    private Board board;
    private Deck deck;

    public Match(Top top, Board board, Deck deck) {
        this.top = top;
        this.board = board;
        this.deck = deck;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Match)) {
            return false;
        }

        Match otherMatch = (Match) other;
        return this.top.equals(otherMatch.top) && this.deck.equals(otherMatch.deck) && this.board.equals(otherMatch.board);
    }
}
