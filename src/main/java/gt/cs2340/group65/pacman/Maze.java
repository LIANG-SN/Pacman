package gt.cs2340.group65.pacman;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze {
    private int width;
    private int height;
    private int numRows = 16;
    private int numColumns = 16;
    private int translateX;
    private int translateY;
    private char[][] grid;
    private ImageView[][] imageViews;
    private Pellet[][] pellets;

    public Maze(int width, int height, int numRows, int numColumns,
                int translateX, int translateY, Group root,
                Coordinate pacmanStartLocation, Coordinate enemyStartLocation) {
        this.width = width;
        this.height = height;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.translateX = translateX;
        this.translateY = translateY;
        assert height / numRows == width / numColumns;
        grid = new char[numRows][numColumns];
        imageViews = new ImageView[numRows][numColumns];
        pellets = new Pellet[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                grid[i][j] = '1';

                imageViews[i][j] = new ImageView(
                    "file:src/main/resources/gt/cs2340/group65/pacman/images/brick_wall.png");
                pellets[i][j] = new Pellet("src/main/resources/gt/cs2340/group65/pacman/images/normalPelle.png", false, 10);
            }
        }
        generateMaze(grid, (int) (pacmanStartLocation.x / getCellSize()),
            (int) (pacmanStartLocation.y / getCellSize()));
        grid[(int) (enemyStartLocation.x / getCellSize())]
            [(int) (enemyStartLocation.y / getCellSize())] = '0';
        removeDeadend();
        grid[(int) (enemyStartLocation.x / getCellSize())]
            [(int) (enemyStartLocation.y / getCellSize())] = '2';
        grid[(int) (pacmanStartLocation.x / getCellSize())]
            [(int) (pacmanStartLocation.y / getCellSize())] = '2';
        pellets[(int) (enemyStartLocation.x / getCellSize())]
               [(int) (enemyStartLocation.y / getCellSize())] = null;
        pellets[(int) (pacmanStartLocation.x / getCellSize())]
                [(int) (pacmanStartLocation.y / getCellSize())] = null;
        displayMaze(root);
    }
    private void removeDeadend(){
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int count = 0;
                if (i + 1 < grid.length)
                {
                    count += Integer.parseInt(Character.toString(grid[i+1][j]));
                }
                else {
                    count += 1;
                }
                if (i-1 >=0){
                    count += Integer.parseInt(Character.toString(grid[i-1][j]));
                }
                else{
                    count += 1;
                }
                if (j+1 < grid[0].length){
                    count += Integer.parseInt(Character.toString(grid[i][j+1]));
                }
                else {
                    count += 1;
                }
                if (j-1 >=0){
                    count += Integer.parseInt(Character.toString(grid[i][j-1]));
                }
                else {
                    count += 1;
                }

                if (count >= 3) {
                    List<int[]> moveXY = new ArrayList<>(List.of(new int[] {1, 0}, new int[] {0, 1},
                        new int[] {-1, 0}, new int[] {0, -1}));
                    Collections.shuffle(moveXY);
                    for (int t = 0; t < 4; t++) {
                        if (i + moveXY.get(t)[0] >= 0 && i + moveXY.get(t)[0] < grid.length
                            && j + moveXY.get(t)[1]  >= 0 && j + moveXY.get(t)[1] < grid[0].length
                            && grid[i + moveXY.get(t)[0]][j + moveXY.get(t)[1]] == '1'){
                            grid[i + moveXY.get(t)[0]][j + moveXY.get(t)[1]] = '0';
                            break;
                        }
                    }
                }
            }
        }
    }
    private void generateMaze(char[][] grid, int x, int y) {
        List<int[]> moveXY = new ArrayList<>(List.of(new int[] {1, 0}, new int[] {0, 1},
            new int[] {-1, 0}, new int[] {0, -1}));
        Collections.shuffle(moveXY);
        for (int i = 0; i < 4; i++) {
            if (x + moveXY.get(i)[0] * 2 >= 0 && x + moveXY.get(i)[0] * 2 < grid.length
                && y + moveXY.get(i)[1] * 2 >= 0 && y + moveXY.get(i)[1] * 2 < grid[0].length
                && (grid[x + moveXY.get(i)[0] * 2][y + moveXY.get(i)[1] * 2] == '1'
                || Math.random() < 0.1)) {
                grid[x + moveXY.get(i)[0] * 2][y + moveXY.get(i)[1] * 2] = '0';
                grid[x + moveXY.get(i)[0]][y + moveXY.get(i)[1]] = '0';
                generateMaze(grid, x + moveXY.get(i)[0] * 2, y + moveXY.get(i)[1] * 2);
            }
        }
    }

    public void displayMaze(Group root) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (grid[i][j] == '1') {
                    imageViews[i][j].setPreserveRatio(false);
                    imageViews[i][j].setFitWidth(getCellSize());
                    imageViews[i][j].setFitHeight(getCellSize());
                    imageViews[i][j].setX(j * getCellSize() + translateX);
                    imageViews[i][j].setY(i * getCellSize() + translateY);
                    root.getChildren().add(imageViews[i][j]);
                }

                if (grid[i][j] == '0') {
                    double random = Math.random();
                    if (random > 0.10) {
                        pellets[i][j].setPreserveRatio(false);
                        pellets[i][j].setFitWidth(getCellSize());
                        pellets[i][j].setFitHeight(getCellSize());
                        pellets[i][j].setX(j * getCellSize() + translateX);
                        pellets[i][j].setY(i * getCellSize() + translateY);
                        root.getChildren().add(pellets[i][j]);
                    } else {
                        pellets[i][j] = new Pellet(
                            "src/main/resources/gt/cs2340/group65/pacman/images/specialPelle.png", true, 100);
                        pellets[i][j].setPreserveRatio(false);
                        pellets[i][j].setFitWidth(getCellSize());
                        pellets[i][j].setFitHeight(getCellSize());
                        pellets[i][j].setX(j * getCellSize() + translateX);
                        pellets[i][j].setY(i * getCellSize() + translateY);
                        root.getChildren().add(pellets[i][j]);
                    }
                }
            }
        }
    }

    public int removePelle(Group root, Coordinate playerLocation) {
        int row = (int) ((playerLocation.y - translateY + getCellSize() / 2) / (getCellSize()));
        int col = (int ) ((playerLocation.x - translateX + getCellSize() / 2) / (getCellSize()));
        int point = 0;
        if (row < numRows && row >= 0 && col < numColumns && col >= 0) {
            if (checkPelle(row, col)) {
                point = getPelleScore(row, col);
                pellets[row][col].setPreserveRatio(false);
                pellets[row][col].setFitWidth(getCellSize());
                pellets[row][col].setFitHeight(getCellSize());
                root.getChildren().remove(pellets[row][col]);
                pellets[row][col] = null;
            }
        }
        return point;
    }
    public boolean checkPelle(int x, int y) {
        return pellets[x][y] != null;
    }

    private int getPelleScore(int x, int y) {
        return pellets[x][y].getPoint();
    }

    public Coordinate getPelleLocation(int x, int y) {
        return pellets[x][y].getLocation();
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

    public boolean checkWall(Coordinate p){
        // p has not been normalized
        int grid_column = (int) ((p.x - translateX + getCellSize() / 2) / (getCellSize()));
        int grid_row = (int) ((p.y - translateY + getCellSize() / 2) / (getCellSize()));
        if (grid[grid_row][grid_column] == '1') {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkUp(Coordinate p) {
        Coordinate lt_moved = new Coordinate(p.x - getCellSize() / 2.3,
            p.y - getCellSize() / 2.3 - 1);
        Coordinate rt_moved = new Coordinate(p.x + getCellSize() / 2.3,
            p.y - getCellSize() / 2.3 - 1);

        if (p.y < 0 + getTranslateY() || checkWall(lt_moved) || checkWall(rt_moved)) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkDown(Coordinate p) {
        Coordinate ld_moved = new Coordinate(p.x - getCellSize() / 2.3,
            p.y + getCellSize() / 2.3 + 1);
        Coordinate rd_moved = new Coordinate(p.x + getCellSize() / 2.3,
            p.y + getCellSize() / 2.3 + 1);

        if (p.y > getHeight() + getTranslateY() - getCellSize()
            || checkWall(ld_moved) || checkWall(rd_moved)) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkLeft(Coordinate p) {
        Coordinate lt_moved = new Coordinate(p.x - getCellSize() / 2.3 - 1,
            p.y - getCellSize() / 2.3);
        Coordinate ld_moved = new Coordinate(p.x - getCellSize() / 2.3 - 1,
            p.y + getCellSize() / 2.3);

        if (p.x < 0 + getTranslateX() || checkWall(lt_moved) || checkWall(ld_moved)) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkRight(Coordinate p) {
        Coordinate rt_moved = new Coordinate(p.x + getCellSize() / 2.3 + 1,
            p.y - getCellSize() / 2.3);
        Coordinate rd_moved = new Coordinate(p.x + getCellSize() / 2.3 + 1,
            p.y + getCellSize() / 2.3);

        if (p.x > getWidth() + getTranslateX() - getCellSize()
            || checkWall(rt_moved) || checkWall(rd_moved)) {
            return false;
        }
        else {
            return true;
        }
    }
}
