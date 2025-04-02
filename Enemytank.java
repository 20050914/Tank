package TankGames.Tankgame4;

import java.util.Vector;

@SuppressWarnings({"all"})
public class Enemytank extends Tank {
    public Enemytank(int x, int y) {
        super(x, y);
    }
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();

}
