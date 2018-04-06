import java.awt.Image;
import java.awt.Rectangle;

public class BewegenderBlock extends Block{
    int x,y,width,height;
    String zustand;
    public BewegenderBlock(int px, int py, Level l){
        rect=new Rectangle(px,py,64,64);
        x = px;
        y = py;
        width = 64;
        height = 64;
        bild=Bilder.getMetallBlockBild(true,true,true,true);
        lvl = l;
        aktiviert=true;
    }

    public void special(){
        if(aktiviert){
            double a = rect.getY();
            if(a > y-200&& zustand == "hoch"){
                bewegeVertikal(-5);
            }
            if(a == y-200){
                zustand = "runter";
            }
            if(a < y&&zustand == "runter"){
                bewegeVertikal(5);
            }
            if(a == y){
                zustand = "hoch";
            }
        }
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){      
        Rectangle rect2 = new Rectangle((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),5);
        if(!begebar&&r.intersects(rect2))
            return true;
        else
            return false;

    } 

    public void istKollidiertMit(Einheit e){
        if(zustand == "hoch"){
            e.bewegeUmXY(0, -5);
        }
    }

    public String getRichtung(){
        return zustand;
    }
}
