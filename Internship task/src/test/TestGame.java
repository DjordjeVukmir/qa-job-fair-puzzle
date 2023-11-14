package test;

import game.Game;
import org.junit.Test;
import player.Player;
import utility.Utility;

import static org.junit.Assert.*;

public class TestGame {
    Player a = new Player(20, Utility.generateCards());
    Player b = new Player(20, Utility.generateCards());
    Game game = new Game(a, b);
    @Test
    public void testGameEndsUponHPLoss() {
        //Player b takes 20 damage,which should result in game ending
        b.takeDamage(20);
        assertTrue("The game should have ended, since player B took 20 damage.", game.getGameEnded());
    }

    @Test
    public void testPlayerCanPlayAndThenDrawInOneTurn() {

        a.drawInitialCards();
        // Draw initial cards
        //Initial hand size is 6
        // Play a card - it should be 5 now, as we've used up one of the cards
        a.playCard(2);
        // Attempt to draw again in the same turn
        a.drawCard();
        int finalHandSize = a.getNumberOfCardsInHand();
        assertEquals("Player A was able to play a card, then draw another card from the deck, which suggests multiple actions are possible during a single turn.",5, finalHandSize);

    }

    @Test
    public void testPlayerCanPlayMultipleCardsInOneTurn() {

        a.drawInitialCards();
        // Draw initial cards
        //Initial hand size is 6
        // Play a card
        a.playCard(2);
        a.playCard(2);
        assertEquals("Player A was able to play a card twice, without the turn ending.",5, a.getNumberOfCardsInHand());

    }

    @Test
    public void testGameStart(){
        assertFalse("Game isn't running?", game.getGameEnded()); //Message is there in case the test fails
    }


}




