package gt.cs2340.group65.pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

import java.io.FileInputStream;

class Pacman extends ImageView {
    private int score = 0;
    private int playerLifes;
    private Maze maze;
    private Coordinate initialCoordinate;
    private String color;
    private Group root;
    private int stepLength = 3;
    private Image pacmanLeft;
    private Image pacmanRight;
    private Image pacmanUp;
    private Image pacmanDown;

    private boolean invulnerable;

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
        this.invulnerable = false;
        initImages();
    }

    private void initImages() {
        try {
            pacmanDown = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/" + color +
                    "PacmanDown.png"));
            pacmanUp = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/" + color + "PacmanUp.png"));
            pacmanLeft = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/" + color +
                    "PacmanLeft.png"));
            pacmanRight = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/" + color +
                    "PacmanRight.png"));
        } catch (Exception e) {
            System.out.println("something was wrong with image file");
        }

    }

    public void moveUp() {
        for (int i = 0; i < stepLength; i++) {
            if (maze.checkUp(getLocation())) {
                setY(getY() - 1);
                try {
                    setImage(pacmanUp);
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < stepLength; i++) {
            if (maze.checkDown(getLocation())) {
                setY(getY() + 1);
                try {
                    setImage(pacmanDown);
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveLeft() {
        for (int i = 0; i < stepLength; i++) {
            if (maze.checkLeft(getLocation())) {
                setX(getX() - 1);
                try {
                    setImage(pacmanLeft);
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveRight() {
        for (int i = 0; i < stepLength; i++) {
            if (maze.checkRight(getLocation())) {
                setX(getX() + 1);
                try {
                    setImage(pacmanRight);
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    private void eatPelle(Coordinate playerLocation) {
        int point = maze.removePelle(root, playerLocation);
        score = score + point;
    }

    public boolean checkCollision(Coordinate playerLocation) {
        if (playerLocation != null) {
            if (maze.pacmanMonsterCollision(playerLocation) && !isInvulnerable() && playerLifes > 0) {
                playerLifes--;
                return true;
            }
        }
        return false;
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

    public void setInvulnerable(boolean immnue) {
        this.invulnerable = immnue;
    }

    public boolean isInvulnerable() {
        return this.invulnerable;
    }
}
