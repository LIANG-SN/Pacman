package gt.cs2340.group65.pacman;

import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.util.Duration;

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
    private boolean doublePoints;
    private int speedUp = 1;
    private FadeTransition fade;

    public Pacman(Coordinate initialCoordinate, String imagePath, int playerLifes, Maze maze, String color, Group root) {
        super("file:" + imagePath);
        this.initialCoordinate = initialCoordinate;
        setX(initialCoordinate.x + maze.getTranslateX());
        setY(initialCoordinate.y + maze.getTranslateY());
        this.playerLifes = playerLifes;
        this.maze = maze;
        this.fade = new FadeTransition(Duration.seconds(0.3), this);
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
                    if(doublePoints) {
                       setEffect(new Glow(0.8));
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
                    if(doublePoints) {
                        setEffect(new Glow(0.8));
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
                    if(doublePoints) {
                        setEffect(new Glow(0.8));
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
                    if(doublePoints) {
                        setEffect(new Glow(0.8));
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
        else if(point == -1) {
            initDoublePoints();
            point = 0;
        }
        if(doublePoints) {
            point *= 2;
        }
        score = score + point;
    }
    private void initAttackAbility() {
        attackAbility = true;
        invulnerable = true;
        fade.setToValue(1.0);
        fade.playFrom(Duration.seconds(0.5));
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

    private void initDoublePoints() {
        doublePoints = true;
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    doublePoints = false;
                    setEffect(null);
                }
            },
            4000
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
