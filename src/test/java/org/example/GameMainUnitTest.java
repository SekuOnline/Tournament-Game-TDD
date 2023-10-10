package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


}