package gt.cs2340.group65.pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Pacman extends ImageView {
    private int score = 0;
    private int playerLifes;
    private Maze maze;
    private Coordinate initialCoordinate;
    private String color;
    private Group root;

    public Pacman(Coordinate initialCoordinate, String imagePath, int playerLifes, Maze maze, String color, Group root) {
        super("file:" + imagePath);
        this.initialCoordinate = initialCoordinate;
        setX(initialCoordinate.x + maze.getTranslateX());
        setY(initialCoordinate.y + maze.getTranslateY());
        this.playerLifes = playerLifes;
        this.maze = maze;
        setPreserveRatio(false);
        setFitWidth(maze.getCellSize());
        setFitHeight(maze.getCellSize());
        this.color = color;
        this.root = root;
    }

    public void moveUp() {
        if (maze.checkUp(new Coordinate(getX(), getY()))) {
            setY(getY() - 1);
            try {
                setImage(new Image(new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/" + color + "PacmanUp.png")));
            }
            catch(Exception e) {
                System.out.println("something was wrong with image file");
            }
            eatPelle(getLocation());
        }
    }

    public void moveDown() {

        if (maze.checkDown(new Coordinate(getX(), getY()))) {
            setY(getY() + 1);
            try {
                setImage(new Image(new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/" + color + "PacmanDown.png")));
            }
            catch(Exception e) {
                System.out.println("something was wrong with image file");
            }
            eatPelle(getLocation());
        }
    }

    public void moveLeft() {
        if (maze.checkLeft(new Coordinate(getX(), getY()))) {
            setX(getX() - 1);
            try {
                setImage(new Image(new FileInputStream(
                    "src/main/resources/gt/cs2340/group65/pacman/" + color + "PacmanLeft.png")));
            } catch (Exception e) {
                System.out.println("something was wrong with image file");
            }
            eatPelle(getLocation());
        }
    }

    public void moveRight() {
        if (maze.checkRight(new Coordinate(getX(), getY()))) {
            setX(getX() + 1);
            try {
                setImage(new Image(new FileInputStream(
                    "src/main/resources/gt/cs2340/group65/pacman/" + color + "PacmanRight.png")));
            } catch (Exception e) {
                System.out.println("something was wrong with image file");
            }
            eatPelle(getLocation());
        }
    }

    private void eatPelle(Coordinate currentLocation) {
        int playerX = (int )Math.ceil(currentLocation.x / maze.getCellSize());
        int playerY = (int) Math.ceil(currentLocation.y / maze.getCellSize());
        if(maze.checkPelle(playerX, playerY)) {
            score = score + maze.getPelleScore(playerX, playerY);
            maze.removePelle(root, playerX, playerY);
        }
    }

    public Coordinate getLocation() {
        return new Coordinate(getX(), getY());
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerLifes() {
        return playerLifes;
    }
}
