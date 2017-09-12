package org.mitio.game.blackjack.table;

import org.junit.Test;
import org.mitio.game.blackjack.player.Player;
import static org.junit.Assert.*;


public class TableTest {

    @Test
    public void addPlayer() throws Exception {

        Table table = new Table();
        final String playersName = "Mr Wagga";
        final Player player = table.addPlayer(playersName);

        assertEquals(player.getName(), playersName);
    }

    @Test
    public void initialPlayersCards() throws Exception {
        Table table = new Table();
        final String playersName = "Mr Wagga";
        final Player player = table.addPlayer(playersName);

        assertEquals(player.getCards().size(), 2);
    }


}