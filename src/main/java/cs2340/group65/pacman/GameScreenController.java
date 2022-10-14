package cs2340.group65.pacman;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import  javafx.scene.Group;
import javafx.scene.Scene;
import  javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

class GameScreenController {
    private boolean keyUp, keyDown, keyLeft, keyRight;
    Stage stage;
    AnimationTimer timer;
    private Pacman pacman;
    private Monster ghost;
    private Monster2 ghost2;
    private Group root;
    private Scene scene;
    private GraphicsContext graphicsContext;
    private Image wallImage;
    private Image pacmanImage;
    private int mazeWidth, mazeHeight;
    private int numRows = 16, numColumns = 16;
    private int topBarHeight = 50;
    private boolean pause = false;

    public GameScreenController (int mazeHeight, int mazeWidth){
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
        assert mazeHeight / numRows == mazeWidth / numColumns;
    }
    public void init(Stage stage, String playerName) throws IOException {
        root = new Group();
        scene = new Scene(root, mazeWidth, mazeHeight+topBarHeight);
        this.stage = stage;
        stage.setScene(scene);

        pacman = new Pacman(new Pacman.Coordinate(0, 0));
        ghost = new Monster(mazeWidth, mazeHeight);
        ghost2 = new Monster2(mazeWidth, mazeHeight,
                0, topBarHeight, getCellSize());

        root.getChildren().addAll(ghost, ghost2, pacman);
        initPlayerInfoBar(playerName);
        initPauseButton();
        initMainScreenButton();
        initCanvas(0, topBarHeight);
        initEventHandler();
        initTimer();
    }

    private void initEventHandler(){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case UP:    keyUp    = true; break;
                    case DOWN:  keyDown  = true; break;
                    case LEFT:  keyLeft  = true; break;
                    case RIGHT: keyRight = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case UP:    keyUp    = false; break;
                    case DOWN:  keyDown  = false; break;
                    case LEFT:  keyLeft  = false; break;
                    case RIGHT: keyRight = false; break;
                }
            }
        });
    }
    private void initPlayerInfoBar(String playerName)
    {
        HBox playerInfoBar = new HBox(20);
        Label playerNameLabel = new Label("Player: " + playerName);
        Label playerLife = new Label("Life: 10");
        Label round = new Label("Round: One");
        playerInfoBar.setAlignment(Pos.TOP_LEFT);
        playerInfoBar.getChildren().addAll(playerNameLabel, playerLife, round);
        root.getChildren().add(playerInfoBar);
    }
    private void initPauseButton(){
        Button pauseButton = new Button("Pause");
        pauseButton.setTranslateX(mazeWidth / 2);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pause = !pause;
                if (pause)
                    pauseButton.setText("Continue");
                else
                    pauseButton.setText("Pause");
            }
        });
        pauseButton.setFocusTraversable(false);
        root.getChildren().add(pauseButton);
    }
    private void initMainScreenButton(){
        Button mainScreenButton = new Button("Main Screen");
        mainScreenButton.setTranslateX(mazeWidth/2 + 80);
        mainScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchToMainScreen();
            }
        });
        mainScreenButton.setFocusTraversable(false);
        root.getChildren().add(mainScreenButton);
    }
    private void initCanvas(int translateX, int translateY){
        Canvas canvas = new Canvas(mazeHeight, mazeWidth);
        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(2);
    }
    private void initTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (!pause)
                {
                    Pacman.Coordinate coordinate = pacman.getLocation();
                    if (keyUp && coordinate.y > 0) pacman.moveUp();
                    if (keyDown && coordinate.y < mazeHeight - (int)(mazeHeight/numRows)) pacman.moveDown();
                    if (keyLeft && coordinate.x > 0) pacman.moveLeft();
                    if (keyRight && coordinate.x < mazeWidth - (int)(mazeWidth/numColumns)) pacman.moveRight();

                    ghost.update();
                    double pathStartX = ghost2.getX() + getCellSize() / 4;
                    double pathStartY = ghost2.getY() - getCellSize();
                    ghost2.update();
                    double pathEndX = ghost2.getX() + getCellSize() / 4;
                    double pathEndY = ghost2.getY() - getCellSize();
                    graphicsContext.strokeLine(pathStartX, pathStartY, pathEndX, pathEndY);
                }
            }
        };
        timer.start();
    }
    private double getCellSize() { return mazeHeight / numRows;}
    private void switchToMainScreen()
    {
        timer.stop();
        App.switchToMainScene();
        try{
            App.setRoot("primary");
        }catch (IOException ioException){
            System.out.println("IO exception occurs App setRoot function");
        }

    }
}
