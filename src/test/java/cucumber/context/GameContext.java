package cucumber.context;

import io.cucumber.java.After;
import org.classes.*;

import java.util.Scanner;

public class GameContext {
    public static GameContext instance;

    public GameMain game;

    public Melee recentMelee;

    public String scannerInput;

    //constructor
    public GameContext(){

        game = new GameMain();
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


    public void addCardContext(Integer playerIndex, String cardCode, int cardIndex) {
        playerIndex-=1;
        Suit cardSuit = Suit.valueOf(cardCode.substring(0, 2));
        int cardValue;
        //the value is still obtained correctly.
        int mod = cardCode.length()%2;
        cardValue = Integer.parseInt(cardCode.substring(cardCode.length() - (2-mod), cardCode.length()));
        game.players[playerIndex].hand[0] = new Card(cardSuit, cardValue);
        game.players[playerIndex].hand[0].setPlayer(game.players[playerIndex]);

        //We gave each player the card, now they have to play it
        scannerInput += "1\n";
        if (cardSuit == Suit.ME || cardSuit == Suit.AP || cardSuit == Suit.AL) {
            if (playerIndex == 0 && (cardSuit == Suit.ME || cardSuit == Suit.AP)) {
                scannerInput += cardCode.substring(2, 4)+ "\n";
            }
            scannerInput += cardValue + "\n";
        }
    }

    public void setRecentMelee(Melee melee){
        recentMelee = melee;
    }
}
