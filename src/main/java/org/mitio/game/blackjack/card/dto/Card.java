package org.mitio.game.blackjack.card.dto;


public class Card {

    private CardSymbol symbol;
    private CardValue valueSet;


    public Card(final CardSymbol symbol, final CardValue value) {
        this.symbol = symbol;
        this.valueSet = value;
    }

    public String toString() {
        return symbol.toString() + valueSet.getCode();
    }

    public int getValue() { return valueSet.getValue(); }
}
