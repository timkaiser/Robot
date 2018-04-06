import java.awt.Image;
import java.awt.Rectangle;

public class EinwegBlock extends Block{
    public EinwegBlock(Image img, int x, int y, Level l){
        rect=new Rectangle(x,y,64,64);
        bild=img;
        lvl = l;

    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        Rectangle r2=new Rectangle((int)r.getX(),(int)(r.getY()-geschwindigkeitY),(int)r.getWidth(),(int)r.getHeight());
        if(geschwindigkeitY>0&&r.intersects(rect)&&!r2.intersects(rect))
            return true;
        else
            return false;
    } 
}
