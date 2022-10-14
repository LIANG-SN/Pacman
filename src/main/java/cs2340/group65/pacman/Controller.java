package cs2340.group65.pacman;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Controller{

    private VBox gameScreen;
    private HBox playerInfoBar;
    private Label playerName;
    private Label playerLife;
    private Label round;


    public Controller () {
        MainGameScreen game = new MainGameScreen(640, 280);
        gameScreen = new VBox();
        playerInfoBar = new HBox(20);
        playerName = new Label("Player's name");
        playerLife = new Label("Life: 10");
        round = new Label("Round: One");
        playerInfoBar.setAlignment(Pos.TOP_LEFT);
        playerInfoBar.getChildren().addAll(playerName, playerLife, round);
        gameScreen.getChildren().addAll(playerInfoBar, game);
        game.play();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot(gameScreen);
    }
}
