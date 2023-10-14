package org.example;

public class Deck {

    Card[] deck;
    public Deck(){
        deck = new Card[80];
        for (int i = 0; i < 80; i++){
            deck[i] = new Card();
        }
    }

    public Card getCard(int index){
        return deck[index];
    }


}
