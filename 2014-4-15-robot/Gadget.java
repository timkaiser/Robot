import java.awt.Image;
import java.awt.Graphics;

public abstract class Gadget{
    protected Image bild;
    protected boolean aktiviert;
    protected long duration = 500,last = 0;

    public void aktivieren(){        
        if(System.currentTimeMillis() >= last+duration){
            last = System.currentTimeMillis();
            ausfuehren();

        }
    }

    public void special(){
        
    }
    
    public abstract void ausfuehren();

    public abstract void reset();    

    public abstract Gadget resetTod();
    
    public void setBild(Image img){
        bild=img;
    }

    public Gadget auswerfen(){
      
        return resetTod();
    }

    public Image getBild(){
        return bild;
    }

    public void zeichnen(int x, int y, Graphics g, Spielfeld spielfeld){
        g.drawImage(this.getBild(),x,y,spielfeld);
    }

}