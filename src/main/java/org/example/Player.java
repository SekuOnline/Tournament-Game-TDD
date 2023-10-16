package org.example;

public class Player {

    //static variables: for ease of changing 'arbitrary' values.
    public static int minPlayers = 3;
    public static int maxPlayers = 5;
    public static int initHitPoints = 50;

    //private variables
    String playerName;
    int hitPoints;
    Card[] hand;

    //Constructors:
    public Player(String playerName){
        this.playerName = playerName;
        this.hand = new Card[12];
    }


    //getters
    public String getPlayerName(){
        return this.playerName;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }
    //setters
    public void setHitPoints(int value){
        this.hitPoints = value;
    }


    //methods
    public void dealHand(Deck deck){
        int k = 0;
        for (int i = 0; i < 12; i++){
            //uses index = k, so we don't have to start from the beginning each time we add a card.
            for(int index = k; index < 80; index++){
                if(!deck.getCard(index).getTaken()){
                    deck.getCard(index).setTaken(true);
                    this.hand[i] = deck.getCard(index);
                    deck.getCard(index).setPlayer(this);
                    k++;
                    //System.out.println(this.getPlayerName() + ": "+this.hand[i].getSuit() + " " + this.hand[i].getValue());
                    break;
                }
            }
        }
    }

    public boolean removeCard(int index){
        if (hand[index] == null){
            return false;
        }
        hand[index] = null;
        return true;
    }

    public void printHand(){
        for (int i = 0; i < hand.length; i++){
            if (hand[i] != null){
                System.out.println((i+1) + ": "+hand[i].toString());
            }
        }
    }

    public void takeDamage(int damageTaken){
        this.hitPoints-=damageTaken;
    }


}
