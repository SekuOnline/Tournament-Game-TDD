package cucumber.context;

import io.cucumber.java.After;
import org.classes.*;

import java.util.Scanner;

public class GameContext {
    public static GameContext instance;

    public GameMain game;

    public Melee recentMelee;
    public int meleeLeader;

    public String scannerInput;

    //constructor
    public GameContext(){

        game = new GameMain(1);
        scannerInput = "";
    }

    public static GameContext getSavedGameContext(){
        if (instance == null){
            instance = new GameContext();
        }
        return instance;
    }

    public void initGame(String playerNum, String playerNames, String initHP){
        instance.scannerInput = playerNum ;  //# of players input
        instance.scannerInput += playerNames; //Player names
        instance.scannerInput += initHP; //Initial Health points.
        System.out.println(instance.scannerInput);
        instance.game.initPlayers(new Scanner(instance.scannerInput));
        instance.scannerInput = "";
    }

    public void reset(){
        instance = new GameContext();
    }

    @After
    public void afterScenario(){
        if (instance != null){
            instance.reset();

        }
    }


    public void addCardContext(Integer playerIndex, String cardCode) {
        Suit cardSuit = Suit.valueOf(cardCode.substring(0, 2));
        int cardValue;
        //the value is still obtained correctly.
        int mod = cardCode.length()%2;
        cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));

        //Ensure that the index we're giving the card is not full
        for (int i = 0; i < Player.handSize; i++){

            if (game.players[playerIndex].hand[i] == null){
                if (cardSuit == Suit.ME || cardSuit == Suit.AP || cardSuit == Suit.AL){
                    cardValue = 0;
                }
                game.players[playerIndex].hand[i] = new Card(cardSuit, cardValue);
                game.players[playerIndex].hand[i].setPlayer(game.players[playerIndex]);
                System.out.println("Added "+cardCode+" to the hand of Player "+playerIndex);
                break;
            }
        }
    }

    public void addCardContextPartTwo(Integer playerIndex, String cardCode){
        Suit cardSuit = Suit.valueOf(cardCode.substring(0, 2));
        int cardValue;
        //the value is still obtained correctly.
        int mod = cardCode.length()%2;
        if (cardSuit == Suit.SW || cardSuit == Suit.DE || cardSuit == Suit.AR || cardSuit == Suit.SO){
            cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));
        }
        else{
            cardValue = 0;
        }



        //Ensure that the index we're giving the card is not full
        for (int i = 0; i < Player.handSize; i++){

            if (game.players[playerIndex].hand[i] == null){
                game.players[playerIndex].hand[i] = new Card(cardSuit, cardValue);
                game.players[playerIndex].hand[i].setPlayer(game.players[playerIndex]);
                System.out.println("Added "+cardCode+" to the hand of Player "+playerIndex);
                break;
            }
        }
    }


    public void playCard(Integer playerIndex, String cardCode){
        Suit cardSuit = Suit.valueOf(cardCode.substring(0,2));
        int mod = cardCode.length()%2;
        int cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));
        if (cardSuit == Suit.ME || cardSuit == Suit.AP || cardSuit == Suit.AL) {
            cardValue = 0;
        }
        Player player = instance.game.players[playerIndex];
        for (int i = 0; i < Player.handSize; i++){
            if(player.hand[i] != null){
                if ((player.hand[i].getSuit().compareTo(cardSuit) == 0) && player.hand[i].getValue() == cardValue){
                    //card found, add its index to the scanner plus any other info needed.


                    scannerInput += (i +1)+ "\n";
                    instance.chooseValue(playerIndex, cardCode, Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length())));
                    break;
                }
            }

        }
    }

    public void playCardPartTwo(Integer playerIndex, String cardCode){
        Suit cardSuit = Suit.valueOf(cardCode.substring(0,2));
        int mod = cardCode.length()%2;
        int cardValue;
        //int cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));
        Player player = instance.game.players[playerIndex];
        for (int i = 0; i < Player.handSize; i++){
            if(player.hand[i] != null){
                if ((player.hand[i].getSuit().compareTo(cardSuit) == 0)){
                    //card found, add its index to the scanner plus any other info needed.

                    if (cardSuit == Suit.SW || cardSuit == Suit.DE || cardSuit == Suit.AR || cardSuit == Suit.SO){
                        cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));
                        if (player.hand[i].getValue() == cardValue){
                            scannerInput += (i+1) + "\n";
                            break;
                        }
                    }
                    else{
                        scannerInput += (i+1) + "\n";

                    }

                }
            }

        }
    }

    public void chooseValue(int playerIndex, String cardCode, int cardValue){
        Suit cardSuit = Suit.valueOf(cardCode.substring(0,2));
        if (cardSuit == Suit.ME || cardSuit == Suit.AP || cardSuit == Suit.AL) {
            if (playerIndex == instance.meleeLeader && (cardSuit == Suit.ME || cardSuit == Suit.AP)) {
                scannerInput += cardCode.substring(2, 4)+ "\n";
            }
            scannerInput += cardValue + "\n";

        }



    }
    public void setRecentMelee(Melee melee){
        recentMelee = melee;
    }

    public int getPlayerIndexByName(String playerName){
        int playerIndex;
        for(int i = 0; i < instance.game.playerCount; i++){
            if (instance.game.players[i].getPlayerName().compareTo(playerName) == 0){
                return i;
            }
        }
        return -1;
    }

}
