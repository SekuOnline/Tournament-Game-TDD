package org.example;

import static java.lang.Math.abs;

public class Melee {
    int leaderIndex;
    int meleeNumber;
    int playerCount;
    Player[] players;
    Card[] cardStack;

    Suit currentSuit;
    public Melee(int leaderIndex, int meleeNumber, int playerCount, Player[] players){
        this.leaderIndex = leaderIndex;
        this.meleeNumber = meleeNumber;
        cardStack = new Card[playerCount];
        this.playerCount = playerCount;
        this.players = players;
        this.currentSuit = Suit.NONE;

    }

    public int determinePlayerNumber(int playerIndex){
        return abs((leaderIndex - playerIndex) % playerCount);
    }

    public boolean isValidPlay(int playerNumber, Card card, Player player){
        //case of leader selecting suit
        if (playerNumber == leaderIndex){
            if (card.getSuit() == Suit.AL){
                //check if all cards in hand are gone
                int cardsInHand = 0;
                for (int i = 0; i < player.hand.length; i++){
                    if (player.hand[i] != null){
                        cardsInHand++;
                    }
                }
                return cardsInHand == 1;
            }
        }
        return false;
    }

}
