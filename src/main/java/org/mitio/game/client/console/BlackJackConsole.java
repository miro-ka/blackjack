package org.mitio.game.client.console;

import org.apache.commons.cli.*;
import org.mitio.game.blackjack.BlackJack;
import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.util.CsvCardsParser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * Console application game implementing BlackJack game
 */


public class BlackJackConsole {

    public static void main(String[] args) throws ParseException {

        printWelcomeScreen();

        // Parse commands
        Options options = buildCommandLineOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(options, args);

        if (cmdLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Black Jack", options);
            return;
        }

        Cards cards = null;
        if(cmdLine.hasOption("deck")){
            File deckFile = new File(cmdLine.getOptionValue("deck"));
            CsvCardsParser csvCardsParser = new CsvCardsParser();
            try {
                cards = csvCardsParser.parse(deckFile);
            }catch(IOException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }


        boolean playGame = true;
        final Scanner keyboard = new Scanner(System.in);

        do{

            final BlackJack blackJack = (cards == null) ? new BlackJack() : new BlackJack(cards);
            final ConsolePlayer playerSam = new ConsolePlayer(blackJack.addPlayer("Sam"));

            while(!playerSam.isDone()) {
                if(blackJack.gameFinished())
                    break;
                playerSam.addCard(blackJack.getCard(playerSam.getUuid()));
            }

            if(!blackJack.gameFinished())
                blackJack.dealersTurn(playerSam.getScore());
            final Cards dealersCards = blackJack.getDealersCards();
            System.out.println("\nDealers cards: " + dealersCards.toString() + "(score: " + dealersCards.getScore() + ")" );

            writeScore(blackJack.getWinner());


            System.out.println("\n\nDo you dare to play one more time? (y/n)");
            if(keyboard.nextLine().equals("n")) {
                System.out.println("Alright, thanks for playing,..wish you all the best.");
                playGame = false;
            }

        }while(playGame);
    }


    private static void writeScore(final Player winner) {
        System.out.println("\n==============★★★★★★THE WINNER IS★★★★★==============");
        System.out.println(  "==============★★★★★★★★★★★★★★★★★★★★★★★★==============");
        if(winner != null)
            System.out.println("The Winner is: " + winner.getName() + ", with score: " + winner.getScore() + "!");
        else
            System.out.println("Nobody wins!");
    }


    private static void printWelcomeScreen(){
        final String welcomeMsg =
                "__________.__                 __          ____.              __  " +
                "\n\\______   \\  | _____    ____ |  | __     |    |____    ____ |  | __" +
                "\n |    |  _/  | \\__  \\ _/ ___\\|  |/ /     |    \\__  \\ _/ ___\\|  |/ /" +
                "\n |    |   \\  |__/ __ \\  \\___ |    <  /\\__|    |/ __ \\  \\___|    < " +
                "\n |______  /____(____  /\\___  >__|_ \\ \\________(____  /\\___  >__|_ \\" +
                "\n        \\/          \\/     \\/     \\/               \\/     \\/     \\/ \n\n";

        System.out.println(welcomeMsg);
    }


    private static Options buildCommandLineOptions(){

        final Option helpOption = Option.builder("h")
                .longOpt("help")
                .required(false)
                .desc("shows help")
                .build();

        final Option deckOption = Option.builder("d")
                .longOpt("deck")
                .numberOfArgs(1)
                .required(false)
                .type(File.class    )
                .desc("csv file containing deck of cards")
                .build();

        Options options = new Options();
        options.addOption(helpOption);
        options.addOption(deckOption);

        return options;
    }
}
