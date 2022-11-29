package gt.cs2340.group65.pacman;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class GameScreenControllerTest extends ApplicationTest {
    private static Stage stage;
    GameScreenController gameScreenController;
    @Override
    public void start(Stage stage) throws Exception{
        String color = "yellow";
        String imagePath =
            "src/main/resources/gt/cs2340/group65/pacman/" + color + "Pacman.png";
        gameScreenController = new GameScreenController("name",
            imagePath, 5, color);
        stage.setScene(gameScreenController.getScene());
        this.stage = stage;
        stage.show();
    }
    @Test
    public void testConstructor() {
        // implicitly test constructor
    }
}
