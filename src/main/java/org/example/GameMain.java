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

            if (Player.minPlayers <= newPlayerCount && newPlayerCount <= Player.maxPlayers){
                playerCount = newPlayerCount;
            }
        }
        catch(Exception e){
            System.out.println("Error: Input was not an integer.");
        }

    }

    public int addPlayer(String playerName){
        if (Objects.equals(playerName, "")){return 0; }
        for (int i = 0; i < Player.maxPlayers; i++){
            if (Objects.equals(players[i], null)){
                players[i] = new Player(playerName);
                return 1;
            }
        }
        return 0;
    }

    public void initHitPoints() {
        for(Player player : players) {
            if (player != null){
                player.setHitPoints(Player.initHitPoints);
            }
        }
    }


}
