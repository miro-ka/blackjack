package org.mitio.game.blackjack.table;

import org.junit.Test;
import org.mitio.game.blackjack.card.dto.Card;
import static org.junit.Assert.*;


public class DeckTest {


    @Test
    public void getNext() throws Exception {

        Deck deck = new Deck();
        final Card card = deck.getNext();

        assertNotEquals(card, null);
    }

}