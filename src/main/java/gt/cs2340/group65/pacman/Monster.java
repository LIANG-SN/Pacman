/**
 * Temporary file to test new move method
 */

package gt.cs2340.group65.pacman;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Monster extends ImageView {
    private double xDirection;
    private double yDirection;

    private boolean isSpawned;

    private Maze maze;

    private int stepSize = 3;
    private int markedNumber;


    public Monster(Maze maze, Coordinate startLocation, String imagePath, boolean spawned, int markedNumber) {
        super(imagePath);
        this.maze = maze;
        this.isSpawned = spawned;
        this.setPreserveRatio(false);
        this.setFitWidth(maze.getCellSize());
        this.setFitHeight(maze.getCellSize());
        this.markedNumber = markedNumber;
        // temp location
        setX(startLocation.x + maze.getTranslateX());
        setY(startLocation.y + maze.getTranslateY());
        setRandomDirection();
    }

    /**
     * Update the position of the Monster.
     */
    public void update() {
        for (int i = 0; i < stepSize; i++) {
            Bounds monsterBounds = getBoundsInParent();
            if (monsterBounds.getMaxX() > maze.getWidth() + maze.getTranslateX()) {
                setRandomDirection();      // change x direction
            } else if (monsterBounds.getMinX() < 0 + maze.getTranslateX()) {
                setRandomDirection();      // change x direction
            } else if (monsterBounds.getMaxY() > maze.getHeight() + maze.getTranslateY()) {
                setRandomDirection();      // change y direction
            } else if (monsterBounds.getMinY() < 0 + maze.getTranslateY()) {
                setRandomDirection();      // change y direction
            }

            if ((!maze.checkUp(new Coordinate(getX(), getY())) && yDirection < 0)
                || (!maze.checkDown(new Coordinate(getX(), getY())) && yDirection > 0)
                || (!maze.checkLeft(new Coordinate(getX(), getY())) && xDirection < 0)
                || (!maze.checkRight(new Coordinate(getX(), getY())) && xDirection > 0)) {
                setRandomDirection();
            }

            setX(getX() + xDirection);   // move this monster
            setY(getY() + yDirection);
        }
    }

    public void setRandomDirection() {
        xDirection = 0;
        yDirection = 0;
        List<Integer> directions = new ArrayList<>();
        if (maze.checkUp(new Coordinate(getX(), getY()))) {
            directions.add(3);
        }
        if (maze.checkDown(new Coordinate(getX(), getY()))) {
            directions.add(2);
        }
        if (maze.checkLeft(new Coordinate(getX(), getY()))) {
            directions.add(1);
        }
        if (maze.checkRight(new Coordinate(getX(), getY()))) {
            directions.add(0);
        }
        if (directions.size() > 0){
            int randomDirection = directions.get((int) (Math.random()*directions.size()));
            switch (randomDirection) {
            case 0:
                xDirection = 1;
                break;
            case 1:
                xDirection = -1;
                break;
            case 2:
                yDirection = 1;
                break;
            case 3:
                yDirection = -1;
                break;
            default:
            }
        }
    }

    public Coordinate getLocation() {
        return new Coordinate(getX(), getY());
    }

    public boolean isSpawned() {
        return this.isSpawned;
    }

    public void setSpawned(boolean spawned) {
        this.isSpawned = spawned;
    }

    public int getMarkedNumber () {
        return this.markedNumber;
    }
}