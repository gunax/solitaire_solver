package solitaire;

import java.util.Arrays;
import java.util.Comparator;

public class Board {
    private Card[][] columns = new Card[10][];
    private int hash;

    public Board() {
        //empty board
        for (Card[] col : columns) {
            col = new Card[0];
        }
    }

    public Board(Card[][] columns) {
        this.columns = columns;
        sort();
    }

    public static Board createRearrangedBoard(Board last, int origin_col, int destination_col) {
        Card[][] old_columns = last.getColumns();
        Card[][] new_columns = new Card[10][];
        Card the_card = old_columns[origin_col][old_columns[origin_col].length - 1];
        for (int i = 0; i < 10; i++) {
            if (i == origin_col) {
                new_columns[i] = Arrays.copyOfRange(old_columns[i], 0, old_columns[i].length - 1);
            }
            else if (i == destination_col) {
                new_columns[i] = Arrays.copyOf(old_columns[i], old_columns[i].length + 1);
                new_columns[i][new_columns[i].length - 1] = the_card;
            }
            else {
                new_columns[i] = old_columns[i];
            }
        }
        return new Board(new_columns);
    }

    public Board(Board last, int colToRemoveFrom) {
        for (int i = 0; i < 10; i ++) {
            if (i != colToRemoveFrom) {
                this.columns[i] = last.columns[i];
            }
            else {
                this.columns[i] = Arrays.copyOf(last.columns[i], last.columns[i].length - 1);
            }
        }
        sort();
    }

    public Board(Board last, Card card, int col) {
        for (int i = 0; i < 10; i ++) {
            if (i != col) {
                this.columns[i] = last.columns[i];
            }
            else {
                this.columns[i] = Arrays.copyOf(last.columns[i], last.columns[i].length + 1);
                this.columns[i][last.columns[i].length] = card;
            }
        }
        sort();
    }

    public Card[][] getColumns() {
        return columns;
    }

    public static int columnValue(Card[] col) {
        int val = 0;
        int shift = 1;
        for (int i = 0; i < col.length; i++) {
            val *= shift;
            shift *= 53;
            val += col[i].getOrdinal();
        }
        return val;
    }

    private void sort() {
        Arrays.sort(columns, Comparator.comparingLong(Board::columnValue));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        for (int i = 0; i < 10; i ++) {
            hash = prime * (hash ^ columnValue(columns[i]));
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }

        Board otherBoard = (Board) other;

        for (int col = 0; col < 10; col++) {
            if (this.columns[col].length != otherBoard.columns[col].length) {
                return false;
            }
            for (int i = 0; i < this.columns[col].length; i++) {
                if (!this.columns[col][i].equals(otherBoard.columns[col][i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
