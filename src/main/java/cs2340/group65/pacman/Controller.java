package cs2340.group65.pacman;

import java.io.IOException;
import javafx.fxml.FXML;

public class Controller{

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void switchToGame() throws  IOException{
        App.startGameScreen();
    }
}
