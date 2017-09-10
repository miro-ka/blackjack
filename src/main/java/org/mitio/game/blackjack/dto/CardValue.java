package org.mitio.game.blackjack.dto;

public class CardValue {

    private int value;
    private String code;
    private String name;

    public CardValue(final int value,
                     final String code,
                     final String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

}
