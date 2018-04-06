import java.awt.Rectangle;
import java.awt.Image;

public class SprungBlock extends Block{

    public SprungBlock( int x, int y, Level l){
        rect=new Rectangle(x,y,64,64);
        bild=Bilder.getSprungBlock();
        lvl = l;
    }

    public boolean kollision(Rectangle r,double geschwindigkeitX,double geschwindigkeitY){
        if(!begebar && r.intersects(rect)){
            return true;
        }
        else
            return false;
    }

    public void istKollidiertMit(Einheit e){        
        e.sprung(640);
    }
}