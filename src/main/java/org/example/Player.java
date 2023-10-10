package org.example;

public class Player {

    //static variables: for ease of changing 'arbitrary' values.
    public static int minPlayers = 3;
    public static int maxPlayers = 5;
    String playerName;

    //Constructors:
    public Player(String playerName){this.playerName = playerName;}
    public Player(){playerName = "";}

    public String getPlayerName(){
        return this.playerName;
    }



}
