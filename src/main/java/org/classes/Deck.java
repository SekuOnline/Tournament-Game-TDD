package org.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    Card[] deck;
    public Deck(){
        deck = new Card[80];
        Suit suit = Suit.AR;
        int index = 0;
        int value;
        //For every card in the deck

        //Add basic weapon cards first: 5*15=75
        for(int j = 0; j < 5; j++){
            //Each j is a basic weapon card suit
            for (int k = 1; k <= 15; k++){
                value = k;

                switch(j){

                    case 0: suit = Suit.SW; break;
                    case 1: suit = Suit.AR; break;
                    case 2: suit = Suit.SO; break;
                    case 3: suit = Suit.DE; break;
                    case 4: suit = Suit.AL; value = 0;
                }
                deck[index] = new Card(suit, value);
                index++;
            }
        }
        //All basic weapon cards created, create ME/AP cards.
        for (int i = 0; i < 5; i++){
            if (i < 3){
                suit = Suit.ME;
            }
            else{
                suit = Suit.AP;
            }

            deck[index] = new Card(suit, 0);
            index++;
        }
    }

    public Card getCard(int index){

        if (0 <= index && index <= 79){
            return deck[index];
        }
        else{
            System.out.println("Index not in range");
            return null;
        }

    }
    //Methods
    public void shuffle(){
        List<Card> cardList = Arrays.asList(this.deck);
        Collections.shuffle(cardList);
        cardList.toArray(this.deck);
    }
}
