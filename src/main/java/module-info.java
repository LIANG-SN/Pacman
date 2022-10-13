module cs2340.group65.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs2340.group65.pacman to javafx.fxml;
    exports cs2340.group65.pacman;
}