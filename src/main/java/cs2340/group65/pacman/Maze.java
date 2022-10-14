package cs2340.group65.pacman;

public class Maze {
    private int width;
    private int height;
    private int numRows = 16;
    private int numColumns = 16;
    private int translateX;
    private int translateY;

    public Maze(int width, int height, int numRows, int numColumns,
                int translateX, int translateY) {
        this.width = width;
        this.height = height;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.translateX = translateX;
        this.translateY = translateY;
        assert height / numRows == width / numColumns;
    }

    public double getCellSize() {
        return height / numRows;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }
}
