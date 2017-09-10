package org.mitio.game.blackjack.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deck of cards
 */

public class Deck{

    private static final Logger logger = LoggerFactory.getLogger(Deck.class);
    private Cards cards;

    public Deck(){
        cards = new Cards();
        shuffle();
    }

    public Deck(final Cards cards){
        this.cards = cards;
    }

    public void shuffle(){
        // TODO shuffle
        // Collections.shuffle(cards);
    }

    public Card getNext(){
        return cards.getNext();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
