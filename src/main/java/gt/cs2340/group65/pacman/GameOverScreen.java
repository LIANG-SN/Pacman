package gt.cs2340.group65.pacman;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverScreen {
    public void start() throws IOException {

        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 45));

        Text descriptionText = new Text("You have lost all your lives...");
        descriptionText.setFont(Font.font("verdana", FontPosture.REGULAR, 15));


        Image yellowPacmanImg = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/endScreenGif.gif"));
        ImageView yellowPacmanImgView = new ImageView(yellowPacmanImg);
        yellowPacmanImgView.setX(2);
        yellowPacmanImgView.setY(2);
        yellowPacmanImgView.setFitWidth(250);
        yellowPacmanImgView.setPreserveRatio(true);


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
        vBox.getChildren().add(gameOverText);
        vBox.getChildren().add(descriptionText);
        vBox.getChildren().add(yellowPacmanImgView);
        vBox.getChildren().add(startOverButton);

        HBox exitButtonHbox = new HBox();
        exitButtonHbox.getChildren().add(exitButton);
        exitButtonHbox.setAlignment(Pos.CENTER_RIGHT);


        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 20, 10, 20));
        border.setCenter(vBox);
        border.setBottom(exitButtonHbox);


        Scene scene = new Scene(border, 550, 400);
        App.setScene(scene);
    }

}
