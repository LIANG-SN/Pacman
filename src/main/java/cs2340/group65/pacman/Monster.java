package cs2340.group65.pacman;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;


public class Monster extends ImageView {
    private double xDirection;
    private double yDirection;

//    private GameScreen game;
    private int mazeWidth, mazeHeight;


    public Monster (/*GameScreen game*/int mazeWidth, int mazeHeight) {
        super("file:BlueGhost.png");
//        this.game = game;
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
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
//        Bounds gameBounds = game.getGameBounds();
        if (monsterBounds.getMaxX() > mazeWidth) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMinX() < 0) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMaxY() > mazeHeight) {
            yDirection *= -1.0;      // change y direction
        } else if (monsterBounds.getMinY() < 0) {
            yDirection *= -1.0;      // change y direction

        }

        setX(getX() + xDirection);   // move this monster
        setY(getX() + yDirection);
    }
}
