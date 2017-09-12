package org.mitio.game.blackjack.player;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void getName() throws Exception {

        final String playersName = "Hugga Wugga";
        final Player player = new Player(playersName);

        assertEquals(player.getName(), playersName);

    }
}