package solitaire;

import java.util.Arrays;

public class Top {

    private int[] top = new int[8];

    public Top(int[] set) {
        top = Arrays.copyOf(set, 8);
    }

    public Top() {
        for (int i = 0; i < 8; i++) {
            top[i] = 0;
        }
    }

    public Top(Top other, Card card) throws Exception{
        this.top = other.top;
        if (other.top[card.getSuit().ordinal()] == card.getValue() - 1) {
            this.top[card.getSuit().ordinal()] += 1;
        }
        else if (other.top[card.getSuit().ordinal() + 4] == card.getValue() - 1) {
            this.top[card.getSuit().ordinal() + 4] += 1;
        }
        else {
            throw new Exception();
        }
    }

    public boolean full() {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += top[i];
        }
        return sum == 13 * 8;
    }

    public boolean canAdd(Card card) {
        return (this.top[card.getSuit().ordinal()] == card.getValue() - 1)
                || (this.top[card.getSuit().ordinal() + 4] == card.getValue() - 1);
    }

    public int[] getTop() {
        return top;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        final int prime = 31;
        for (int i = 0; i < 8; i++) {
            hash *= 14;
            hash += top[i];
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Top) {
            Top otherTop = (Top) other;
            for (int i = 0; i < 8; i++) {
                if (top[i] != otherTop.top[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
