package gt.cs2340.group65.pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Pacman extends ImageView {
    private int score = 0;
    private int playerLifes;
    private Maze maze;
    private Coordinate initialCoordinate;
    private String color;

    public Pacman(Coordinate initialCoordinate, String imagePath, int playerLifes, Maze maze, String color) {
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
