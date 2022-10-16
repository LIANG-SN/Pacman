package cs2340.group65.pacman;

import java.io.IOException;
import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ConfigScreen {
    /**
     * config screen setup
     * @return new stage to display
     */
    public Stage display() throws IOException {
        //creating gridPane size(col:3 row:4) index(colIndex:2 rowIndex:3)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        //creating config menu
        Label label = new Label("WELCOME!");
        Image pacman = new Image(new FileInputStream("src/main/resources/cs2340/group65/pacman/pacman.png")); //replace {} with image path variable
        ImageView window = new ImageView(pacman);//set height/width if needed
        Button prevBtn = new Button("<-");
        Button nextBtn = new Button("->");
        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //set image display on previous gif
            }
        });
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //set image display on next gif
            }
        });
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
        grid.add(label, 1, 0);
        grid.add(window, 1, 1);
        grid.add(prevBtn, 0, 1);
        grid.add(nextBtn, 2, 1);
        grid.add(backBtn, 0, 3);
        grid.add(playBtn, 2, 3);
        //creating scene and return
        Scene scene = new Scene(grid);
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    /** screen switching using xml
    protected void switchTo{screen}() throws IOException {
        App.setRoot("{screen}");
    }
    protected void switchTo{screen2}() throws IOException {
        App.setRoot("{screen2}");
    **/
}
