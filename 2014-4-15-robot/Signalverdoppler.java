import java.awt.Image;
import java.awt.Rectangle;

public class Signalverdoppler extends Block{
    Block ziel1, ziel2;
    public Signalverdoppler(int x, int y, Level l,Block b1, Block b2)    {
        rect=new Rectangle(x,y,64,64);
        ziel1=b1;
        ziel1.deaktivieren();
        ziel2=b2;
        ziel2.deaktivieren();
    }  

    public void ausloesen(){
        ziel1.ausloesen();        
        ziel2.ausloesen();
    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){        
        return false;
    }
}
