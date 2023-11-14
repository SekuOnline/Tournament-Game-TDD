package org.classes;

import static java.lang.Math.abs;

public class Melee {
    public Suit currentSuit;
    int leaderIndex;
    int meleeNumber;
    int playerCount;
    Player[] players;
    public Card[] cardStack;

    public int loserIndex;

    public Melee(){};
    public Melee(int leaderIndex, int meleeNumber, int playerCount, Player[] players){
        this.leaderIndex = leaderIndex;
        this.meleeNumber = meleeNumber;
        cardStack = new Card[playerCount];
        this.playerCount = playerCount;
        this.players = players;
        this.currentSuit = Suit.NONE;

    }

    public int determinePlayerNumber(int playerIndex){
        return abs((leaderIndex + playerIndex) % playerCount);
    }

    //Checks if a played card is valid
    public boolean isValidPlay(int playerNumber, Card card, Player player){
        //case of leader selecting suit
        //System.out.println(currentSuit + " " + card.getSuit());
        if (card != null){
            if (card.getSuit() == Suit.AL) {

                if (checkAlchemy(player)) {
                    return true;
                } else {
                    System.out.println("Alchemy card is invalid: Another valid play exists.");
                    return false;
                }
            }
            else if (playerNumber == leaderIndex){
                return true;
            }
            //If the player is not the leader, then the card must match the suit (when applicable)
            else if (currentSuit != Suit.NONE){
                //Matching suit, merlin, or apprentice are always okay
                return card.suit == currentSuit || card.suit == Suit.ME || card.suit == Suit.AP;
                //if the card is a basic card of a different suit return false.


                //
            }
            //if the current suit IS none (from alchemy card only), then anything is valid.
            return true;
        }

        return false;

    }

    public void printPlayerTurnInfo(Player player){
        System.out.println("------------------------------------------");
        System.out.print(player.getPlayerName() +"'s turn");
        System.out.println(" (HP: "+player.getHitPoints()+")");
        System.out.println("Select a card to play by index (Current suit: ["+currentSuit+"])");
        System.out.println("Current cards in play: ");
        for (Card card : cardStack) {
            if (card != null) {
                System.out.println(card);
            }
        }
        System.out.println("------------------------------------------");
        player.printHand();
        System.out.println("------------------------------------------");


    }

    //Checks if a valid playable card exists within the players hand.
    public boolean checkValidPlay(Player player){

        for(int i = 0; i < player.hand.length; i++){
            if (player.hand[i] != null){
                //System.out.println("Item = currSuit");
                //System.out.println(player.hand[i].getSuit() == currentSuit);
                Suit cardSuit = player.hand[i].getSuit();
                if (cardSuit == currentSuit || cardSuit == Suit.ME || cardSuit == Suit.AP || (currentSuit == Suit.NONE && (cardSuit == Suit.SW || cardSuit == Suit.DE || cardSuit == Suit.AR || cardSuit == Suit.SO))){
                    //System.out.println("Player has valid play.");
                    System.out.println("Valid play, card: "+(i+1));
                    return true;
                }
                else if (cardSuit == Suit.AL && checkAlchemy(player)){
                    System.out.println("Valid play, AL card");
                    return true;
                }



            }
        }

        return false;
    }

    public boolean checkAlchemy(Player player){


        for (int i = 0; i < player.hand.length; i++){

            if (player.hand[i] != null){

                Suit cardSuit = player.hand[i].getSuit();
                if (player.hand[i].getSuit() != Suit.AL) {
                    if (cardSuit == currentSuit || cardSuit == Suit.ME || cardSuit == Suit.AP || (currentSuit == Suit.NONE && (cardSuit == Suit.SW || cardSuit == Suit.DE || cardSuit == Suit.AR || cardSuit == Suit.SO))) {
                        //System.out.println("You cannot play an alchemy card, as you have another playable card: "+player.hand[i].toString());
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
