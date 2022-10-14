/**
 * Temporary file to test new move method
 */

package cs2340.group65.pacman;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Monster2 extends ImageView {
    private double xDirection;
    private double yDirection;

    private int mazeWidth, mazeHeight;
    private int mazeTranslateX, mazeTranslateY;

    private int changeDirectionCounter = 0;


    public Monster2 (int mazeWidth, int mazeHeight,
                     int mazeTranslateX, int mazeTranslateY,
                     double cellSize) {
        super("file:src/main/resources/cs2340/group65/pacman/images/BlueGhost.png");
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.mazeTranslateX = mazeTranslateX;
        this.mazeTranslateY = mazeTranslateY;
        this.setPreserveRatio(false);
//        this.setFitWidth(cellSize);
//        this.setFitHeight(cellSize);
        // temp location
        setX(mazeWidth / 2);
        setY(mazeTranslateY + mazeHeight / 2);
        setRandomDirection();
    }

    /**
     * Update the position of the Monster.
     */
    public void update(){
        Bounds monsterBounds = getBoundsInParent();
        if (monsterBounds.getMaxX() > mazeWidth + mazeTranslateX) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMinX() < 0 + mazeTranslateX) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMaxY() > mazeHeight + mazeTranslateY) {
            yDirection *= -1.0;      // change y direction
        } else if (monsterBounds.getMinY() < 0 + mazeTranslateY) {
            yDirection *= -1.0;      // change y direction
        }

        setX(getX() + xDirection);   // move this monster
        setY(getY() + yDirection);

        if(++changeDirectionCounter==10)
        {
            changeDirectionCounter=0;
            if (Math.random() <= 0.4)
                setRandomDirection();
        }
    }
    public void setRandomDirection()
    {
        xDirection = 0;
        yDirection = 0;
        int randomDirection = (int)(Math.random() * 4);
        switch (randomDirection){
            case 0: xDirection = 2; break;
            case 1: xDirection = -2; break;
            case 2: yDirection = 2; break;
            case 3: yDirection = -2; break;
        }
    }
}