package cs2340.group65.pacman;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class Monster extends ImageView {
    private double xDirection;
    private double yDirection;

    private GameScreen game;


    public Monster (GameScreen game) {
        super("file:BlueGhost.png");
        this.game = game;
        this.setPreserveRatio(true);
        this.setFitWidth(getImage().getWidth());
        this.xDirection = 2;
        this.yDirection = 2;
    }

    /**
     * Update the position of the Monster.
     */
    public void update() {
        Bounds monsterBounds = getBoundsInParent();
        Bounds gameBounds = game.getGameBounds();
        if (monsterBounds.getMaxX() > gameBounds.getMaxX()) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMinX() < gameBounds.getMinX()) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMaxY() > gameBounds.getMaxY()) {
            yDirection *= -1.0;      // change y direction
        } else if (monsterBounds.getMinY() < gameBounds.getMinY()) {
            yDirection *= -1.0;      // change y direction

        }

        setX(getX() + xDirection);   // move this monster
        setY(getX() + yDirection);
    }

}
