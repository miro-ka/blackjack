package org.mitio.game.blackjack.table;

import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.player.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Table {

    private HashMap<UUID, Player> players = new HashMap<>();
    private Deck deck;
    private Player dealer;
    private final int targetScore = 21;


    public Table() {
         deck = new Deck();
        initialize();
    }

    public Table(final Cards cards) {
        deck = new Deck(cards);
        initialize();
    }

    private void initialize() {
        dealer = addPlayer("Mr Dealer");
    }

    public Player addPlayer(final String playersName){
        Player newPlayer = new Player(playersName);

        // Always Initialize player with 2 cards
        newPlayer.addCard(deck.getNext());
        newPlayer.addCard(deck.getNext());

        players.put(newPlayer.getUuid(), newPlayer);
        return newPlayer;
    }


    public void dealersTurn(final int maxUserScore) {

        while(dealer.getScore() <= maxUserScore && dealer.getScore() < targetScore)
            dealer.addCard(deck.getNext());
    }

    public Cards getDealersCards()  {
        return dealer.getCards();
    }

    public Card getCard(final UUID userId) {

        final Card card = deck.getNext();
        players.get(userId).addCard(card);
        
        return card;
    }

    public boolean gameFinished() {

        boolean everybodyOverLimit = true;

        for(Map.Entry<UUID, Player> entry : players.entrySet()) {
            final Player player = entry.getValue();
            final int playersScore = player.getScore();

            // if player has blackjack (21)
            if(playersScore == targetScore) {
                return true;
            }

            // Check if all users don't have all over 21
            if(everybodyOverLimit && playersScore <= targetScore)
                everybodyOverLimit = false;
        }

        if(everybodyOverLimit)
            return true;

        if(deck.isEmpty()) {
            return true;
        }

        return false;
    }


    public Player getWinner() {

        int maxScore = 0;
        Player maxScorePlayer = null;

        for(Map.Entry<UUID, Player> entry : players.entrySet()) {
            final Player player = entry.getValue();
            final int score = player.getScore();

            if(score > targetScore)
                continue;

            // if player has blackjack (21)
            if(score == targetScore) {
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
