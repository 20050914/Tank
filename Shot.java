package TankGames.Tankgame4;
@SuppressWarnings({"all"})
public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 5;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct){
                case 0: y -=speed; //子弹方向向上
                    break;
                case 1: x +=speed; //子弹方向向右
                    break;
                case 2: y +=speed; //子弹方向向下
                    break;
                case 3: x -=speed; //子弹方向向左
                    break;
            }
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive = false;
                break;
            }
        }
    }
}
