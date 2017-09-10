package org.mitio.game.blackjack.util;


import org.mitio.game.blackjack.dto.Cards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * Csv parser - parses and import playing cards from csv file
 */

public class CsvCardsParser {

    private static final Logger logger = LoggerFactory.getLogger(CsvCardsParser.class);

    //TODO: possibility to make this static
    public Cards parse(final File csvFile){

        Cards cards = new Cards();
        logger.info("parsing csv cards");

        return cards;
    }
}
