package cs2340.group65.pacman;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  javafx.scene.Group;
import javafx.scene.Scene;
import  javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

class GameScreenController {
    private boolean keyUp, keyDown, keyLeft, keyRight;
    private Pacman pacman;
    private Group root;
    private Scene scene;
    private GraphicsContext graphicsContext;
    private Image wallImage;
    private Image pacmanImage;
    private int mazeWidth, mazeHeight;
    private int numRows = 16, numColumns = 16;
    private boolean pause = false;

    public GameScreenController (int mazeHeight, int mazeWidth){
        pacman = new Pacman(new Pacman.Coordinate(0, 0));
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
        assert mazeHeight / numRows == mazeWidth / numColumns;
    }
    public void init(Stage stage) throws IOException {
        root = new Group();
        int topBarHeight = 50;
        scene = new Scene(root,mazeHeight + topBarHeight, mazeWidth);
        stage.setScene(scene);

        initPauseButton();
        initCanvas(0, topBarHeight);
        initImages();
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
    private void initCanvas(int translateX, int translateY){
        Canvas canvas = new Canvas(mazeHeight, mazeWidth);
        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
    }
    private void initTimer(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (!pause)
                {
                    Pacman.Coordinate coordinate = pacman.getLocation();
                    if (keyUp && coordinate.y > 0) pacman.moveUp();
                    if (keyDown && coordinate.y < mazeHeight - (int)(mazeHeight/numRows)) pacman.moveDown();
                    if (keyLeft && coordinate.x > 0) pacman.moveLeft();
                    if (keyRight && coordinate.x < mazeWidth - (int)(mazeWidth/numColumns)) pacman.moveRight();

                    graphicsContext.clearRect(0, 0,640,  640);
                    drawPacman();
                }
            }
        };
        timer.start();
    }
    private void initImages(){
        int cellSize = (int)(mazeHeight / numRows);
        wallImage = new Image(getClass().getResourceAsStream("images/brick_wall.png"), cellSize, cellSize, false, false);
        pacmanImage = new Image(getClass().getResourceAsStream("images/pacman.gif"), cellSize, cellSize, false, false);
    }
    public void drawPacman(){
        Pacman.Coordinate coordinate = pacman.getLocation();
        graphicsContext.drawImage(pacmanImage, coordinate.x, coordinate.y);
    }
}
