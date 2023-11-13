package cucumber;


import cucumber.context.GameContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;


import java.util.Scanner;

import static org.junit.Assert.*;

public class MeleeStepDefinition {

    @Given("The game has started")
    public void startTheGame(){
        GameContext context = GameContext.getSavedGameContext();
        context.initGame("4\n", "Player 1\nPlayer 2\nPlayer 3\nPlayer 4\n", "999\n");

    }
    @Given("Player {string} plays the card {word}")
    public void playerPlaysTheCard(String playerIndex, String cardCode){
        //Give each player the card designated in the cardCode
        GameContext context = GameContext.getSavedGameContext();
        context.addCardContext(Integer.parseInt(playerIndex), cardCode, 0);

    }
    @Then("The loser should be {word}")
    public void checkLoserOfMelee(String loserCode){

        //System.out.println(loserCode);
        GameContext context = GameContext.getSavedGameContext();
        //System.out.println(context.scannerInput);
        context.recentMelee = context.game.doMelee(0, new Scanner(context.scannerInput));
        //no loser:
        if (loserCode.compareTo("-") == 0){
            //System.out.println("CASE: NO LOSER");
            assertEquals((context.recentMelee.loserIndex), (-1));
        }
        else{
            int loser = Integer.parseInt(loserCode.substring(1,2))-1;
            //System.out.println(context.recentMelee.loserIndex + " " + loserCode.substring(1,2));
            assertEquals(context.recentMelee.loserIndex, loser);
        }
    }

    @And("The injury points should be {int}")
    public void checkInjuryPointsOfMelee(int injury_points){
        GameContext context = GameContext.getSavedGameContext();

        assertEquals(injury_points, context.game.getTotalDamage(context.recentMelee.cardStack));
    }


}
