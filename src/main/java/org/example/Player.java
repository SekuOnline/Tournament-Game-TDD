package org.example;

public class Player {

    //static variables: for ease of changing 'arbitrary' values.
    public static int minPlayers = 3;
    public static int maxPlayers = 5;
    public static int initHitPoints = 50;

    //private variables
    String playerName;
    int hitPoints;

    //Constructors:
    public Player(String playerName){this.playerName = playerName;}
    public Player(){playerName = "";}

    //getters
    public String getPlayerName(){
        return this.playerName;
    }

    public int getHitPoints(){
        return this.hitPoints;
    };
    //setters
    public void setHitPoints(int value){
        this.hitPoints = value;
    }


    //methods





}
