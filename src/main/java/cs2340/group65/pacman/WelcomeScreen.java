package cs2340.group65.pacman;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreen extends Application {
    private BorderPane root;
    
    @Override
    public void start(Stage primaryStage) {
        Button start = new Button("Start Game");
        Font font1 = Font.font("Courier New", FontWeight.BOLD, 36);
        start.setFont(font1);
        Button quit = new Button("Exit Game");
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: skyblue;");
        root.setCenter(start);
        root.setBottom(quit);

        InputStream stream = null;
        try {
            stream = new FileInputStream("pacman.gif");
            Image image = new Image(stream);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            root.setTop(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                BorderPane load = new BorderPane();
                load.setPrefSize(100, 100);
                Text t = new Text (10, 20, "LOADING PACMAN...");
                t.setFont(Font.font ("Verdana", 20));
                t.setFill(Color.DARKRED);
                load.setCenter(t);
                root.setCenter(load);

                
    }});

        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            BorderPane load = new BorderPane();
            load.setPrefSize(100, 100);
            Text t = new Text (10, 20, "EXITING PACMAN...");
            t.setFont(Font.font ("Verdana", 20));
            t.setFill(Color.DARKRED);
            load.setCenter(t);
            root.setCenter(load);
            
            new Timer().schedule(new TimerTask() {
                public void run () { System.exit(0); }
            }, 200);
    }});

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Play PACMAN");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
