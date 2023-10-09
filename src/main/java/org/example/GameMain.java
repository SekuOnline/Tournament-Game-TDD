package org.example;

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

    public void setPlayerCount(String input){
        try{
            int newPlayerCount = Integer.parseInt(input);
            newPlayerCount = Integer.parseInt(input);
            if (3 <= newPlayerCount && newPlayerCount <= 5){
                playerCount = newPlayerCount;
            }
        }
        catch(Exception e){
            return;
        }



    }
}
