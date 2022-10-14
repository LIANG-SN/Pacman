package cs2340.group65.pacman;

import javafx.scene.image.ImageView;

class Pacman extends ImageView {
    public static class Coordinate{
        double x;
        double y;
        public Coordinate(double x, double y){
            this.x=x;
            this.y=y;
        }
    }
    public Pacman(Coordinate initialCoordinate)
    {
        super("file:src/main/resources/cs2340/group65/pacman/images/pacman.gif");
        setX(initialCoordinate.x);
        setY(initialCoordinate.y);
    }

    public void moveUp(){ setY(getY() - 2); }
    public void moveDown(){ setY(getY() + 2); }
    public void moveLeft() { setX(getX() - 2); }
    public void moveRight() { setX(getX() + 2); }
    public Coordinate getLocation(){
        return new Coordinate(getX(), getY());
    }
}
