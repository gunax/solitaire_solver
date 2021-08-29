package solitaire;

import java.util.Arrays;

public class Deck {
    private Card[] cards;
    private int position;

    public Deck(Card[] c, int pos) {
        cards = Arrays.copyOf(c, c.length);
        this.position = pos;
    }

    public Deck(Card[] c, int skip, int pos) {
        cards = Arrays.copyOfRange(c, skip, c.length);
        this.position = pos;
    }

    public int size() {
        return cards.length;
    }

    public Card getTop() {
        if (position >= cards.length) {
            return null;
        }
        return cards[position];
    }

    public Deck draw() {
        Card[] new_cards = new Card[cards.length - 1];
        for (int i = 0; i < position; i++) {
            new_cards[i] = cards[i];
        }
        for (int i = position + 1; i < cards.length; i++) {
            new_cards[i - 1] = cards[i];
        }
        if (position == 0) {
            return new Deck(new_cards, position);
        }
        return new Deck(new_cards, position - 1);
    }
}
