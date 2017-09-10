package org.mitio.game;

import org.apache.commons.cli.*;
import org.mitio.game.blackjack.BlackJack;
import org.mitio.game.blackjack.dto.Cards;
import org.mitio.game.blackjack.table.Player;
import org.mitio.game.blackjack.util.CsvCardsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;


/**
 * TODO: Add small description about the class
 */


public class BlackJackApp {

    private static final Logger logger = LoggerFactory.getLogger(BlackJackApp.class);


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


        BlackJack blackJack = new BlackJack();
        Cards cards;
        if(cmdLine.hasOption("deck")){
            File deckFile = new File(cmdLine.getOptionValue("deck"));
            logger.info("loading black jack with deck file: " + deckFile.getName());
            CsvCardsParser csvCardsParser = new CsvCardsParser();
            cards = csvCardsParser.parse(deckFile);
            blackJack.setCards(cards);
        }

        // Add user
        final Player player = blackJack.addPlayer("Sam");
        System.out.println("Welcome " + player.getName() + ", hope your luck will have no limits today.");

        while(!blackJack.gameFinished()){

            System.out.println("Your cards are: " + player.getCards().toString() + "  (Press space for new card or d for done)");


            //player1Uuid.takeTurn

            logger.info("running game cycle");
        }

        // TODO: blackJack.getScore();

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
