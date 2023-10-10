package org.example;

import java.util.Objects;
import java.util.Scanner;

public class GameMain {

    public String lastInput = "";
    int playerCount;
    Player[] players;
    public GameMain(){
        playerCount = 0;
        players = new Player[Player.maxPlayers];
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

    public int addPlayer(String playerName){
        if (Objects.equals(playerName, "")){return 0; };
        for (int i = 0; i < Player.maxPlayers; i++){
            if (Objects.equals(players[i], null)){
                players[i] = new Player(playerName);
                return 1;
            }
        }
        return 0;
    }


}
