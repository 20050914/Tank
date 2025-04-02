package TankGames.Tankgame4;

import javax.swing.*;
@SuppressWarnings({"all"})
public class TankGame04 extends JFrame {
    MyPanel mp= null;
    public static void main(String[] args) {
        new TankGame04();
    }
    public TankGame04(){
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);

        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
