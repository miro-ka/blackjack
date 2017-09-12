package org.mitio.game.blackjack.player;

import org.mitio.game.blackjack.card.Cards;
import org.mitio.game.blackjack.card.dto.Card;

import java.util.UUID;


public interface PlayerInterface
{
    UUID getUuid();
    String getName();
    int getScore();
    Cards getCards();
    void addCard(final Card card);
}