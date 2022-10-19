package gt.cs2340.group65.pacman;

import java.io.IOException;
import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ConfigurationScreen {
    private String name;
    private String difficulty;
    private int startingLives;
    private String pacmanImageSelected;

    public void start() throws IOException {
        //creating gridPane size(col:3 row:4) index(colIndex:2 rowIndex:3)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        //Welcome Label
        Label configLabel = new Label("Welcome To The Configuration Screen!");
        grid.add(configLabel, 1, 0);

        //User Selection label
        Label userSelectionLabel = new Label(newUserSelection());
        userSelectionLabel.setMinHeight(50);
        grid.add(userSelectionLabel, 1, 4);

        //Name
        Label nameLabel = new Label("Enter Your Name:");
        TextField nameField = new TextField();
        VBox nameBox = new VBox();
        nameBox.getChildren().addAll(nameLabel, nameField);
        grid.add(nameBox, 1, 1);

        //Name logic (listens for change in textfield, sets name to textfield)
        nameField.textProperty().addListener((obs, oldText, newText) -> {
            System.out.println("Text changed from " + oldText + " to " + newText);
            name = newText;
            userSelectionLabel.setText(newUserSelection());
        });

        //Difficulty
        Label difficultyLabel = new Label("Choose The Difficulty:");
        Label difficultyDescriptionLabel =
            new Label("Easy = 10 lives; Medium = 5 lives; Hard = 2 lives");
        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button hardButton = new Button("Hard");
        HBox difficultyButtonsBox = new HBox();
        difficultyButtonsBox.getChildren().addAll(easyButton, mediumButton, hardButton);
        VBox difficultyBox = new VBox();
        difficultyBox.getChildren()
            .addAll(difficultyLabel, difficultyDescriptionLabel, difficultyButtonsBox);
        grid.add(difficultyBox, 1, 2);

        //Difficulty logic
        EventHandler<ActionEvent> difficultyHandler = new EventHandler<>() {
            @Override
            public void handle(final ActionEvent event) {
                if (event.getSource() == easyButton) {
                    difficulty = "Easy";
                    startingLives = 10;
                    System.out.println("New difficulty" + difficulty);
                } else if (event.getSource() == mediumButton) {
                    difficulty = "Medium";
                    startingLives = 5;
                    System.out.println("New difficulty" + difficulty);
                } else if (event.getSource() == hardButton) {
                    difficulty = "Hard";
                    startingLives = 2;
                    System.out.println("New difficulty" + difficulty);
                }
                userSelectionLabel.setText(newUserSelection());
            }
        };

        easyButton.setOnAction(difficultyHandler);
        mediumButton.setOnAction(difficultyHandler);
        hardButton.setOnAction(difficultyHandler);


        //Choose pacman character sprite
        Label pacmanChooserLabel = new Label("Choose Your Pacman Character Sprite:");

        Image yellowPacmanImg = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/yellowPacman.png"));
        ImageView yellowPacmanImgView = new ImageView(yellowPacmanImg);
        yellowPacmanImgView.setX(2);
        yellowPacmanImgView.setY(2);
        yellowPacmanImgView.setFitWidth(70);
        yellowPacmanImgView.setPreserveRatio(true);
        Button yellowPacmanButton = new Button("Yellow Pacman");
        yellowPacmanButton.setGraphic(yellowPacmanImgView);

        Image bluePacmanImg = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/bluePacman.png"));
        ImageView bluePacmanImgView = new ImageView(bluePacmanImg);
        bluePacmanImgView.setX(2);
        bluePacmanImgView.setY(2);
        bluePacmanImgView.setFitWidth(70);
        bluePacmanImgView.setPreserveRatio(true);
        Button bluePacmanButton = new Button("Blue Pacman");
        bluePacmanButton.setGraphic(bluePacmanImgView);

        Image pinkPacmanImg = new Image(
            new FileInputStream("src/main/resources/gt/cs2340/group65/pacman/pinkPacman.png"));
        ImageView pinkPacmanImgView = new ImageView(pinkPacmanImg);
        pinkPacmanImgView.setX(2);
        pinkPacmanImgView.setY(2);
        pinkPacmanImgView.setFitWidth(70);
        pinkPacmanImgView.setPreserveRatio(true);
        Button pinkPacmanButton = new Button("Pink Pacman");
        pinkPacmanButton.setGraphic(pinkPacmanImgView);

        HBox pacmanButtonsBox = new HBox();
        pacmanButtonsBox.getChildren()
            .addAll(yellowPacmanButton, bluePacmanButton, pinkPacmanButton);
        VBox pacmanChooserBox = new VBox();
        pacmanChooserBox.getChildren().addAll(pacmanChooserLabel, pacmanButtonsBox);
        grid.add(pacmanChooserBox, 1, 3);

        //Choose pacman character sprite logic
        EventHandler<ActionEvent> pacmanChooserHandler = new EventHandler<>() {
            @Override
            public void handle(final ActionEvent event) {
                if (event.getSource() == yellowPacmanButton) {
                    pacmanImageSelected = "Yellow Pacman";
                    System.out.println("Pacman is " + pacmanImageSelected);
                } else if (event.getSource() == bluePacmanButton) {
                    pacmanImageSelected = "Blue Pacman";
                    System.out.println("Pacman is " + pacmanImageSelected);
                } else if (event.getSource() == pinkPacmanButton) {
                    pacmanImageSelected = "Pink Pacman";
                    System.out.println("Pacman is " + pacmanImageSelected);
                }
                userSelectionLabel.setText(newUserSelection());
            }
        };

        yellowPacmanButton.setOnAction(pacmanChooserHandler);
        bluePacmanButton.setOnAction(pacmanChooserHandler);
        pinkPacmanButton.setOnAction(pacmanChooserHandler);


        //creating screen linking btn
        Button backBtn = new Button("Back");
        Button playBtn = new Button("Play");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                App.startWelcomeScreen();
            }
        });

        //play button
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //App.setRoot("{next screen}");//using xml, replace {} when ready

                //handling for when user has not inputted valid name, chosen a player model, or selected a difficulty
                try {
                    if (name == null || name.isBlank() || difficulty == null
                        || pacmanImageSelected == null) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Input Error!!");
                    a.setContentText(
                        "There is an input error. Please make sure you have inputted a valid name, chosen a player model, and selected a difficulty.");
                    a.setHeight(300);
                    a.setWidth(400);
                    a.showAndWait();
                }
                String color = pacmanImageSelected.split("\\s+")[0];
                color = color.toLowerCase();
                String imagePath =
                    "src/main/resources/gt/cs2340/group65/pacman/" + color + "Pacman.png";
                System.out.println(imagePath);
                App.startGameScreen(name, imagePath, startingLives);
            }
        });
        //adding elements to grid pane
        grid.add(backBtn, 0, 5);
        grid.add(playBtn, 2, 5);
        //creating scene
        Scene scene = new Scene(grid);
        App.setScene(scene);
    }

    public String newUserSelection() {
        String userSelection = "Here is what you have inputted/selected: ";
        if (name == null || name.isBlank()) {
            userSelection += "\nYou haven't inputted a name yet";
        } else {
            userSelection += "\nThe name you inputted is: " + name;
        }
        if (difficulty == null) {
            userSelection += "\nYou haven't chosen a difficulty yet";
        } else {
            userSelection +=
                "\nThe difficulty you chose is: " + difficulty + ". You will start with "
                    + startingLives + " lives.";
        }
        if (pacmanImageSelected == null) {
            userSelection += "\nYou haven't chosen a pacman character yet";
        } else {
            userSelection += "\nThe pacman character you chose is:  " + pacmanImageSelected;
        }
        return userSelection;
    }
}
