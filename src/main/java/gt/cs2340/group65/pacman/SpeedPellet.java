package gt.cs2340.group65.pacman;

public class SpeedPellet extends Pellet{
    public SpeedPellet(String imagePath, boolean specialPellet) {
        // trick: use point = 2 to represent this attack pellet
        super(imagePath, specialPellet, 2);
    }
}
