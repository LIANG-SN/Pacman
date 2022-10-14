package cs2340.group65.pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mStage;
    private static GameScreenController gameScreenController;

    @Override
    public void start(Stage stage) throws IOException {

        mStage = stage;
        scene = new Scene(loadFXML("primary"), 640, 480);
        gameScreenController = new GameScreenController(640, 640);

        stage.setTitle("PacMan Game");

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Group root) {
        scene.setRoot(root);
    }

    public static void startGameScreen() throws  IOException{
        gameScreenController.init(mStage, "Default name");
    }

    static void setRoot(VBox root) throws IOException {
        scene.setRoot(root);
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void switchToMainScene()
    {
        mStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}