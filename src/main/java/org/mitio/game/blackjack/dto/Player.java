package org.mitio.game.blackjack.dto;

import java.util.UUID;


public class Player {

    public Player(final String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    private UUID uuid;
    private String name;
    private Cards cards;
    private int score;


    public String getName() { return name; }
    public UUID getUuid() { return uuid; }
    public int getScore() {  return score; }


    public void pickCard(final Deck deck) {
        //final Card newCard = deck.getNextCard();
    }

    public void setScore(int score) { this.score = score; }
}
