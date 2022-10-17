package cs2340.group65.pacman;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

class GameScreenController {
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;
    private AnimationTimer timer;
    private Pacman pacman;
    private Monster ghost;
    private Group root;
    private Scene scene;
    private GraphicsContext graphicsContext;
    private Maze maze;

    private int topBarHeight = 50;
    private boolean pause = false;

    public GameScreenController(String playerName, String playerImagePath,
                                int playerLifes) {
        maze = new Maze(640, 640, 16, 16, 0, topBarHeight);
        root = new Group();
        scene = new Scene(root, maze.getWidth(), maze.getHeight() + topBarHeight);
        App.setScene(scene);
        pacman = new Pacman(new Pacman.Coordinate(100, 100), playerImagePath, playerLifes);
        ghost = new Monster(maze);
        root.getChildren().addAll(ghost, pacman);
        initPlayerInfoBar(playerName);
        initPauseButton();
        initMainScreenButton();
        initCanvas(0, topBarHeight);
        initEventHandler();
        initTimer();
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
                }
            }
        });
    }

    private void initPlayerInfoBar(String playerName) {
        HBox playerInfoBar = new HBox(20);
        Label playerNameLabel = new Label("Player: " + playerName);
        Label scoreLabel = new Label("Score: " + pacman.getScore());
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
                }
                else {
                    pauseButton.setText("Pause");
                }
            }
        });
        pauseButton.setFocusTraversable(false);
        root.getChildren().add(pauseButton);
    }

    private void initMainScreenButton() {
        Button mainScreenButton = new Button("Main Screen");
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

    private void initCanvas(int translateX, int translateY) {
        Canvas canvas = new Canvas(maze.getWidth(), maze.getHeight());
        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(2);
    }

    private void initTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (!pause) {
                    Pacman.Coordinate coordinate = pacman.getLocation();
                    if (keyUp && coordinate.y > 0 + maze.getTranslateY()) {
                        pacman.moveUp();
                    }
                    if (keyDown && coordinate.y < maze.getHeight()
                            - maze.getCellSize() + maze.getTranslateY()) {
                        pacman.moveDown();
                    }
                    if (keyLeft && coordinate.x > 0 + maze.getTranslateX()) {
                        pacman.moveLeft();
                    }
                    if (keyRight && coordinate.x < maze.getWidth()
                            - maze.getCellSize() + maze.getTranslateX()) {
                        pacman.moveRight();
                    }

                    double pathStartX = ghost.getX() + maze.getCellSize() / 4;
                    double pathStartY = ghost.getY() - maze.getCellSize();
                    ghost.update();
                    double pathEndX = ghost.getX() + maze.getCellSize() / 4;
                    double pathEndY = ghost.getY() - maze.getCellSize();
                    graphicsContext.strokeLine(pathStartX, pathStartY, pathEndX, pathEndY);
                }
            }
        };
        timer.start();
    }

    private void switchToMainScreen() {
        try {
            App.setScene(new Scene(App.loadFXML("primary"), 640, 480));
        } catch (IOException ioException) {
            System.out.println("IO exception occurs during load fxml");
        }
    }
}
