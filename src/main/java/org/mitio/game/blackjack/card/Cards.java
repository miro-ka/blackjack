package org.mitio.game.blackjack.card;

import org.mitio.game.blackjack.card.dto.Card;

import java.util.Collections;
import java.util.LinkedList;


public class Cards {

    private LinkedList<Card> cards = new LinkedList<>();

    public Cards (final Cards cards) {
        this.cards = new LinkedList<>(cards.getCards());
    }

    public Cards() {}

    public Card getNext(){
        if(cards.isEmpty())
            return null;
        else return cards.pop();
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public String toString() {
        String s = new String();
        for(final Card card : cards) {
            s += card.toString() + "  " ;
        }
        return s;
    }

    public int getScore() {
        int score = 0;

        for(final Card card : cards) {
            score += card.getValue();
        }
        return score;
    }
}
