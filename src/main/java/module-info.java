module gt.cs2340.group65.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens gt.cs2340.group65.pacman to javafx.fxml;
    exports gt.cs2340.group65.pacman;
}