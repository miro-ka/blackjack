package org.mitio.game;

import org.apache.commons.cli.*;
import org.mitio.game.blackjack.BlackJack;
import org.mitio.game.blackjack.dto.Cards;
import org.mitio.game.blackjack.dto.Deck;
import org.mitio.game.blackjack.utils.CsvDeckParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;


public class BlackJackApp {

    private static final Logger _logger = LoggerFactory.getLogger(BlackJackApp.class);


    public static void main(String[] args) throws ParseException {


        Options options = buildCommandLineOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(options, args);

        Deck deck = null;
        if (cmdLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Black Jack", options);
            return;
        }else if(cmdLine.hasOption("deck")){
            File deckFile = new File(cmdLine.getOptionValue("deck"));
            _logger.info("loading black jack with deck file: " + deckFile.getName());
            CsvDeckParser csvDeckParser = new CsvDeckParser();
            deck = csvDeckParser.parse(deckFile);
        }

        printWelcomeScreen();
        BlackJack blackJack = new BlackJack(deck);

    }

    private static void printWelcomeScreen(){
        final String welcomeMsg =
                "__________.__                 __          ____.              __  " +
                "\n\\______   \\  | _____    ____ |  | __     |    |____    ____ |  | __" +
                "\n |    |  _/  | \\__  \\ _/ ___\\|  |/ /     |    \\__  \\ _/ ___\\|  |/ /" +
                "\n |    |   \\  |__/ __ \\  \\___ |    <  /\\__|    |/ __ \\  \\___|    < " +
                "\n |______  /____(____  /\\___  >__|_ \\ \\________(____  /\\___  >__|_ \\" +
                "\n        \\/          \\/     \\/     \\/               \\/     \\/     \\/   by miti0\n\n";

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
