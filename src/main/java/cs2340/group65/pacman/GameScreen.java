package cs2340.group65.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public abstract
class GameScreen extends Region {
    private final Bounds bounds;                     // game bounds
    private final Duration fpsTarget;                // target duration for game loop
    private final Timeline loop = new Timeline();    // timeline for main game loop

    private boolean initialized = false;

    public GameScreen (int width, int height, int fps) {
        super();
        setMinWidth(width);
        setMinHeight(height);
        this.bounds = new BoundingBox(0, 0, width, height);
        this.fpsTarget = Duration.millis(1000.0 / fps);
        initGameLoop();
    }

    /**
     * Initialize the main game loop.
     */
    private void initGameLoop() {
        KeyFrame updateFrame = new KeyFrame(fpsTarget, event -> {
            requestFocus();
            update();
        });
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.getKeyFrames().add(updateFrame);
    }

    /**
     * Perform one iteration of the main game loop.
     */
    protected abstract void update();

    /**
     * Initialize the game. A game may override this method to perform initialization
     * that needs to happen prior to the main game loop.
     */
    protected void init() {}

    /**
     * Setup and start the main game loop.
     */
    public final void play() {
        if (!initialized) {
            init();
            initialized = true;
        }
        loop.play();
    }

    /**
     * Get the bounds for this game that were specified when it was constructed.
     * @return bounds for this game
     */
    public final Bounds getGameBounds() {
        return bounds;
    }

}
