package org.mitio.game.blackjack.dto;

import java.util.ArrayDeque;


public class Cards {

    private ArrayDeque<Card> cards = new ArrayDeque<Card>();


    public Card getNext(){
        if(cards.isEmpty())
            return null;
        else return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
