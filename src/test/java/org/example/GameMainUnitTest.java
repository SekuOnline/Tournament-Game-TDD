package org.example;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

        assertTrue(newGame.lastInput.compareTo("1") == 0);
    }

    @Test
    @DisplayName("UNIT TEST 003: Test assignment of user input to player count variable. ")
    void TestPlayerCountAssignment(){
        String input = "1";

        GameMain newGame = new GameMain();
        newGame.setPlayerCount(input);
        assertEquals(1, newGame.playerCount);
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

        newGame.setPlayerCount("2");
        assertEquals(0, newGame.playerCount);

        newGame.setPlayerCount("6");
        assertEquals(0, newGame.playerCount);

        newGame.setPlayerCount("4");
        assertEquals(4, newGame.playerCount);

    }

    @Test
    @DisplayName("UNIT TEST 006: Confirm that GameMain can store player names.")
    void TestPlayersStoredInMemory(){
        GameMain newGame = new GameMain();

        System.out.println(newGame.addPlayer("David"));
        assertEquals("David", newGame.players[0].getPlayerName());
    }


    @Test
    @DisplayName("UNIT TEST 007: Test that valid player names can be added to the players array, otherwise are not stored.")
    void TestAddingPlayersToMemory(){
        GameMain newGame = new GameMain();
        assertEquals(1, newGame.addPlayer("David"));
        assertEquals(0, newGame.addPlayer(""));




    }


}