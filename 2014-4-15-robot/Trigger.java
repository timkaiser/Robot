import java.awt.Image;
import java.awt.Rectangle;

public class Trigger extends Block{  
    Block ziel;
    public Trigger (int x, int y, Level l,Block b)    {
        rect=new Rectangle(x,y,64,64);
        ziel=b;
        b.deaktivieren();
    }  

    public void ausloesen(){
        ziel.ausloesen();
        aktiviert=false;
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        if(aktiviert){
            if(rect.intersects(Spielfeld.level.getSpieler().getX(),Spielfeld.level.getSpieler().getY(),Spielfeld.level.getSpieler().getWidth(),Spielfeld.level.getSpieler().getHeight())){
                ausloesen();
            }
        }
        return false;
    }
}
