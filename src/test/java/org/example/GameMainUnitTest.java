package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
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
}