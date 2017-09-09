package org.mitio.game.blackjack.utils;


import org.mitio.game.blackjack.dto.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class CsvDeckParser {

    private static final Logger _logger = LoggerFactory.getLogger(CsvDeckParser.class);

    //TODO: possibility to make this static
    public Deck parse(final File csvFile){

        Deck deck = new Deck();
        _logger.info("parsing csv deck");

        return deck;
    }
}
