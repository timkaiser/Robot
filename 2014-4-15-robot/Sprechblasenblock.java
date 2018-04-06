import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.*;

public class Sprechblasenblock extends Block{
    String text = "Sesam öffne dich!";
    int zeit;
    int timer=0;
    public Sprechblasenblock(int x, int y, Level l)    {        
        rect=new Rectangle(x,y,64,64);        
        lvl = l;
        aktiviert = false;
        zeit = text.length()*300;
    }

    public void ausloesen(){
        aktiviert=true;
        timer=zeit;
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        return false;
    }
    
    public void special(){
       timer-=25;
       if(timer<=0){
           aktiviert=false;
       }
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        if(aktiviert){
            int xt = (int)spielfeld.level.spieler.getX()-spielfeld.level.spieler.getXVerschiebung();
            int yt = (int)spielfeld.level.spieler.getY()-spielfeld.level.spieler.getYVerschiebung()-22;
            int laenge = text.length()*7+10;
            g.setColor(Color.WHITE);
            g.fillRect(xt,yt,laenge,20);
            g.setColor(Color.BLACK);
            g.drawRect(xt,yt,laenge,20);
            g.drawString(text,xt+5,yt+15);
        }
    }
}
