package org.example;

import java.util.Objects;
import java.util.Scanner;

public class GameMain {

    //private variables
    public String lastInput = "";
    int playerCount;
    Deck deck;
    Player[] players;

    //Constructor
    public GameMain(){
        playerCount = 0;
        players = new Player[Player.maxPlayers];
        deck = new Deck();
    }

    //getters
    public void getUserInput(Scanner input) {
        lastInput = input.nextLine();
    }

    //setters
    public void setPlayerCount(String input){
        try{
            int newPlayerCount = Integer.parseInt(input);

            if (Player.minPlayers <= newPlayerCount && newPlayerCount <= Player.maxPlayers){
                playerCount = newPlayerCount;
            }
        }
        catch(Exception ignored){
        }

    }
    public void initHitPoints() {
        for(Player player : players) {
            if (player != null){
                player.setHitPoints(Player.initHitPoints);
            }
        }
    }

    //methods
    public void removeHands(){
        for(int k = 0; k < this.playerCount; k++){
            for(int i = 0; i < this.players[k].hand.length; i++){
                if (players[k].hand[i] != null){
                    players[k].hand[i].setTaken(false);

                }
                players[k].hand[i] = null;
            }
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

    //main method:
    public static void main(String[] args){

    }


}
