package cs2340.group65.pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage thisStage;
    private static GameScreenController gameScreenController;

    @Override
    public void start(Stage stage) throws IOException {

        thisStage = stage;
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle("PacMan Game");
        stage.setScene(scene);
        thisStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void startGameScreen() throws IOException {
        gameScreenController = new GameScreenController("Default name",
                "src/main/resources/cs2340/group65/pacman/images/pacman.gif",
                10);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setScene(Scene setScene) {
        scene = setScene;
        thisStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}