import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.*;

public class Timer extends Block{
    int zeit;
    int timer=0;
    Block ziel;
    public Timer(int x, int y, int t, Level l, Block b)    {        
        rect=new Rectangle(x,y,64,64);        
        lvl = l;
        aktiviert = false;
        ziel=b;       
        b.deaktivieren();
        zeit=t;
    }

    public void ausloesen(){
        timer=zeit;
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        return false;
    }
    
    public void special(){
       timer-=25;
       if(timer<=0&&timer>-25){
           ziel.ausloesen();
       }
    }

}
