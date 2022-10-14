package cs2340.group65.pacman;

public class MainGameScreen extends GameScreen {
    private Monster ghost;

    private GameScreen game;

    public MainGameScreen (int width, int height) {
        super(width, height, 60);
        ghost = new Monster(this);
    }
    @Override
    protected void init() {
        getChildren().addAll(ghost);
        ghost.setX(0);
        ghost.setY(0);
    }
    @Override
    protected void update() {
        ghost.update();
    }
}
