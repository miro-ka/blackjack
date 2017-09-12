package org.mitio.game.blackjack.util;


import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;
import org.mitio.game.blackjack.card.dto.CardSymbol;
import org.mitio.game.blackjack.card.dto.CardValue;
import org.mitio.game.blackjack.card.dto.HigherCard;
import org.mitio.game.blackjack.common.Common;
import org.mitio.game.blackjack.common.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Csv parser - parses and imports playing cards from csv file
 */

public class CsvCardsParser {

    private static final Logger logger = LoggerFactory.getLogger(CsvCardsParser.class);
    private final int codeSizeMin = 2;
    private final int codeSizeMax = 3;


    public Cards parse(final File csvFile) throws IOException{

        Cards cards = new Cards();

        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                final String cardString = scanner.next().replaceAll("\\s+","");

                // Check symbol string
                if(cardString.length() < codeSizeMin || cardString.length() > codeSizeMax){
                    final String errorMsg = "Invalid card: " + cardString;
                    logger.error(errorMsg);
                    throw new IOException(errorMsg);
                }

                // Check card symbol
                final String cardSymbol = Character.toString(cardString.charAt(0));
                if(!Common.isInEnum(cardSymbol, CardSymbol.class)) {
                    final String errorMsg = "Invalid card symbol: " + cardString;
                    logger.error(errorMsg);
                    throw new IOException(errorMsg);
                }

                // Check card value
                final String valueString = Character.toString(cardString.charAt(1));
                CardValue cardValue = new CardValue();
                if(!Common.isInEnum(valueString, HigherCard.class)) {
                    final int cardNumValue = Integer.parseInt(valueString);

                    if(cardNumValue < 2 && cardNumValue > 11) {
                        final String errorMsg = "Invalid card value: " + valueString;
                        logger.error(errorMsg);
                        throw new IOException(errorMsg);
                    }

                    cardValue.setValue(cardNumValue);
                }else
                    cardValue.setValue(10);

                cardValue.setCode(valueString);
                final Card card = new Card(CardSymbol.valueOf(cardSymbol), cardValue);
                cards.addCard(card);
            }

        } catch (IOException | NumberFormatException e) {
            final String errorMsg = "Problem parsing *.csv file. Please check it and try again.";
            logger.error(errorMsg);
            throw new IOException(errorMsg);
        }

        // Check if number of cards is 52
        if(cards.size() != Rules.NUMBER_OF_CARDS){
            final String errorMsg = "Sorry man, some cards are missing or are too much. (required " +
                    Rules.NUMBER_OF_CARDS + ", you have " + cards.size() + ")";
            logger.error(errorMsg);
            throw new IOException(errorMsg);
        }

        return cards;
    }
}
