package org.mitio.game.client.console;

import org.apache.commons.cli.*;
import org.mitio.game.blackjack.BlackJack;
import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.player.Player;
import org.mitio.game.blackjack.util.CsvCardsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;


/**
 * Console application game implementing BlackJack game
 */


public class BlackJackConsole {

    private static final Logger logger = LoggerFactory.getLogger(BlackJackConsole.class);


    public static void main(String[] args) throws ParseException {

        printWelcomeScreen();
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
            logger.info("loading black jack with deck file: " + deckFile.getName());
            CsvCardsParser csvCardsParser = new CsvCardsParser();
            cards = csvCardsParser.parse(deckFile);
        }


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
        System.out.println("Dealers cards: " + dealersCards.toString() + "(score: " + dealersCards.getScore() + ")" );

        System.out.println("\n--------------");
        System.out.println("--------------");
        final Player winner = blackJack.getWinner();
        if(winner != null)
            System.out.println("The Winner is: " + winner.getName() + ", with score: " + winner.getScore() + "!");
        else
            System.out.println("Nobody wins!");


        // TODO do you want to continue?
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
