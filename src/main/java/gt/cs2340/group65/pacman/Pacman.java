package gt.cs2340.group65.pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

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
    private Image attackLeft;
    private Image attackRight;
    private Image attackUp;
    private Image attackDown;

    private boolean invulnerable;
    private boolean attackAbility = false;
    private int speedUp = 1;

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
            attackDown = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/images/attackDown.png"));
            attackUp = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/images/attackUp.png"));
            attackLeft = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/images/attackLeft.png"));
            attackRight = new Image(new FileInputStream(
                "src/main/resources/gt/cs2340/group65/pacman/images/attackRight.png"));
        } catch (Exception e) {
            System.out.println("something was wrong with image file");
        }

    }

    public void moveUp() {
        for (int i = 0; i < stepLength * speedUp; i++) {
            if (maze.checkUp(getLocation())) {
                setY(getY() - 1);
                try {
                    if(attackAbility) {
                        setImage(attackUp);
                    }
                    else {
                        setImage(pacmanUp);
                    }
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < stepLength * speedUp; i++) {
            if (maze.checkDown(getLocation())) {
                setY(getY() + 1);
                try {
                    if(attackAbility) {
                        setImage(attackDown);
                    }
                    else {
                        setImage(pacmanDown);
                    }
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveLeft() {
        for (int i = 0; i < stepLength * speedUp; i++) {
            if (maze.checkLeft(getLocation())) {
                setX(getX() - 1);
                try {
                    if(attackAbility) {
                        setImage(attackLeft);
                    }
                    else {
                        setImage(pacmanLeft);
                    }
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    public void moveRight() {
        for (int i = 0; i < stepLength * speedUp; i++) {
            if (maze.checkRight(getLocation())) {
                setX(getX() + 1);
                try {
                    if(attackAbility) {
                        setImage(attackRight);
                    }
                    else {
                        setImage(pacmanRight);
                    }
                } catch (Exception e) {
                    System.out.println("something was wrong with image file");
                }
                eatPelle(getLocation());
            }
        }
    }

    private void eatPelle(Coordinate playerLocation) {
        int point = maze.removePelle(root, playerLocation);
        if (point == 1) {
            initAttackAbility();
        }
        else if(point == 2) {
            initSpeedUp();
        }
        score = score + point;
    }
    private void initAttackAbility() {
        attackAbility = true;
        invulnerable = true;
        setImage(attackUp);
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    attackAbility = false;
                    invulnerable = false;
                    setImage(pacmanUp);
                }
            },
            5000
        );
    }
    private void initSpeedUp() {
        speedUp = 2;
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    speedUp = 1;
                }
            },
            3000
        );
    }

    public int checkCollision(Coordinate playerLocation) {
        int monsterIndex = maze.pacmanMonsterCollision(playerLocation);
        if (playerLocation != null) {
            if (monsterIndex != -1 && getAttackAbility() && isInvulnerable()) {
                return monsterIndex;
            } else if (monsterIndex != -1 && !isInvulnerable() && playerLifes > 0) {
                playerLifes--;
                return monsterIndex;
            }
        }
        return -1;
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

    public void addScore(int point) {
        this.score += point;
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
    public boolean getAttackAbility() {
        return attackAbility;
    }
    public void  setAttackAbility(boolean a) {
        attackAbility = a;
    }
}
