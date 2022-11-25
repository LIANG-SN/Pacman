package gt.cs2340.group65.pacman;

import javafx.scene.Group;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class MazeTest extends ApplicationTest{
    GameScreenController gameScreenController;
    Maze maze;
    int numRows = 15;
    int numColumns = 15;

    @Override
    public void start(Stage stage) throws Exception{
        String color = "yellow";
        String imagePath =
            "src/main/resources/gt/cs2340/group65/pacman/" + color + "Pacman.png";
        gameScreenController = new GameScreenController("name",
            imagePath, 5, color);
        stage.setScene(gameScreenController.getScene());
        stage.show();
        Coordinate pacmanStartLocation = new Coordinate(0, 0);
        Coordinate enemyStartLocation = new Coordinate(320, 320);
        maze = new Maze(600, 600, numRows, numColumns, 0, 50,
            pacmanStartLocation, enemyStartLocation);
    }
    @Test
    public void deadend() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int count = 0;
                if (i + 1 < numRows)
                {
                    count += maze.getGrid(i+1, j) == '1' ? 1 : 0;
                }
                else {
                    count += 1;
                }
                if (i-1 >=0){
                    count += maze.getGrid(i-1, j) == '1' ? 1 : 0;
                }
                else{
                    count += 1;
                }
                if (j+1 < numColumns){
                    count += maze.getGrid(i, j+1) == '1' ? 1 : 0;
                }
                else {
                    count += 1;
                }
                if (j-1 >=0){
                    count += maze.getGrid(i, j-1) == '1' ? 1 : 0;
                }
                else {
                    count += 1;
                }
                assertTrue(count < 3);
            }
        }
    }
    @Test
    public void removePellet() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int p = maze.removePelle(gameScreenController.getRoot(),
                    new Coordinate(i*maze.getCellSize(), j*maze.getCellSize()));
                assertTrue(p == 0 || p == 10 || p == 100);
            }
        }

    }
    @Test
    public void getHeight() {
        double h = maze.getHeight();
        assertEquals(600, h, 1e-3);
    }

    @Test
    public void getWidth() {
        double w = maze.getWidth();
        assertEquals(600, w, 1e-3);
    }
}
