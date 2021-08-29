package solitaire;

public class Card {
    public int getOrdinal() {
        return this.suit.ordinal() * 13 + this.getValue();
    }

    public enum Suit {
        Clubs,
        Diamonds,
        Hearts,
        Spades
    }
    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    private static String faceValue(int value) {
        if (value == 1) {
            return "Ace";
        }
        else if (value == 11) {
            return "Jack";
        }
        else if (value == 12) {
            return "Queen";
        }
        else if (value == 13) {
            return "King";
        }

        return String.valueOf(value);
    }

    public String toString() {
        return String.format("%s of %s", faceValue(this.value), this.suit);
    }

    public boolean equals(Card other) {
        return other.getOrdinal() == this.getOrdinal();
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return getOrdinal();
    }
}
