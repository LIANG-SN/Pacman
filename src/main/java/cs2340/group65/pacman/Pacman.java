package cs2340.group65.pacman;

class Pacman {
    public static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    private Coordinate coordinate;
    public Pacman(Coordinate initialCoordinate)
    {
        coordinate = initialCoordinate;
    }

    public void moveUp(){ coordinate.y -= 2; }
    public void moveDown(){ coordinate.y += 2; }
    public void moveLeft() { coordinate.x -= 2; }
    public void moveRight() { coordinate.x += 2; }
    public Coordinate getLocation(){
        return coordinate;
    }
}
