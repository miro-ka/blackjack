package org.mitio.game.blackjack;

import org.mitio.game.blackjack.dto.*;
import org.mitio.game.blackjack.table.Deck;
import org.mitio.game.blackjack.table.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;


/**
 * Main BlackJack class dealing with game objects and game flow
 */


public class BlackJack {

    private static final Logger logger = LoggerFactory.getLogger(BlackJack.class);
    private Dealer dealer;
    private HashMap<UUID, Player> players = new HashMap<>();
    private GameState gameState = GameState.RUNNING;
    private Deck deck = new Deck();
    private final String dealersName = "Mr. Dealer";


    public BlackJack(){
        dealer = new Dealer(dealersName);
        dealer.addCard(deck.getNext());
        dealer.addCard(deck.getNext());
    }

    public Player addPlayer(final String playersName){
        Player newPlayer = new Player(playersName);
        newPlayer.addCard(deck.getNext());
        newPlayer.addCard(deck.getNext());

        players.put(newPlayer.getUuid(), newPlayer);

        return newPlayer;
    }

    public void setCards(final Cards cards){
        deck = new Deck(cards);
    }

    public Card handOutCardToUser(final UUID uuid) {
        final Card card = deck.getNext();
        players.get(uuid).addCard(card);
        return card;
    }

    public boolean gameFinished() {

        boolean everybodyOverLimit = true;

        for(Map.Entry<UUID, Player> entry : players.entrySet()) {
            final Player player = entry.getValue();

            final int playersScore = player.getScore();

            // if player has blackjack (21)
            if(playersScore == 21) {
                gameState = GameState.WE_HAVE_WINNER;
                return true;
            }

            // Check if all users don't have all over 21
            if(everybodyOverLimit && playersScore <= 21)
                everybodyOverLimit = false;
        }

        if(everybodyOverLimit)
            return true;

        if(deck.isEmpty()) {
            gameState = GameState.NO_MORE_CARDS;
            return true;
        }

        return false;
    }










    public void shuffleDeck(){
        deck.shuffle();
    }


    public GameState getGameState() { return gameState; }
    //public void getCard()


    public GameState evaluateGame() {
        logger.info("Evaluating,...");

        // If not more cards, game is finished

        //TODO: evaluate game
        return GameState.RUNNING;
    }




}
