package org.example;

import java.util.Objects;
import java.util.Scanner;

public class GameMain {

    //private variables
    public String lastInput = "";
    int playerCount;
    Deck deck;
    Player[] players;

    int leaderIndex;

    //Constructor
    public GameMain(){
        playerCount = 0;
        players = new Player[Player.maxPlayers];
        deck = new Deck();

        leaderIndex = 0;
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
            else{
                System.out.println("Invalid player count.");
                System.exit(-1);
            }
        }
        catch(Exception exception){
            System.exit(-1);
        }

    }
    public void initHitPoints() {
        for(Player player : players) {
            if (player != null){
                player.setHitPoints(Player.initHitPoints);
            }
        }
    }

    void setLeaderIndex(int newLeaderIndex){
        this.leaderIndex = newLeaderIndex;
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
        if (Objects.equals(playerName, "")){
            System.out.println("Invalid player name - Player names cannot be empty.");
            return 0;
        }
        for (int i = 0; i < Player.maxPlayers; i++){

            if (Objects.equals(players[i], null)){
                players[i] = new Player(playerName);
                return 1;
            }
            else if (Objects.equals(players[i].getPlayerName(), playerName)){
                System.out.println("Invalid player name - Players cannot have the same names.");
                return 0;
            }
        }
        return 0;
    }

    public void initPlayers(Scanner input){
        System.out.println("Enter a number of players between 3-5: ");
        this.getUserInput(input);
        this.setPlayerCount(lastInput);
        for (int i = 0; i < playerCount; i++){
            System.out.println("Enter a name for player #"+(i+1)+": ");
            this.getUserInput(input);
            if (this.addPlayer(lastInput) == 0){
                i--;
            }

        }
        initHitPoints();

    }

    public void startRound(int leaderIndex, Scanner input){
        int loserIndex = leaderIndex;
        removeHands();
        deck.shuffle();
        for (int i = 0; i < playerCount; i++){
            players[i].dealHand(deck);
        }
        for (int meleeCount  = 0; meleeCount < 12; meleeCount++){
            Melee melee = new Melee(loserIndex, 0, playerCount, players);
            Player currentPlayer;
            for (int i = 0; i < playerCount; i++){

                currentPlayer = players[melee.determinePlayerNumber(i)];
                //System.out.println("Before printPlayerInfo");
                melee.printPlayerTurnInfo(currentPlayer);
                //System.out.println("After printPlayerInfo");

                //Asking for played card before checking if user has a playable card
                if (!melee.checkValidPlay(currentPlayer)){
                    //System.out.println("Inside checkvalidplay");
                    //shame damage
                    System.out.println(currentPlayer.getPlayerName() + "Cannot play any cards and takes 5 shame damage immediately.");
                    currentPlayer.takeDamage(5);
                    //checkHP
                    checkPlayersBelowZero();
                    break;
                }

                //System.out.println("After checkValidPlay");

                getUserInput(new Scanner(System.in));
                Card playedCard = currentPlayer.hand[Integer.parseInt(lastInput) - 1];

                //System.out.println("Before isvalidplay");
                while(!melee.isValidPlay(melee.determinePlayerNumber(i), playedCard , currentPlayer)){
                    System.out.println("Invalid card played: "+ playedCard.toString());
                    getUserInput(input);
                    playedCard = currentPlayer.hand[(Integer.parseInt(lastInput) - 1)];
                }
                //card added to card stack

                currentPlayer.removeCard(Integer.parseInt(lastInput) - 1);

                //If the current player is the leader, set the suit.
                if (i==0 && (playedCard.getSuit() == Suit.SW || playedCard.getSuit() == Suit.AR ||playedCard.getSuit() == Suit.SO||playedCard.getSuit() == Suit.DE)){
                    melee.currentSuit = playedCard.getSuit();
                }
                //If the player played a merlin card (Whether they're the leader or not)
                else if (playedCard.getSuit() == Suit.ME || playedCard.getSuit() == Suit.AL || playedCard.getSuit() == Suit.AP){

                    //setting suit for i=0, for merlin / app cards
                    if((playedCard.getSuit() == Suit.ME || playedCard.getSuit() == Suit.AP) && i == 0){
                        System.out.print("Enter a suit for the melee (SW, AR, SO, DE):");
                        getUserInput(input);
                        melee.currentSuit = Suit.valueOf(lastInput);
                    }

                    System.out.print("Enter a value for that card (between 1-15)");
                    getUserInput(input);
                    playedCard.value = Integer.parseInt(lastInput);
                }
                melee.cardStack[i] = playedCard;



            }
            //System.out.println("After player turns");
            //By this point, all players have played cards (if able). Shame must be added above.
            //Determine loser, deal damage, ect.
            int damage = getTotalDamage(melee.cardStack);
            Card lowestCard = null;

            for (int k = 0; k < melee.cardStack.length; k++){
                boolean notShared = true;
                for (int j = 0; j < melee.cardStack.length; j++){
                    if ((melee.cardStack[k].getValue() == melee.cardStack[j].getValue())&& k != j){
                        notShared = false;
                        break;
                    }
                }
                //if a card is the only card of a certain value
                if (notShared){
                    //compare it to the current lowest notShared card
                    if (lowestCard != null){
                        if (lowestCard.getValue() > melee.cardStack[k].getValue()){
                            lowestCard = melee.cardStack[k];
                        }
                    }
                    else{
                        lowestCard = melee.cardStack[k];
                    }
                }
            }
            //Now we have the lowest card -> if it's not equal to null then we have a loser.
            //If it is equal to null, then there is no loser
            if (lowestCard == null){
                System.out.println("There is no loser for this melee.");
            }
            else{
                System.out.println("The lowest valued non-duped card was: "+lowestCard.toString());
                System.out.println(lowestCard.player.getPlayerName()+" is the loser of this melee.\nThey take "+damage+" injury.");
                lowestCard.player.takeDamage(damage);
                checkPlayersBelowZero();

                for (int i = 0; i < playerCount; i++){
                    if (players[i] == lowestCard.player){
                        loserIndex = i;
                    }
                }
            }



        }
        //set new leader, then round ends.
        setLeaderIndex((leaderIndex+1)%playerCount);

    }
    public void checkPlayersBelowZero(){
        boolean gameEnd = false;
        for (int i = 0; i < playerCount; i++){
            if (players[i].getHitPoints() <= 0){
                System.out.println(players[i].getPlayerName() + " has fallen below zero hitpoints.");
                gameEnd = true;
            }
        }
        if (gameEnd){
            System.out.println("The game has ended. The winners are:");
            for (int i = 0; i < playerCount; i++){
                if (players[i].getHitPoints() > 0){
                    System.out.println(players[i].getPlayerName());

                }
            }
            System.out.println("Thank you for playing.");
            System.exit(0);
        }

    }

    public int getTotalDamage(Card[] cardStack){
        int damage = 0;
        for (int i = 0; i < cardStack.length; i++){
            if (cardStack[i] != null){
                damage += cardStack[i].getDamage();
            }
        }
        return damage;
    }







    //main method:
    public static void main(String[] args){
        GameMain newGame = new GameMain();
        Scanner input = new Scanner(System.in);
        newGame.initPlayers(input);
        do{newGame.startRound(0, input);}while(true);




    }


}
