/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package solitaire;

import java.util.*;

public class App {

    public final static Card club1 = new Card(Card.Suit.Clubs, 1);
    public final static Card club2 = new Card(Card.Suit.Clubs, 2);
    public final static Card club3 = new Card(Card.Suit.Clubs, 3);
    public final static Card club4 = new Card(Card.Suit.Clubs, 4);
    public final static Card club5 = new Card(Card.Suit.Clubs, 5);
    public final static Card club6 = new Card(Card.Suit.Clubs, 6);
    public final static Card club7 = new Card(Card.Suit.Clubs, 7);
    public final static Card club8 = new Card(Card.Suit.Clubs, 8);
    public final static Card club9 = new Card(Card.Suit.Clubs, 9);
    public final static Card club10 = new Card(Card.Suit.Clubs, 10);
    public final static Card club11 = new Card(Card.Suit.Clubs, 11);
    public final static Card club12 = new Card(Card.Suit.Clubs, 12);
    public final static Card club13 = new Card(Card.Suit.Clubs, 13);

    public final static Card diamond1 = new Card(Card.Suit.Diamonds, 1);
    public final static Card diamond2 = new Card(Card.Suit.Diamonds, 2);
    public final static Card diamond3 = new Card(Card.Suit.Diamonds, 3);
    public final static Card diamond4 = new Card(Card.Suit.Diamonds, 4);
    public final static Card diamond5 = new Card(Card.Suit.Diamonds, 5);
    public final static Card diamond6 = new Card(Card.Suit.Diamonds, 6);
    public final static Card diamond7 = new Card(Card.Suit.Diamonds, 7);
    public final static Card diamond8 = new Card(Card.Suit.Diamonds, 8);
    public final static Card diamond9 = new Card(Card.Suit.Diamonds, 9);
    public final static Card diamond10 = new Card(Card.Suit.Diamonds, 10);
    public final static Card diamond11 = new Card(Card.Suit.Diamonds, 11);
    public final static Card diamond12 = new Card(Card.Suit.Diamonds, 12);
    public final static Card diamond13 = new Card(Card.Suit.Diamonds, 13);

    public final static Card heart1 = new Card(Card.Suit.Hearts, 1);
    public final static Card heart2 = new Card(Card.Suit.Hearts, 2);
    public final static Card heart3 = new Card(Card.Suit.Hearts, 3);
    public final static Card heart4 = new Card(Card.Suit.Hearts, 4);
    public final static Card heart5 = new Card(Card.Suit.Hearts, 5);
    public final static Card heart6 = new Card(Card.Suit.Hearts, 6);
    public final static Card heart7 = new Card(Card.Suit.Hearts, 7);
    public final static Card heart8 = new Card(Card.Suit.Hearts, 8);
    public final static Card heart9 = new Card(Card.Suit.Hearts, 9);
    public final static Card heart10 = new Card(Card.Suit.Hearts, 10);
    public final static Card heart11 = new Card(Card.Suit.Hearts, 11);
    public final static Card heart12 = new Card(Card.Suit.Hearts, 12);
    public final static Card heart13 = new Card(Card.Suit.Hearts, 13);

    public final static Card spade1 = new Card(Card.Suit.Spades, 1);
    public final static Card spade2 = new Card(Card.Suit.Spades, 2);
    public final static Card spade3 = new Card(Card.Suit.Spades, 3);
    public final static Card spade4 = new Card(Card.Suit.Spades, 4);
    public final static Card spade5 = new Card(Card.Suit.Spades, 5);
    public final static Card spade6 = new Card(Card.Suit.Spades, 6);
    public final static Card spade7 = new Card(Card.Suit.Spades, 7);
    public final static Card spade8 = new Card(Card.Suit.Spades, 8);
    public final static Card spade9 = new Card(Card.Suit.Spades, 9);
    public final static Card spade10 = new Card(Card.Suit.Spades, 10);
    public final static Card spade11 = new Card(Card.Suit.Spades, 11);
    public final static Card spade12 = new Card(Card.Suit.Spades, 12);
    public final static Card spade13 = new Card(Card.Suit.Spades, 13);

