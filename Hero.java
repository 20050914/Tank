package TankGames.Tankgame4;

public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }
    Shot shot = null;
    public void shotEnemyTank(){
        switch (getDirect()){
            case 0:
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,3);
                break;


        }
        new Thread(shot).start();
    }

}
