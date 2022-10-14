/**
 * Temporary file to test new move method
 */

package cs2340.group65.pacman;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Monster2 extends ImageView {
    private double xDirection;
    private double yDirection;

    //    private GameScreen game;
    protected int mazeWidth, mazeHeight;

    private int changeDirectionCounter = 0;


    public Monster2 (/*GameScreen game*/int mazeWidth, int mazeHeight) {
        super("file:src/main/resources/cs2340/group65/pacman/images/BlueGhost.png");
//        this.game = game;
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.setPreserveRatio(true);
        this.setFitWidth(getImage().getWidth());
        setRandomDirection();
//        xDirection = 1;
//        yDirection = 1;
    }

    /**
     * Update the position of the Monster.
     */
    public void update(){
        Bounds monsterBounds = getBoundsInParent();
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
        setY(getY() + yDirection);

        if(++changeDirectionCounter==50)
        {
            changeDirectionCounter=0;
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