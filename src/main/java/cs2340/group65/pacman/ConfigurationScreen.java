package cs2340.group65.pacman;

import java.io.IOException;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ConfigurationScreen extends Application {

    public void start(Stage stage) throws IOException {
        //creating gridPane size(col:3 row:4) index(colIndex:2 rowIndex:3)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        //Welcome Label
        Label configLabel = new Label("Welcome To The Configuration Screen!");
        grid.add(configLabel, 1, 0);

        //Name
        Label nameLabel = new Label("Enter Your Name:");
        TextField nameField = new TextField();
        VBox nameBox = new VBox();
        nameBox.getChildren().addAll(nameLabel, nameField);
        grid.add(nameBox, 1, 1);

        //Difficulty
        Label difficultyLabel = new Label("Choose The Difficulty:");
        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button hardButton = new Button("Hard");
        HBox difficultyButtonsBox = new HBox();
        difficultyButtonsBox.getChildren().addAll(easyButton, mediumButton, hardButton);
        VBox difficultyBox = new VBox();
        difficultyBox.getChildren().addAll(difficultyLabel, difficultyButtonsBox);
        grid.add(difficultyBox, 1, 2);

        //Choose pacman character sprite
        Label pacmanChooserLabel = new Label("Choose Your Pacman Character Sprite:");

        Image yellowPacmanImg = new Image(new FileInputStream("src/main/resources/cs2340/group65/pacman/yellowPacman.png"));
        ImageView yellowPacmanImgView = new ImageView(yellowPacmanImg);
        yellowPacmanImgView.setX(2);
        yellowPacmanImgView.setY(2);
        yellowPacmanImgView.setFitWidth(70);
        yellowPacmanImgView.setPreserveRatio(true);
        Button yellowPacmanButton = new Button("Yellow Pacman");
        yellowPacmanButton.setGraphic(yellowPacmanImgView);

        Image bluePacmanImg = new Image(new FileInputStream("src/main/resources/cs2340/group65/pacman/bluePacman.png"));
        ImageView bluePacmanImgView = new ImageView(bluePacmanImg);
        bluePacmanImgView.setX(2);
        bluePacmanImgView.setY(2);
        bluePacmanImgView.setFitWidth(70);
        bluePacmanImgView.setPreserveRatio(true);
        Button bluePacmanButton = new Button("Blue Pacman");
        bluePacmanButton.setGraphic(bluePacmanImgView);

        Image pinkPacmanImg = new Image(new FileInputStream("src/main/resources/cs2340/group65/pacman/pinkPacman.png"));
        ImageView pinkPacmanImgView = new ImageView(pinkPacmanImg);
        pinkPacmanImgView.setX(2);
        pinkPacmanImgView.setY(2);
        pinkPacmanImgView.setFitWidth(70);
        pinkPacmanImgView.setPreserveRatio(true);
        Button pinkPacmanButton = new Button("Pink Pacman");
        pinkPacmanButton.setGraphic(pinkPacmanImgView);

        HBox pacmanButtonsBox = new HBox();
        pacmanButtonsBox.getChildren().addAll(yellowPacmanButton, bluePacmanButton, pinkPacmanButton);
        VBox pacmanChooserBox = new VBox();
        pacmanChooserBox.getChildren().addAll(pacmanChooserLabel, pacmanButtonsBox);
        grid.add(pacmanChooserBox, 1, 3);



        //creating screen linking btn
        Button backBtn = new Button("Back");
        Button playBtn = new Button("Play");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //App.setRoot("{previous screen}");//using xml, replace {} when ready
            }
        });
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //App.setRoot("{next screen}");//using xml, replace {} when ready
            }
        });
        //adding elements to grid pane
        grid.add(backBtn, 0, 4);
        grid.add(playBtn, 2, 4);
        //creating scene
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    /** screen switching using xml
    protected void switchTo{screen}() throws IOException {
        App.setRoot("{screen}");
    }
    protected void switchTo{screen2}() throws IOException {
        App.setRoot("{screen2}");
    **/
}
