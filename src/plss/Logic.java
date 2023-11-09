package plss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Logic extends JPanel implements ActionListener, KeyListener, MouseWheelListener {
    private int Bit1Y=120, Bit2Y=120;
    private int BallPX=290, BallPY=150;
    private int BallX=3, BallY=3;
    private Timer time;

    private boolean isOver = false;

    public Logic(){
        addKeyListener(this);
        addMouseWheelListener(this);
        time = new Timer(10,this);
        setFocusable(true);
        time.start();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        //background
        g.fillRect(0,0,600,400);
        g.setColor(Color.white);
        g.drawLine(295,0,295,400);
        //bit1
        g.setColor(Color.white);
        g.fillRect(0,Bit1Y,10,100);
        //bit2
        g.setColor(Color.white);
        g.fillRect(575,Bit2Y,10,100);
        //ball
        g.fillOval(BallPX,BallPY,15,15);
        //font
        Font f = new Font("Arial", Font.BOLD,25);
        g.setFont(f);
        if(isOver == true) {
            g.drawString("game over", 228,180);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
       BallPX += BallX;
       BallPY += BallY;
       if(BallPX <= 0) {
           isOver = true;
       }
       if(BallPY<=0){
           BallY=-BallY;
       }
        if(BallPY>=350){
            BallY=-BallY;
        }

        if(BallPY-50 <= Bit2Y && BallPX >= 300 && BallX>0) {
            Bit2Y-=5;
        }
        if(BallPY-50 >= Bit2Y && BallPX >= 300 && BallX>0) {
            Bit2Y+=5;
        }

        if(new Rectangle(BallPX,BallPY,15,15).intersects(new Rectangle(575,Bit2Y,10,100))){
            BallX=-BallX;
        }
        if(new Rectangle(BallPX,BallPY,15,15).intersects(new Rectangle(0,Bit1Y,10,100))){
            BallX=-BallX;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            Bit1Y+=9;
            if(Bit1Y >=263){
                Bit1Y=263;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            Bit1Y-=9;
            if(Bit1Y <= 0){
                Bit1Y=0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()>0){
            Bit1Y+=15;
            if(Bit1Y >=263){
                Bit1Y=263;
            }
        }
        if(e.getWheelRotation()<0){
            Bit1Y-=15;
            if(Bit1Y <= 0){
                Bit1Y=0;
            }
        }
    }
}
