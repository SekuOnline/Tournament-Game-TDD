package org.example;

import java.io.PrintWriter;
import java.util.Scanner;

public class GameMain {

    public String lastInput = "";
    int playerCount;
    public GameMain(){
        playerCount = 0;
    }
    public static void main(String[] args){

    }


    public void getUserInput(Scanner input) {
        lastInput = input.nextLine();
    }
}
