package org.mitio.game.blackjack.table;

import org.mitio.game.blackjack.dto.Card;
import org.mitio.game.blackjack.dto.Cards;

import java.util.UUID;


public class Player {

    private UUID uuid;
    private String name;
    private Cards cards = new Cards();
    private int score;


    public Player(final String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public String getName() { return name; }
    public UUID getUuid() { return uuid; }
    public int getScore() {  return score; }
    public Cards getCards() {  return cards; }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public void setScore(int score) { this.score = score; }
}
