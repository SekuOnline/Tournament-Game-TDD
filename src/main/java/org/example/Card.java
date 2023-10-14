package org.example;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Card {


    //private variables
    Suit suit;
    boolean poisoned;
    int damage;
    //Value = 0 for Alchemy/Merlin/Apprentice cards
    int value;

    //Constructor
    public Card(Suit suit, int value){this.suit = suit; this.value = value;}

    //getters
    public Suit getSuit(){return suit;}
    public int getDamage(){return damage;}
    public int getValue(){return value;}
    public boolean getPoisoned(){return poisoned;}





}
