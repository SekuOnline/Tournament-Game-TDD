package cucumber;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MeleeStepDefinition {

    @Given("Player {int} plays the card {String}")
    public void givePlayerCard(int playerIndex, String cardCode){
        //Give each player the card designated in the cardCode

    }
    @Then("The loser should be {String}")
    public void checkLoserOfMelee(String playerCode){

    }

    @And("The injury points should be {int}")
    public void checkInjuryPointsOfMelee(int injury_points){

    }
}
