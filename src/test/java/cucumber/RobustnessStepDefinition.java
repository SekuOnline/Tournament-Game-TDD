package cucumber;


import cucumber.context.GameContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import java.util.Scanner;

import static org.junit.Assert.*;


public class RobustnessStepDefinition {
    @Given("An invalid number of players {int}")
    public void invalidNumPlayers(int numPlayers){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += numPlayers + "\n";

    }

    @Given("A valid number of players {int}")
    public void validNumPlayers(int numPlayers){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += numPlayers + "\n";

    }



    @Given("An invalid name for Player {int} {string}")
    public void getInvalidPlayerName(int playerNum, String playerName){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += playerName + "\n";
    }

    @Given("A valid name for Player {int} {string}")
    public void getValidPlayerName(int playerNum, String playerName){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += playerName + "\n";
    }

    @Given("An invalid number for initial health points {int}")
    public void getInvalidInitHP(int healthPoints){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += healthPoints + "\n";
    }

    @Given("A valid number for initial health points {int}")
    public void getValidInitHP(int healthPoints){
        GameContext context = GameContext.getSavedGameContext();
        context.scannerInput += healthPoints + "\n";
    }

    @Then("Player count should be {int}")
    public void checkPlayerCount(int playerCount){
        GameContext context = GameContext.getSavedGameContext();
        context.game.initPlayers(new Scanner(context.scannerInput));
        int gamePlayerCount = context.game.playerCount;

        assertEquals(gamePlayerCount, playerCount);
    }

    @Then("Player {int} name should be {string}")
    public void checkValidName(int playerIndex, String playerName){
        GameContext context = GameContext.getSavedGameContext();
        String actualPlayerName = context.game.players[playerIndex-1].getPlayerName();
        assertEquals(actualPlayerName, playerName);
    }

    @Then("Initial health points should be {int}")
    public void checkHealthPoints(int healthPoints){
        GameContext context = GameContext.getSavedGameContext();
        for(int i = 0; i < context.game.playerCount; i++){
            assertEquals(context.game.players[i].getHitPoints(), healthPoints);
        }
    }

}
