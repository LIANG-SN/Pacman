package cs2340.group65.pacman;

import javafx.scene.image.ImageView;

class Pacman extends ImageView {
    private int score = 0;
    private int playerLifes;

    public Pacman(Coordinate initialCoordinate, String imagePath, int playerLifes) {
        super("file:" + imagePath);
        setX(initialCoordinate.x);
        setY(initialCoordinate.y);
        this.playerLifes = playerLifes;
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

    public static class Coordinate {
        public double x;
        public double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
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
