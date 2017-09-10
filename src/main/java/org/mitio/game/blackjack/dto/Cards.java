package org.mitio.game.blackjack.dto;

import java.util.Collections;
import java.util.LinkedList;


public class Cards {

    private LinkedList<Card> cards = new LinkedList<>();


    public Card getNext(){
        if(cards.isEmpty())
            return null;
        else return cards.pop();
    }

    public void addCard(final Card card) {
        cards.push(card);
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
}
