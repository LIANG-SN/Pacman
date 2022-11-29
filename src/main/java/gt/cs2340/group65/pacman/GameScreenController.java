package gt.cs2340.group65.pacman;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.util.*;

class GameScreenController {
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;
    private AnimationTimer timer;
    private Pacman pacman;
    private Group root;
    private Scene scene;
    private GraphicsContext graphicsContext;
    private Maze maze;

    private Label scoreLabel;
    private Label playerLife;

    private int topBarHeight = 50;
    private boolean pause = false;

    private int numEnemy = 3;
    private Queue<Monster> monsterEggs;
    private List<Monster> monsters;
    private String[] monsterImagepaths;
    private Coordinate enemyStartLocation;

    public GameScreenController(String playerName, String playerImagePath,
                                int playerLifes, String color) {
        root = new Group();
        Coordinate pacmanStartLocation = new Coordinate(0, 0);
        Coordinate enemyStartLocation = new Coordinate(320, 320);
        maze = new Maze(600, 600, 15, 15, 0, topBarHeight, root,
            pacmanStartLocation, enemyStartLocation);
        scene = new Scene(root, maze.getWidth(), maze.getHeight() + topBarHeight);
        App.setScene(scene);
        pacman = new Pacman(pacmanStartLocation, playerImagePath, playerLifes, maze, color, root);
        root.getChildren().add(pacman);
        initMonsters(enemyStartLocation);
        initPlayerInfoBar(playerName);
        initPauseButton();
        initMainScreenButton();
        initEventHandler();
        initTimer();
    }
    private void initMonsters(Coordinate enemyStartLocation){
        this.enemyStartLocation = enemyStartLocation;
        monsterEggs = new ArrayDeque<Monster>();
        monsters = new ArrayList<Monster>();
        monsterImagepaths = new String[]{
            "file:src/main/resources/gt/cs2340/group65/pacman/images/BlueGhost.png",
            "file:src/main/resources/gt/cs2340/group65/pacman/images/RedGhost.png",
            "file:src/main/resources/gt/cs2340/group65/pacman/images/YellowGhost.png"
        };
        for (int i = 0; i < numEnemy; i++) {
            Monster ghost  = new Monster(maze, enemyStartLocation, monsterImagepaths[i], false, i);
            maze.monsterList(ghost);
            monsterEggs.add(ghost);
        }
        monsters.add(monsterEggs.poll());
        monsters.get(0).setSpawned(true);
        root.getChildren().add(monsters.get(0));
    }
    private void initEventHandler() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case UP:
                    keyUp = true;
                    break;
                case DOWN:
                    keyDown = true;
                    break;
                case LEFT:
                    keyLeft = true;
                    break;
                case RIGHT:
                    keyRight = true;
                    break;
                default:
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case UP:
                    keyUp = false;
                    break;
                case DOWN:
                    keyDown = false;
                    break;
                case LEFT:
                    keyLeft = false;
                    break;
                case RIGHT:
                    keyRight = false;
                    break;
                default:
                }
            }
        });
    }

    private void initPlayerInfoBar(String playerName) {
        HBox playerInfoBar = new HBox(20);
        Label playerNameLabel = new Label("Player: " + playerName);
        scoreLabel = new Label("Score: " + pacman.getScore());
        playerLife = new Label("Life: " + pacman.getPlayerLifes());
        Label round = new Label("Round: One");
        playerInfoBar.setAlignment(Pos.TOP_LEFT);
        playerInfoBar.getChildren().addAll(playerNameLabel, scoreLabel, playerLife, round);
        root.getChildren().add(playerInfoBar);
    }

    private void initPauseButton() {
        Button pauseButton = new Button("Pause");
        pauseButton.setTranslateX(maze.getWidth() / 2 + 10);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pause = !pause;
                if (pause) {
                    pauseButton.setText("Continue");
                } else {
                    pauseButton.setText("Pause");
                }
            }
        });
        pauseButton.setFocusTraversable(false);
        root.getChildren().add(pauseButton);
    }

    private void initMainScreenButton() {
        Button mainScreenButton = new Button("Back");
        mainScreenButton.setTranslateX(scene.getWidth() / 2 + 80);
        mainScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchToMainScreen();
            }
        });
        mainScreenButton.setFocusTraversable(false);
        root.getChildren().add(mainScreenButton);
    }

    private void addNewMonster(int markedNumber){
        Monster ghost  = new Monster(maze, enemyStartLocation, monsterImagepaths[markedNumber], false, markedNumber);
        maze.monsterList(ghost);
        monsterEggs.add(ghost);
    }

    private void initTimer() {
        timer = new AnimationTimer() {
            long lastTime = 0;
            int count = 0;
            int countMonster = 1;

            int countInvulnerable = 0;
            FadeTransition blink = new FadeTransition(Duration.seconds(0.3), pacman);
            @Override
            public void handle(long time) {

                if (!pause && (time - lastTime > 1e7) ) {
                    if (keyUp) {
                            pacman.moveUp();
                    }
                    if (keyDown) {
                            pacman.moveDown();
                    }
                    if (keyLeft) {
                            pacman.moveLeft();
                    }
                    if (keyRight) {
                            pacman.moveRight();
                    }
                    for(int i = 0; i < monsters.size(); i++) {
                        monsters.get(i).update();
                    }
                    lastTime = time;
                    if (count == 500){
                        count = 0;
                        if (monsterEggs.size() > 0) {
                            monsters.add(monsterEggs.poll());
                            monsters.get(countMonster).setSpawned(true);
                            countMonster++;
                            root.getChildren().add(monsters.get(monsters.size()-1));
                        }
                    }
                    int checkCollision = pacman.checkCollision(pacman.getLocation());
                    if (checkCollision != -1 && pacman.getAttackAbility()) {
                        int monsterIndex = monsters.get(checkCollision).getMarkedNumber();
                        monsters.remove(checkCollision);
                        pacman.addScore(maze.removeMonster(root, checkCollision));
                        blink.setToValue(1.0);
                        blink.playFrom(Duration.seconds(0.5));
                        countMonster--;
                        if(monsters.size() < numEnemy) {
                            count = 0;
                            addNewMonster(monsterIndex);
                        }
                    } else if (checkCollision != -1) {
                        pacman.setInvulnerable(true);
                        countInvulnerable = 0;
                        blink.setToValue(0.3);
                        blink.playFrom(Duration.seconds(1.5));
                    }

                    if (countInvulnerable > 250 && !pacman.getAttackAbility()) {
                        pacman.setInvulnerable(false);
                        countInvulnerable = 0;
                        blink.setToValue(1.0);
                        blink.playFrom(Duration.seconds(0.5));
                    }
                    count++;
                    countInvulnerable++;
                    scoreLabel.setText(("Score: " + pacman.getScore()));
                    playerLife.setText("Life: " + pacman.getPlayerLifes());
                    if (pacman.getPlayerLifes() <= 0) {
                        App.startGameOverScreen();
                        timer.stop();
                    }

                    if (maze.checkWinCondition()) {
                        App.startGameOverScreen();
                        timer.stop();
                    }
                }
            }
        };
        timer.start();
    }

    private void switchToMainScreen() {
        App.startConfigurationScreen();
    }
}
