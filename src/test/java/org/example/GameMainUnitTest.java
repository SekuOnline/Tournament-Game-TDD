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
}