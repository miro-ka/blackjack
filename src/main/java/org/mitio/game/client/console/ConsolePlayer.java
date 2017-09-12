package org.mitio.game.client.console;


import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.player.PlayerInterface;
import java.util.Scanner;
import java.util.UUID;

/**
 * Console Player class - handling cards handing out from input
 */


public class ConsolePlayer implements PlayerInterface{

    private Player player;
    private final int targetScore = 21;


    public ConsolePlayer(final Player player) {
        this.player = player;

        printCommands();
        System.out.println("Welcome " + player.getName() + ", hope your luck will have no limits today. Good luck!");
        System.out.println("Your cards: " + player.getCards().toString() + " (score: " + player.getScore() + ")");

    }

    private void printCommands() {
        System.out.println("Controls:");
        System.out.println("\t Enter - get new card");
        System.out.println("\t Any other key + Enter - done/finished\n");

    }

    @Override
    public UUID getUuid() { return player.getUuid(); }

    @Override
    public String getName() { return player.getName(); }

    @Override
    public int getScore() { return player.getScore(); }

    @Override
    public Cards getCards() { return player.getCards(); }

    public boolean isDone() {

        if(getScore() > targetScore) {
            System.out.println(player.getName() + ": Ohh,..crap!");
            return true;
        }

        final Scanner keyboard = new Scanner(System.in);
        final String input = keyboard.nextLine();

        if(!input.isEmpty()) {
            System.out.println(player.getName() + ": I'm good");
            return true;
        }

        System.out.println(player.getName() + ": Give me one");
        return false;
    }

    @Override
    public void addCard(final Card card) {
        player.addCard(card);
        System.out.println("Your cards are: " + player.getCards().toString() + " (score: " + player.getScore() + ")");
    }
}
