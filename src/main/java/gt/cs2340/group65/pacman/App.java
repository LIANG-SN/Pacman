package gt.cs2340.group65.pacman;

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
    private static WelcomeScreen welcomeScreen;
    private static ConfigurationScreen configurationScreen;

    private static GameOverScreen gameOverScreen;

    @Override
    public void start(Stage stage) throws IOException {

        thisStage = stage;
        welcomeScreen = new WelcomeScreen();
        welcomeScreen.display();
        configurationScreen = new ConfigurationScreen();
        gameOverScreen = new GameOverScreen();
        stage.setTitle("PacMan Game");
        thisStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void startWelcomeScreen() {
        try {
            welcomeScreen.display();
        } catch (IOException ioException) {
            System.out.println("io exception occurs during starting welcome screen.");
        }

    }

    public static void startConfigurationScreen() {
        try {
            configurationScreen.start();
        } catch (IOException ioException) {
            System.out.println("io exception occurs during starting config screen.");
        }
    }

    public static void startGameOverScreen() {
        try {
            gameOverScreen.start();
        } catch (IOException ioException) {
            System.out.println("io exception occurs during starting game over screen.");
        }
    }

    public static void startGameScreen(String playerName,
                                       String playerImagePath,
                                       int playerLifes, String color) {
        gameScreenController = new GameScreenController(playerName,
            playerImagePath, playerLifes, color);
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