    public static void main(String[] args) {
        long start_time = java.lang.System.currentTimeMillis();

        HashSet<Match> examined_matches = new HashSet<>();
        PriorityQueue<Match> queue = new PriorityQueue<>(new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return o2.size() - o1.size();
            }
        });

        Match start = new Match(new Top(), App.getTheBoard(), App.getTheDeck(), 0);
        queue.add(start);
        examined_matches.add(start);
        int biggest_size = 0;

        while (!queue.isEmpty()) {
            Match next_match = queue.poll();
            if (next_match.size() > biggest_size) {
                System.out.println(next_match.size());
                biggest_size = next_match.size();
            }

            //check if done
            if (next_match.size() == 15) {
                long time_taken = (java.lang.System.currentTimeMillis() - start_time)/1000;
                System.out.println("Found solution in " + time_taken);
                System.out.println(next_match);
                System.exit(0);
            }

            List<Match> future_matches = next_match.moves();
            for (Match future_match : future_matches) {
                if (!examined_matches.contains(future_match)) {
                    queue.add(future_match);
                    examined_matches.add(future_match);
                }
            }
        }
        long time_taken = (java.lang.System.currentTimeMillis() - start_time)/1000;
        System.out.println("No solution found after " + time_taken);
    }

    private static Board getTheBoard() {
        Card[][] columns = new Card[10][];
        columns[0] = new Card[]{spade4, heart7, heart3, heart7};
        columns[1] = new Card[]{diamond3, heart3, heart2, spade10};
        columns[2] = new Card[]{diamond8, diamond1, heart11, spade7};
        columns[3] = new Card[]{spade8, club12, diamond1, spade2};
        columns[4] = new Card[]{club9, club7, heart12, club5};
        columns[5] = new Card[]{club8, spade7, heart1, diamond11};
        columns[6] = new Card[]{diamond2, spade11, diamond10, club1};
        columns[7] = new Card[]{diamond13, club11, spade6, diamond12};
        columns[8] = new Card[]{heart6, club5, heart5, club6};
        columns[9] = new Card[]{club9, spade5, club12, heart4};

        return new Board(columns);
    }

    private static Deck getTheDeck() {
        ArrayList<Card> c = new ArrayList<Card>();
        c.add(diamond2);
        c.add(heart12);
        c.add(spade12);
        c.add(spade1);
        c.add(club7);
        c.add(diamond9);
        c.add(heart11);
        c.add(diamond13);
        c.add(diamond4);
        c.add(heart10);
        c.add(heart1);
        c.add(club10);
        c.add(club13);
        c.add(heart8);
        c.add(spade8);
        c.add(spade5);
        c.add(diamond5);
        c.add(spade1);
        c.add(diamond8);
        c.add(spade12);
        c.add(spade9);
        c.add(heart9);
        c.add(club2);
        c.add(club1);
        c.add(club2);
        c.add(spade4);
        c.add(spade2);
        c.add(club12);
        c.add(diamond12);
        c.add(club3);
        c.add(diamond7);
        c.add(diamond6);
        c.add(heart13);
        c.add(club4);
        c.add(spade11);
        c.add(diamond4);
        c.add(diamond6);
        c.add(heart13);
        c.add(club8);
        c.add(heart10);
        c.add(diamond10);
        c.add(club10);
        c.add(spade3);
        c.add(spade10);
        c.add(club3);
        c.add(heart8);
        c.add(heart6);
        c.add(heart5);
        c.add(spade13);
        c.add(spade6);
        c.add(club13);
        c.add(spade9);
        c.add(club4);
        c.add(heart2);
        c.add(diamond11);
        c.add(diamond5);
        c.add(spade13);
        c.add(diamond7);
        c.add(heart4);
        c.add(diamond9);
        c.add(heart9);
        c.add(diamond3);
        c.add(spade3);
        c.add(club6);

        return new Deck(c.toArray(new Card[]{}), 0);
    }
}
