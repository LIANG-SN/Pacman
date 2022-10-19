package gt.cs2340.group65.pacman;

import javafx.scene.image.ImageView;

class Pacman extends ImageView {
    private int score = 0;
    private int playerLifes;
    private Maze maze;
    private Coordinate initialCoordinate;

    public Pacman(Coordinate initialCoordinate, String imagePath, int playerLifes, Maze maze) {
        super("file:" + imagePath);
        this.initialCoordinate = initialCoordinate;
        setX(initialCoordinate.x + maze.getTranslateX());
        setY(initialCoordinate.y + maze.getTranslateY());
        this.playerLifes = playerLifes;
        this.maze = maze;
        setPreserveRatio(false);
        setFitWidth(maze.getCellSize());
        setFitHeight(maze.getCellSize());
    }

    public void moveUp() {
        setY(getY() - 2);
    }

    public void moveDown() {
        setY(getY() + 2);
    }

    public void moveLeft() {
        setX(getX() - 2);
    }

    public void moveRight() {
        setX(getX() + 2);
    }

    public Coordinate getLocation() {
        return new Coordinate(getX(), getY());
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerLifes() {
        return playerLifes;
    }
}
