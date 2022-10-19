/**
 * Temporary file to test new move method
 */

package gt.cs2340.group65.pacman;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Monster extends ImageView {
    private double xDirection;
    private double yDirection;

    private Maze maze;

    private int changeDirectionCounter = 0;


    public Monster(Maze maze, Coordinate startLocation) {
        super("file:src/main/resources/gt/cs2340/group65/pacman/images/BlueGhost.png");
        this.maze = maze;
        this.setPreserveRatio(false);
        this.setFitWidth(maze.getCellSize());
        this.setFitHeight(maze.getCellSize());
        // temp location
        setX(startLocation.x + maze.getTranslateX());
        setY(startLocation.y + maze.getTranslateY());
        setRandomDirection();
    }

    /**
     * Update the position of the Monster.
     */
    public void update() {
        Bounds monsterBounds = getBoundsInParent();
        if (monsterBounds.getMaxX() > maze.getWidth() + maze.getTranslateX()) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMinX() < 0 + maze.getTranslateX()) {
            xDirection *= -1.0;      // change x direction
        } else if (monsterBounds.getMaxY() > maze.getHeight() + maze.getTranslateY()) {
            yDirection *= -1.0;      // change y direction
        } else if (monsterBounds.getMinY() < 0 + maze.getTranslateY()) {
            yDirection *= -1.0;      // change y direction
        }

        setX(getX() + xDirection);   // move this monster
        setY(getY() + yDirection);

        if (++changeDirectionCounter == 10) {
            changeDirectionCounter = 0;
            if (Math.random() <= 0.4) {
                setRandomDirection();
            }
        }
    }

    public void setRandomDirection() {
        xDirection = 0;
        yDirection = 0;
        int randomDirection = (int) (Math.random() * 4);
        switch (randomDirection) {
        case 0:
            xDirection = 2;
            break;
        case 1:
            xDirection = -2;
            break;
        case 2:
            yDirection = 2;
            break;
        case 3:
            yDirection = -2;
            break;
        default:
        }
    }
}