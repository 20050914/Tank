package TankGames.Tankgame4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
@SuppressWarnings({"all"})
//坦克大战绘图区域
public class MyPanel extends JPanel implements KeyListener , Runnable {
        Hero hero = null;
    Vector<Enemytank> enemytanks = new Vector<>();
    //定义Vector类来存放爆炸效果
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;



    Vector<Bomd> bomds = new Vector<>();

        public MyPanel(){
            hero = new Hero(100,100);//初始化位置
            hero.setSpeed(10);

            int enemttanksSize = 3;
            for (int i = 0; i < enemttanksSize; i++) {
                //创建敌人坦克
                Enemytank enemytank = new Enemytank(100 * (i + 1), 0);
                enemytank.setDirect(2);
                enemytanks.add(enemytank);
                Shot shot = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
                enemytank.shots.add(shot);
                new Thread(shot).start();
            }
            img1 = Toolkit.getDefaultToolkit().getImage("img/img1.png");
            img2 = Toolkit.getDefaultToolkit().getImage("img/img2.png");
            img3 = Toolkit.getDefaultToolkit().getImage("img/img3.png");




        }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        if(hero.shot!=null&&hero.shot.isLive == true){
            //画出我方的子弹
            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
        }
        for (int i = 0; i < enemytanks.size(); i++) {
            //画出敌人坦克
            Enemytank enemytank = enemytanks.get(i);
            if (enemytank.isLive) {
                drawTank(enemytank.getX(), enemytank.getY(), g, enemytank.getDirect(), 1);
                for (int j = 0; j < enemytank.shots.size(); j++) {
                    Shot shot = enemytank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        enemytank.shots.remove(shot);
                    }
                }
            }
        }
        for (int i = 0; i < bomds.size(); i++) {
            Bomd bomd = bomds.get(i);
            if(bomd.live>6){
                g.drawImage(img3,bomd.x,bomd.y,60,60,this);
            }
            else if(bomd.live>3){
                g.drawImage(img2,bomd.x,bomd.y,60,60,this);
            }
            else {
                g.drawImage(img3,bomd.x,bomd.y,60,60,this);
            }
            bomd.LiveDown();
            if(bomd.live==0){
                bomds.remove(bomd);
            }
        }

    }

    /**
     *
     * @param x  坦克的左上角x坐标
     * @param y  坦克的左上角y坐标
     * @param g  画笔
     * @param direct  方向
     * @param type  类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //根据不同的类型设置不同的坦克
        switch (type){
            case 0:   //自己的坦克
                g.setColor(Color.cyan);break;
            case 1:   //敌人的坦克
                g.setColor(Color.yellow);break;
        }
        //（0，1，2，3）  0：上，1：右，2：下，3：左
        switch (direct){
            case 0: //向上
                g.draw3DRect(x,y,10,60,false);              //左边轮子
                g.draw3DRect(x+30,y,10,60,false);        //右边轮子
                g.draw3DRect(x+10,y+10,20,40,false);  //中间
                g.drawOval(x+10,y+20,20,20);                //圆
                g.drawLine(x+20,y+30,x+20,y);                      //炮筒
                break;
            case 1: //向右
                g.draw3DRect(x,y,60,10,false);             //上面的轮子
                g.draw3DRect(x,y+30,60,10,false);       //下面的轮子
                g.draw3DRect(x+10,y+10,40,20,false); //中间
                g.drawOval(x+20,y+10,20,20);               //圆
                g.drawLine(x+30,y+20,x+60,y+20);                     //炮筒
                break;
            case 2: //向下
                g.draw3DRect(x,y,10,60,false);              //左边轮子
                g.draw3DRect(x+30,y,10,60,false);        //右边轮子
                g.draw3DRect(x+10,y+10,20,40,false);  //中间
                g.drawOval(x+10,y+20,20,20);                //圆
                g.drawLine(x+20,y+30,x+20,y+60);                      //炮筒
                break;
            case 3: //向右
                g.draw3DRect(x,y,60,10,false);             //上面的轮子
                g.draw3DRect(x,y+30,60,10,false);       //下面的轮子
                g.draw3DRect(x+10,y+10,40,20,false); //中间
                g.drawOval(x+20,y+10,20,20);               //圆
                g.drawLine(x+30,y+20,x,y+20);                     //炮筒
                break;

            default:
                System.out.println("---");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //判断我方他坦克是否击中敌方坦克

    public  void hitTank(Shot s, Enemytank enemytank){
        switch (enemytank.getDirect()){
            case 0:
            case 2:
                if(s.x>enemytank.getX()&&s.x<enemytank.getX()+40&&s.y>enemytank.getY()&&s.y<enemytank.getY()+60){
                    s.isLive = false;
                    enemytank.isLive=false;
                    Bomd bomd = new Bomd(enemytank.getX(), enemytank.getY());
                    bomds.add(bomd);
                }
                break;
            case 1:
            case 3:
                if(s.x>enemytank.getX()&&s.x<enemytank.getX()+60&&s.y>enemytank.getY()&&s.y<enemytank.getY()+40)
                {
                    s.isLive = false;
                    enemytank.isLive=false;
                    Bomd bomd = new Bomd(enemytank.getX(), enemytank.getY());
                    bomds.add(bomd);
                }
                break;
        }
    }






    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W){
                hero.setDirect(0);
                hero.moveUp();
            }
            else if(e.getKeyCode() == KeyEvent.VK_D){
                hero.setDirect(1);
                hero.moveRight();
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                hero.setDirect(2);
                hero.moveDown();
            }
            else if(e.getKeyCode() == KeyEvent.VK_A){
                hero.setDirect(3);
                hero.moveLeft();
            }
            if(e.getKeyCode() == KeyEvent.VK_J){
                hero.shotEnemyTank();
            }
            repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(hero.shot!=null&&hero.shot.isLive){
            for (int i = 0; i < enemytanks.size(); i++) {
                Enemytank enemytank =  enemytanks.get(i);
                hitTank(hero.shot,enemytank);


            }
        }
        this.repaint();
    }
        }
}
