package gt.cs2340.group65.pacman;

public class AttackPellet extends Pellet{
    public AttackPellet(String imagePath, boolean specialPelle){
        // trick: use point = 1 to represent this attack pellet
        super(imagePath, specialPelle, 1);
    }
}
