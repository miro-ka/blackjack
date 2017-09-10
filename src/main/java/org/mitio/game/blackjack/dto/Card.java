package org.mitio.game.blackjack.dto;



public class Card {

    private CardSymbol symbol;
    private CardValue value;


    public Card(final CardSymbol symbol, final CardValue value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String toString() {
        return symbol.toString() + value.getCode();
    }
}
