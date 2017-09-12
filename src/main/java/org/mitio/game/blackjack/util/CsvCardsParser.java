package org.mitio.game.blackjack.util;


import org.mitio.game.blackjack.card.Cards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * Csv parser - parses and import playing cards from csv file
 */

public class CsvCardsParser {

    private static final Logger logger = LoggerFactory.getLogger(CsvCardsParser.class);


    public Cards parse(final File csvFile){

        Cards cards = new Cards();
        logger.info("parsing csv cards");


        // Check if number of cards is 52

        // Check if import has all cards

        return cards;
    }
}
