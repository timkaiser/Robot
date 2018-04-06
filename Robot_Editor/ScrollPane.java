import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollPane extends JScrollPane implements KeyListener{
    private boolean waagerecht;
    private Gitter[][] gitter;
    public ScrollPane(JPanel p,Gitter[][] gitter){
        super(p);
        addKeyListener(this);
        waagerecht = false;
        this.gitter = gitter;
    }

    public void processMouseWheelEvent(MouseWheelEvent e){

      

        if(waagerecht){
            if(e.getWheelRotation() > 0){
                getHorizontalScrollBar().setValue(getHorizontalScrollBar().getValue()+Frame.gitterZahl);
            }
            else{
                getHorizontalScrollBar().setValue(getHorizontalScrollBar().getValue()-Frame.gitterZahl);
            }
        }
        else{

            super.processMouseWheelEvent(e);
        }

    }

    public void scrollWaagerecht(){
        waagerecht = true;
    }

    public void scrollSenkrecht(){
        waagerecht = false;
    }

    public boolean isWaagerecht(){
        return waagerecht;
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            waagerecht = false;
        }
    }

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            waagerecht = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_PLUS){
            Frame.gitterZahl += 5;
            System.out.println(Frame.gitterZahl);

            
            Gitter[][] tmp = new Gitter[gitter.length][gitter[0].length];

            
            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    tmp[i][j] = new Gitter(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight(),gitter[i][j].getCode(),gitter[i][j].getZahl(),gitter[i][j].getExtras());
                }
            }
            
            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    gitter[i][j] = new Gitter(i*Frame.gitterZahl,j*Frame.gitterZahl,Frame.gitterZahl,Frame.gitterZahl);
                }
            }

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    gitter[i][j].setCode(tmp[i][j].getCode());
                    gitter[i][j].setZahl(tmp[i][j].getZahl());
                    gitter[i][j].setExtras(tmp[i][j].getExtras());
                }
            }
           setViewportView(new Zeichenpanel(0,0,gitter));
        }
         else if(e.getKeyCode() == KeyEvent.VK_MINUS){
            Frame.gitterZahl -= 5;
            System.out.println(Frame.gitterZahl);

            
            Gitter[][] tmp = new Gitter[gitter.length][gitter[0].length];

            
            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    tmp[i][j] = new Gitter(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight(),gitter[i][j].getCode(),gitter[i][j].getZahl(),gitter[i][j].getExtras());
                }
            }
            
            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    gitter[i][j] = new Gitter(i*Frame.gitterZahl,j*Frame.gitterZahl,Frame.gitterZahl,Frame.gitterZahl);
                }
            }

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0; j < gitter[0].length;j++){
                    gitter[i][j].setCode(tmp[i][j].getCode());
                    gitter[i][j].setZahl(tmp[i][j].getZahl());
                    gitter[i][j].setExtras(tmp[i][j].getExtras());
                }
            }
            setViewportView(new Zeichenpanel(0,0,gitter));
        }
        repaint();
    }

    public void keyTyped(KeyEvent e){

    }

}