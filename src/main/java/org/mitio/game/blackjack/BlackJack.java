package org.mitio.game.blackjack;

import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.common.Rules;
import org.mitio.game.blackjack.table.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Main BlackJack class dealing with game objects and game flow
 */


public class BlackJack {

    private Table table;

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

        final HashMap<UUID, Player> players = table.getPlayers();

        int maxScore = 0;
        Player maxScorePlayer = null;

        for(Map.Entry<UUID, Player> entry : players.entrySet()) {
            final Player player = entry.getValue();
            final int score = player.getScore();

            if(score > Rules.MAX_SCORE)
                continue;

            // if player has blackjack (21)
            if(score == Rules.MAX_SCORE) {
                return player;
            }

            if(maxScore < score){
                maxScore = score;
                maxScorePlayer = player;
            }
        }

        // If nobody has full pot, the winner the one with the highest score
        return maxScorePlayer;
    }
}
