package org.example;

import java.util.*;



public class Card {

    private static final List<Integer> swP = Arrays.asList(6, 7, 8, 9);
    private static final List<Integer> arP = Arrays.asList(8,9,10,11);
    private static final List<Integer> soP = Arrays.asList(5,6,11,12);
    private static final List<Integer> deP = Arrays.asList(6, 7, 8, 9);
    //private variables
    Suit suit;
    boolean poisoned;
    boolean taken;
    int damage;
    //Value = 0 for Alchemy/Merlin/Apprentice cards
    int value;

    //Constructor
    public Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
        this.taken = false;
        //poisoned
        switch(suit){
            case SW:
                if (swP.contains(value)){this.poisoned = true;} break;
            case AR:
                if (arP.contains(value)){this.poisoned = true;} break;
            case SO:
                if (soP.contains(value)){this.poisoned = true;} break;
            case DE:
                if (deP.contains(value)){this.poisoned = true;} break;

            default: this.poisoned = false;
        }
        //damage
        if (suit == Suit.ME){this.damage = 25;}
        else if (this.poisoned){this.damage = 10;}
        else{this.damage = 5;}

    }

    //getters
    public Suit getSuit(){return suit;}
    public int getDamage(){return damage;}
    public int getValue(){return value;}
    public boolean getPoisoned(){return poisoned;}
    public boolean getTaken(){return taken;}

    public void setTaken(boolean taken){this.taken = taken;}





}
