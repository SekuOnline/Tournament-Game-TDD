package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameMainUnitTest {
    @Test
    @DisplayName("UNIT TEST 001: Initialize the GameMain Class")
    void TestGameMainClass(){
        GameMain newGame = new GameMain();
        assertEquals(0, newGame.playerCount);
    }

    @Test
    @DisplayName("UNIT TEST 002: Test getting user input.")
    void TestGetUserInput(){
        String input = "1\n";

        GameMain newGame = new GameMain();

        newGame.getUserInput(new Scanner(input));

        assertEquals(0, newGame.lastInput.compareTo("1"));
    }

    @Test
    @DisplayName("UNIT TEST 003: Test assignment of user input to player count variable. ")
    void TestPlayerCountAssignment(){
        String input = Player.maxPlayers + "";

        GameMain newGame = new GameMain();
        newGame.setPlayerCount(input);
        assertEquals(Player.maxPlayers, newGame.playerCount);
    }

    @Test
    @DisplayName("UNIT TEST 004: Confirm that program does not accept inputs that cannot be converted to ints, do not commit to memory")
    void TestPlayerCountIntegersOnly(){
        String input = "CAR";
        GameMain newGame = new GameMain();
        newGame.setPlayerCount(input);
        assertEquals(0, newGame.playerCount);
    }

    @Test
    @DisplayName("UNIT TEST 005: Confirm that program only accepts valid integer values for playerCount (3-5), do not commit to memory otherwise.")
    void TestPlayerCountValidIntegers(){

        GameMain newGame = new GameMain();

        newGame.setPlayerCount((Player.minPlayers -1) + "");
        assertEquals(0, newGame.playerCount);

        newGame.setPlayerCount((Player.maxPlayers +1) + "");
        assertEquals(0, newGame.playerCount);

        newGame.setPlayerCount(Player.maxPlayers + "");
        assertEquals(Player.maxPlayers, newGame.playerCount);

    }

    @Test
    @DisplayName("UNIT TEST 006: Confirm that GameMain can store player names.")
    void TestPlayersStoredInMemory(){
        GameMain newGame = new GameMain();

        newGame.addPlayer("David");
        assertEquals("David", newGame.players[0].getPlayerName());
    }


    @Test
    @DisplayName("UNIT TEST 007: Test that valid player names can be added to the players array, otherwise are not stored.")
    void TestAddingPlayersToMemory(){
        GameMain newGame = new GameMain();
        assertEquals(1, newGame.addPlayer("David"));
        assertEquals(0, newGame.addPlayer(""));

    }

    @Test
    @DisplayName("UNIT TEST 008: Test for valid initial number of health points of the players (who all share the same amount)")
    void TestValidHealthForAllPlayers(){
        GameMain newGame = new GameMain();
        for (int i = 0; i < Player.maxPlayers; i++){
            newGame.addPlayer(i + "");
        }
        newGame.initHitPoints();
        int lastHP = -1;
        for (int i = 0; i < Player.maxPlayers; i++){
            if (i == 0){
                lastHP = newGame.players[i].getHitPoints();
                assertTrue(lastHP > 0);
            }
            else{
                assertEquals(lastHP, newGame.players[i].getHitPoints());
                lastHP = newGame.players[i].getHitPoints();

            }
        }
    }

    @Test
    @DisplayName("UNIT TEST 009: Test for valid number of each suit in the deck.")
    void TestCardSuitGeneration(){
        GameMain newGame = new GameMain();
        int sw = 0, ar = 0, so = 0, de = 0, al = 0, me = 0, ap = 0;
        for (int i = 0; i < 80; i++){
            switch (newGame.deck.getCard(i).getSuit()){
                case SW: sw++; break;
                case AR: ar++; break;
                case SO: so++; break;
                case DE: de++; break;
                case AL: al++; break;
                case ME: me++; break;
                case AP: ap++; break;
                default:
            }
        }
        assertTrue(sw == 15 && ar == 15 && so == 15 && de == 15 && al == 15 && me == 3 && ap == 2);
    }

    @Test
    @DisplayName("UNIT TEST 010: Test that poisoned cards are labeled as poisoned correctly")
    void TestPoisonedLabel(){
        GameMain newGame = new GameMain();
        List<Integer> swP = Arrays.asList(6, 7, 8, 9);
        List<Integer> arP = Arrays.asList(8,9,10,11);
        List<Integer> soP = Arrays.asList(5,6,11,12);
        List<Integer> deP = Arrays.asList(6, 7, 8, 9);
        Card card;
        for (int i = 0; i < 80; i++){
            card = newGame.deck.getCard(i);
            switch(card.getSuit()){
                case SW: assertEquals(swP.contains(card.getValue()), card.getPoisoned()); break;
                case AR: assertEquals(arP.contains(card.getValue()), card.getPoisoned()); break;
                case SO: assertEquals(soP.contains(card.getValue()), card.getPoisoned()); break;
                case DE: assertEquals(deP.contains(card.getValue()), card.getPoisoned()); break;
                default:
                    assertFalse(card.getPoisoned());
            }
        }
    }

    @Test
    @DisplayName("UNIT TEST 011: Test that cards have correct damage(injury) value.")
    void TestCorrectInjuryValue(){
        GameMain newGame = new GameMain();
        Card card;
        for(int i = 0; i < 80; i++){
            card = newGame.deck.getCard(i);
            switch(card.getSuit()){
                case ME: assertEquals(25, card.getDamage()); break;
                default:
                    if (card.getPoisoned()){assertEquals(10, card.getDamage());}
                    else{assertEquals(5, card.getDamage());}
            }
        }
    }

    @Test
    @DisplayName("UNIT TEST 012: Test that cards have changed position in the deck once shuffled.")
    void TestDeckShuffling(){
        GameMain newGame = new GameMain();
        Deck original = new Deck();
        newGame.deck.shuffle();
        boolean foundDiff = false;
        Card cardOriginal, cardShuffle;
        for(int i = 0; i < 80; i++){
            cardOriginal = original.getCard(i);
            cardShuffle = newGame.deck.getCard(i);
            System.out.println(cardOriginal.getSuit() +" "+ cardShuffle.getSuit());
            if(cardOriginal.getSuit() != cardShuffle.getSuit() || cardOriginal.getValue() != cardOriginal.getValue()){
                foundDiff = true;
                break;
            }
        }
        assertTrue(foundDiff);
    }


}