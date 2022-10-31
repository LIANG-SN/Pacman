package gt.cs2340.group65.pacman;

import javafx.scene.image.ImageView;

public class Pelle extends ImageView {

    private boolean isSpecial;
    private int point;

    private Coordinate location;

    public Pelle (String imagePath, boolean specialPelle, int point) {
        super("file:" + imagePath);
        this.point = point;
        isSpecial = specialPelle;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }

    public int getPoint () {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Coordinate getLocation() {
        return new Coordinate(getX(), getY());
    }
}
