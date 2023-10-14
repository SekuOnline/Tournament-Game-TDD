package org.example;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Card {

    private static List<Integer> swP = Arrays.asList(6, 7, 8, 9);
    private static List<Integer> arP = Arrays.asList(8,9,10,11);
    private static List<Integer> soP = Arrays.asList(5,6,11,12);
    private static List<Integer> deP = Arrays.asList(6, 7, 8, 9);
    //private variables
    Suit suit;
    boolean poisoned;
    int damage;
    //Value = 0 for Alchemy/Merlin/Apprentice cards
    int value;

    //Constructor
    public Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
        switch(suit){
            case SW:
                if (swP.contains(value)){this.poisoned = true;} break;
            case AR:
                if (arP.contains(value)){this.poisoned = true;} break;
            case SO:
                if (soP.contains(value)){this.poisoned = true;} break;
            case DE:
                if (swP.contains(value)){this.poisoned = true;} break;

            default: this.poisoned = false;
        }
    }

    //getters
    public Suit getSuit(){return suit;}
    public int getDamage(){return damage;}
    public int getValue(){return value;}
    public boolean getPoisoned(){return poisoned;}





}
