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
            shift *= 52;
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

    public boolean equals(Board other) {
        for (int col = 0; col < 10; col++) {
            if (this.columns[col].length != other.columns[col].length) {
                return false;
            }
            for (int i = 0; i < this.columns[col].length; i++) {
                if (!this.columns[col][i].equals(other.columns[col][i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
