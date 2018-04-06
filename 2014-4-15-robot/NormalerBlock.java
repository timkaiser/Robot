import java.awt.Image;
import java.awt.Rectangle;

public class NormalerBlock extends Block{

    public NormalerBlock(Image img, int x, int y, Level l){
        rect=new Rectangle(x,y,64,64);
        bild=img;
        lvl = l;
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        if(!begebar&&r.intersects(rect))
            return true;
        else
            return false;

    }
}
