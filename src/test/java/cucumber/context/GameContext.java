package cucumber.context;

import io.cucumber.java.After;
import org.classes.*;

public class GameContext {
    public static GameContext instance;

    public GameMain game;

    //constructor
    public GameContext(){
        game = new GameMain();
    }

    public void setPlayerNumber(String playerNumber) {
        if (game != null) {
            game.setPlayerCount(playerNumber);
        }
    }

    public void reset(){
        game = new GameMain();
    }

    @After
    public void afterScenario(){
        if (instance != null){
            instance.reset();
        }
    }
}
