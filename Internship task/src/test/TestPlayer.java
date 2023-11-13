package test;

import cards.BoostAttackCard;
import cards.Card;
import org.junit.Test;
import player.Player;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPlayer {

    @Test
    public void testTakeDamage() {
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.takeDamage(1);
        assertEquals(9, player.getHealth());
    }

    @Test
    public void testNumberOfPlayerCardsAtTheStart() {
        Player player = new Player(20, Utility.generateCards());
        int fullDeck = player.getNumberOfCardsInDeck();
        assertNotEquals(fullDeck, 24);
        assertNotEquals(fullDeck, 26);
    }

    @Test
    public void testIfThePlayerHandIsEmptyUponInstantiation() {
        Player player = new Player(20, Utility.generateCards());
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    public void testNumberOfCardsInHand() {
        Player player = new Player(20, Utility.generateCards());  //instantiate an object of a player
        player.drawInitialCards();      // draw cards
        assertEquals(6, player.getNumberOfCardsInHand());  //check if the amount of drawn cards are actually 6 as per documentation
    }

    @Test
    public void verifyThatCardsAreRemovedFromHandAfterBeingUsed() {
        Player player = new Player(20, Utility.generateCards());
        int numOfCards = 6;   //Rules say 6, but we could've used player.getNumberOfCardsInHand()
        int amountOfDraws = 0;
        player.drawInitialCards();   //First draw
        while (player.getNumberOfCardsInHand() > 0) {   //Player uses a card, draw amount increases, number of card decreases
            player.playCard(2);
            amountOfDraws++;
            assertEquals(numOfCards - amountOfDraws, player.getNumberOfCardsInHand());
        }
    }


    @Test
    public void testDrawBeforeTurnEnds() {
        Player player = new Player(20, Utility.generateCards());
        player.drawInitialCards();
        player.drawCard();
        assertEquals("Player should only be able to have 6 cards or less in hand at once.", 6, player.getNumberOfCardsInHand());
    }

    @Test
    public void testStartingDamage() {
        Player player = new Player(20, Utility.generateCards());
        assertEquals(0, player.getDamage());
    }

    @Test
    public void testDamageReset() {
        List<Card> playerDeck = new ArrayList<Card>();
        BoostAttackCard a = new BoostAttackCard();
        playerDeck.add(a);
        Player player = new Player(20, playerDeck);
        player.drawCard();
        //Player has 0 damage as no cards have been played yet
        player.playCard(2);
        //Played a attack boost card
        assertEquals(2, player.getDamage());
        player.resetDamage();
        //Damage should be zero now, since it's reset
        assertEquals("Damage should be 0 once reset is called", 0, player.getDamage());
    }

    @Test
    public void testShuffleDeck() {
        Player player1 = new Player(20, Utility.generateCards());
        //Original deck copy
        List<Card> originalDeck = new ArrayList<>(player1.getDeck());
        //Shuffle the deck
        player1.shuffleDeck();
        //Shuffled deck
        List<Card> shuffledDeck = player1.getDeck();
        //Card order should be changed upon shuffling, so these two decks should not be equal
        assertNotEquals("Shuffling should result in a different card order.", originalDeck, shuffledDeck);
    }

    @Test
    public void testPlayerHpAmountAtStart() {
        Player player = new Player(20, Utility.generateCards());
        assertEquals(player.getHealth(), 20);
    }

    @Test
    public void verifyPlayerHPCannotExceed20(){
        Player player = new Player(25, Utility.generateCards());
        assertEquals("Expected an error message, documentation states that player's starting HP is ALWAYS 20.", 20, player.getHealth());
    }
    @Test
    public void verifyPlayerHPCannotGoBelow20(){
        Player player = new Player(15, Utility.generateCards());
        assertEquals("Expected an error message, documentation states that player's starting HP is ALWAYS 20. NOTE: Make HP a constant.", 20, player.getHealth());
    }

    @Test
    public void verifyPlayerHPCannotBeSetToNegativeValue(){
        Player player = new Player(-15, Utility.generateCards());
        assertEquals("Expected an error message, documentation states that player's starting HP is ALWAYS 20. NOTE: Make HP a constant.", 20, player.getHealth());
    }

}





