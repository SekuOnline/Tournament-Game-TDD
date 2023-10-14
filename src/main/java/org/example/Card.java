package org.example;

public class Card {

    //private variables
    Suit suit;
    boolean poisoned;
    int damage;
            //Value = 0 for Alchemy/Merlin/Apprentice cards
    int value;

    //Constructor

    public Card(){suit = Suit.SW;}

    //getters
    public Suit getSuit(){return suit;}
    public int getDamage(){return damage;}
    public int getValue(){return value;}





}
