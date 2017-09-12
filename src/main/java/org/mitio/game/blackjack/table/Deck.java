package org.mitio.game.blackjack.table;

import org.mitio.game.blackjack.card.*;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.card.dto.CardSymbol;
import org.mitio.game.blackjack.card.dto.CardValue;
import org.mitio.game.blackjack.card.dto.HigherCard;


/**
 * Deck of cards
 */

public class Deck{

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
