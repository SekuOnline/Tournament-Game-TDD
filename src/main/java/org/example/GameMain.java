package org.example;

import java.util.Objects;
import java.util.Scanner;

public class GameMain {

    public String lastInput = "";
    int playerCount;
    String[] players;
    public GameMain(){
        playerCount = 0;
        players = new String[] {};
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

    public void addPlayer(String playerName){
        if (Objects.equals(playerName, "")){return; };
        String[] tmpArray = new String[players.length + 1];

        for (int i = 0; i < players.length; i++){
            tmpArray[i] = players[i];
        }

        tmpArray[players.length] = playerName;
        players = tmpArray;
    }


}
