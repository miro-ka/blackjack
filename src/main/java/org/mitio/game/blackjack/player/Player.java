package org.mitio.game.blackjack.player;

import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.card.Cards;
import java.util.UUID;


public class Player implements PlayerInterface {

    private UUID uuid;
    private String name;
    private Cards cards = new Cards();
    private int score;


    public Player(final String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public Player(final Player player){
        this.uuid = player.uuid;
        this.name = player.name;
        this.cards = new Cards(player.cards);
        this.score = player.score;
    }

    @Override
    public String getName() { return name; }

    @Override
    public UUID getUuid() { return uuid; }

    @Override
    public int getScore() {  return cards.getScore(); }

    @Override
    public Cards getCards() {  return cards; }

    @Override
    public void addCard(final Card card) {
        cards.addCard(card);
        score = cards.getScore();
    }
}
