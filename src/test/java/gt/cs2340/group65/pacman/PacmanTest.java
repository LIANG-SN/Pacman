package gt.cs2340.group65.pacman;

import javafx.scene.Group;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class PacmanTest extends ApplicationTest {
    private Pacman pacman;
    String color = "yellow";
    String imagePath =
        "src/main/resources/gt/cs2340/group65/pacman/" + color + "Pacman.png";
    int playerLifes = 5;
    int numRows = 15;
    int numColumns = 15;
    Coordinate pacmanStartLocation = new Coordinate(0, 0);
    Coordinate enemyStartLocation = new Coordinate(320, 320);
    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
        Group root = new Group();
        Maze maze = new Maze(600, 600, numRows, numColumns, 0, 50,
            root, pacmanStartLocation, enemyStartLocation);
        pacman = new Pacman(pacmanStartLocation, imagePath, playerLifes, maze, color, root);
    }
    @Test
    public void move() {
        pacman.moveUp();
        pacman.moveDown();
        pacman.moveLeft();
        pacman.moveRight();
    }
    @Test
    public void collision() {
        boolean c = pacman.checkCollision(pacmanStartLocation);
        assertFalse(c);
    }
}
