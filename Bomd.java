package TankGames.Tankgame4;
@SuppressWarnings({"all"})
//坦克爆炸
public class Bomd {
    int x,y;
    int live = 9;
    boolean isLive = true;

    public Bomd(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void LiveDown(){
        if(live>0){
            live--;
        }
        else {
            isLive=false;
        }
    }
}
