package gt.cs2340.group65.pacman;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;

public class WinScreen {
    public void start() throws IOException {
        Text winText = new Text("You won!!!");
        winText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,45));
        winText.setFill(Color.DARKMAGENTA);

        Label livesLost = new Label();
        if (ConfigurationScreen.startingLives != 1) {
            livesLost.setText("You have lost: "  + (ConfigurationScreen.startingLives - GameScreenController.pacman.getPlayerLifes()) + " lives");
        } else {
            livesLost.setText("You have lost: "  + (ConfigurationScreen.startingLives - GameScreenController.pacman.getPlayerLifes()) + " life");
        }

        Label score = new Label();
        score.setText("Final Score: " + GameScreenController.pacman.getScore());
        score.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        Label pelletsLeft = new Label();
        if (Maze.numPellets != 1) {
            pelletsLeft.setText("You have "  + Maze.numPellets + " pellets left");
        } else {
            pelletsLeft.setText("You have "  + Maze.numPellets + " pellet left");
        }

        Image yellowPacmanImg = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/endScreenGif.gif"));
        ImageView yellowPacmanImgView = new ImageView(yellowPacmanImg);
        yellowPacmanImgView.setX(2);
        yellowPacmanImgView.setY(2);
        yellowPacmanImgView.setFitWidth(250);
        yellowPacmanImgView.setPreserveRatio(true);

        Image confetti = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/confetti.gif"));
        ImageView confettiImgView = new ImageView(confetti);
        confettiImgView.setX(2);
        confettiImgView.setY(2);
        confettiImgView.setFitWidth(250);
        confettiImgView.setFitHeight(100);
        confettiImgView.setPreserveRatio(true);

        Button startOverButton = new Button("Start Over");
        startOverButton.setStyle("-fx-font-size:18");
        startOverButton.setPrefSize(120, 40);
        startOverButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                App.startWelcomeScreen();
            }
        });

        Button exitButton = new Button("Exit");

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 50, 20, 50));
        vBox.setSpacing(10);
        vBox.getChildren().add(confettiImgView);
        vBox.getChildren().add(winText);
        vBox.getChildren().add(score);
        vBox.getChildren().add(livesLost);
        vBox.getChildren().add((pelletsLeft));
        vBox.getChildren().add(yellowPacmanImgView);
        vBox.getChildren().add(startOverButton);

        HBox exitButtonHbox = new HBox();
        exitButtonHbox.getChildren().add(exitButton);
        exitButtonHbox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 10, 10, 10));
        border.setCenter(vBox);
        border.setBottom(exitButtonHbox);

        Scene scene = new Scene(border, 550, 400);
        App.setScene(scene);
    }
}
