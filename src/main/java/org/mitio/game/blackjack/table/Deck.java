package org.mitio.game.blackjack.table;

import org.mitio.game.blackjack.dto.*;
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
        initializeCards();
        shuffle();
    }

    public Deck(final Cards cards){
        this.cards = cards;
    }

    private void initializeCards() {
        for (CardSymbol cardSymbol : CardSymbol.values()) {

            // Add numerical card (2,3 ...10)
            for(int i=2; i<=10; i++) {
                CardValue cardValue = new CardValue(i, String.valueOf(i));
                Card card = new Card(cardSymbol, cardValue);
                cards.addCard(card);
            }

            // Add higher cards
            for (HigherCard higherCard : HigherCard.values()) {
                CardValue cardValue = new CardValue(10, higherCard.toString());
                Card card = new Card(cardSymbol, cardValue);
                cards.addCard(card);
            }
        }
    }

    public void shuffle(){
        cards.shuffle();
    }

    public Card getNext(){
        return cards.getNext();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
