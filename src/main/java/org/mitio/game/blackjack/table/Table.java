package org.mitio.game.blackjack.table;

import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.common.Rules;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Table {

    private HashMap<UUID, Player> players = new HashMap<>();
    private Deck deck;
    private Player dealer;


    public Table() {
         deck = new Deck();
        initialize();
    }

    public Table(final Cards cards) {
        deck = new Deck(cards);
        initialize();
    }

    public HashMap<UUID, Player> getPlayers() {  return players;  }

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

        while(dealer.getScore() <= maxUserScore && dealer.getScore() < Rules.MAX_SCORE)
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
            if(playersScore == Rules.MAX_SCORE) {
                return true;
            }

            // Check if all users don't have all over 21
            if(everybodyOverLimit && playersScore <= Rules.MAX_SCORE)
                everybodyOverLimit = false;
        }

        if(everybodyOverLimit)
            return true;

        if(deck.isEmpty()) {
            return true;
        }

        return false;
    }
}
