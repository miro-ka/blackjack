package org.mitio.game.blackjack;

import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.table.Score;
import org.mitio.game.blackjack.table.Table;
import java.util.UUID;


/**
 * Main BlackJack class dealing with game objects and game flow
 */


public class BlackJack {

    private Table table;
    private Score score = new Score();

    public BlackJack(){
        table = new Table();
    }
    public BlackJack(final Cards cards) {
        table = new Table(cards);
    }

    public Player addPlayer(final String playersName){
        final Player player = table.addPlayer(playersName);
        return new Player(player);
    }

    public boolean gameFinished() {
        return table.gameFinished();
    }

    public Card getCard(final UUID userId) {
        return table.getCard(userId);
    }

    public void dealersTurn(final int maxScore) {
        table.dealersTurn(maxScore);
    }

    public Cards getDealersCards() {
        return table.getDealersCards();
    }

    public Player getWinner() {
        return table.getWinner();
    }
}
