package cucumber;

import cucumber.context.GameContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.classes.Player;


import java.util.Scanner;

import static org.junit.Assert.*;

public class Part3StepDefinition {

    @Given("A number of players {int}")
    public void validNumPlayers(int numPlayers){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += numPlayers + "\n";

    }

    @Given("A name for player {string}")
    public void getValidPlayerName(String playerName){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += playerName + "\n";
    }

    @Given ("Game is initialized")
    public void initGame(){
        GameContext context = GameContext.getSavedGameContext();
        context.game.initPlayers(new Scanner(context.scannerInput));
    }

    @Given("The melee begins")
    public void meleeBegins(){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput = "";
    }

    @Given("The round occurs")
    public void startRound(){
        GameContext context = GameContext.getSavedGameContext();
        context.game.startRound(0, new Scanner(context.scannerInput));

    }

    @Then("{word} should have health {word} than {int}")
    public void assertCorrectHealth(String playerName, String comparison, int value){
        GameContext context = GameContext.getSavedGameContext();
        int playerIndex = context.getPlayerIndexByName(playerName);

        if (comparison.compareTo("greater") == 0){
            assertTrue(context.game.players[playerIndex].getHitPoints() > 0);
        }
        else{
            System.out.println(context.game.players[playerIndex].getHitPoints());
            assertTrue(context.game.players[playerIndex].getHitPoints() <= 0);
        }
    }

    @Then("The winners should be {string}")
    public void assertWinners(String playerNames){
        GameContext context = GameContext.getSavedGameContext();
        String[] winnerNames = playerNames.split("\\s");
        Player[] actualWinners = context.game.checkPlayersBelowZero();
        for (int i = 0; i < winnerNames.length; i++){
            assertEquals(actualWinners[i].getPlayerName(), winnerNames[i]);
        }


    }

}
