package org.mitio.game.blackjack.card.dto;

public class CardValue {

    private int value;
    private String code;

    public CardValue() {}
    public CardValue(final int value,
                     final String code) {
        this.value = value;
        this.code = code;
    }


    public int getValue() { return value; }
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }
    public void setValue(int value) { this.value = value; }
}
