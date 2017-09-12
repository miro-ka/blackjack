package org.mitio.game.blackjack.util;

import org.junit.Test;
import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.common.Rules;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CsvCardsParserTest {

    @Test
    public void parseValidCsvFile() throws Exception {

        final String csvFile = "/home/miro/dev/blackjack/deck.csv";
        CsvCardsParser csvCardsParser = new CsvCardsParser();
        Cards cards = csvCardsParser.parse(new File(csvFile));
        assertEquals(cards.size(), Rules.NUMBER_OF_CARDS);
    }

    @Test(expected = IOException.class)
    public void parseInvalidCsvFile() throws Exception {

        final String csvData = "some_invalid_huhahasize.csv";
        CsvCardsParser csvCardsParser = new CsvCardsParser();
        Cards cards = csvCardsParser.parse(new File(csvData));
    }
}