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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
    private int topBarHeight = 50;
    private boolean pause = false;

    private int numEnemy = 3;
    private Queue<Monster> monsterEggs;
    private List<Monster> monsters;

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
        monsterEggs = new ArrayDeque<Monster>();
        monsters = new ArrayList<Monster>();
        String imagepaths[] = new String[]{
            "file:src/main/resources/gt/cs2340/group65/pacman/images/BlueGhost.png",
            "file:src/main/resources/gt/cs2340/group65/pacman/images/RedGhost.png",
            "file:src/main/resources/gt/cs2340/group65/pacman/images/YellowGhost.png"
        };
        for (int i = 0; i < numEnemy; i++) {
            monsterEggs.add(new Monster(maze, enemyStartLocation, imagepaths[i]));
        }
        monsters.add(monsterEggs.poll());
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
        Label playerLife = new Label("Life: " + pacman.getPlayerLifes());
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

    private void initTimer() {
        timer = new AnimationTimer() {
            long lastTime = 0;
            int count = 0;
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
                            root.getChildren().add(monsters.get(monsters.size()-1));
                        }
                    }
                    count++;
                    scoreLabel.setText(("Score: " + pacman.getScore()));
                }
            }
        };
        timer.start();
    }

    private void switchToMainScreen() {
        App.startConfigurationScreen();
    }
}
