package cucumber;


import cucumber.context.GameContext;
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


    //--Scenario:(Melee 1) Playing cards of the incorrect suit.

    @Given("{word} is the leader of the melee")
    public void startMelee(String leaderName){
        GameContext context = GameContext.getSavedGameContext();
        //Get the 'leader' from leaderName
        context.meleeLeader = context.getPlayerIndexByName(leaderName);
        context.scannerInput = "";
        //Might have to move this down FIND
        //context.game.doMelee(leaderIndex, new Scanner(context.scannerInput));
    }

    @Given("{word} is given {string}")
    public void giveCard(String playerName, String cardString){
        GameContext context = GameContext.getSavedGameContext();
        //System.out.println(context.game.playerCount);
        String[] cards = cardString.split("\\s");
        int playerIndex = context.getPlayerIndexByName(playerName);
        //System.out.println(playerIndex);
        String cardCode;
        for (int i = 0; i < cards.length; i++){
            cardCode = cards[i];
            context.addCardContextPartTwo(playerIndex, cardCode);
        }
        //context.game.players[playerIndex].printHand();
    }

    @Given("{word} plays {word}")
    public void playCard(String playerName, String cardString){
        GameContext context = GameContext.getSavedGameContext();
        int playerIndex = context.getPlayerIndexByName(playerName);
        context.playCardPartTwo(playerIndex, cardString);
    }

    @Given("{word} chooses value {int} for {word}")
    public void chooseValue(String playerName, int value, String cardCode){
        GameContext context = GameContext.getSavedGameContext();
        int playerIndex = context.getPlayerIndexByName(playerName);
        context.chooseValue(playerIndex, cardCode, value);
    }

    @Given("The melee occurs")
    public void beginMelee(){
        GameContext context = GameContext.getSavedGameContext();
        System.out.println("Here is the melee input: \n"+context.scannerInput);
        context.recentMelee = context.game.doMelee(context.meleeLeader, new Scanner(context.scannerInput));
    }

    @Then("{word} should be the loser")
    public void compareLoser(String playerName){
        GameContext context = GameContext.getSavedGameContext();
        int actualLoserIndex = context.getPlayerIndexByName(playerName);
        assertEquals(actualLoserIndex, context.recentMelee.loserIndex);
    }

    @Then("Injury points should be {int}")
    public void compareInjuryPoints(int injuryPoints){
        GameContext context = GameContext.getSavedGameContext();
        int actualInjuryPoints = context.game.getTotalDamage(context.recentMelee.cardStack);
        assertEquals(actualInjuryPoints, injuryPoints);
    }



}
