import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;

abstract class Block{
    boolean begebar;
    Rectangle rect; 
    Image bild;
    Level lvl;
    boolean aktiviert=true;

    abstract boolean kollision(Rectangle r, double geschwindigkeitX,double geschwindigkeitY);
    
    public void istKollidiertMit(Einheit e){
    }

    public void special(){
    }

    public void aktivieren(){
        aktiviert=true;
    }

    public void deaktivieren(){
        aktiviert=false;
    }

    public Rectangle getRect(){
        return rect;
    }

    public void bewegeVertikal(int a){
        rect = new Rectangle((int)rect.getX(),(int)rect.getY()+a,(int)rect.getWidth(),(int)rect.getHeight());
    }

    public Image getBild(){
        return bild;
    }

    public void setBild(Image img){
        bild = img;
    }

    public void ausloesen(){
        aktiviert=!aktiviert;
    }

    public String getRichtung(){
        return null;
    }
    
    public void zeichnen(Graphics g, Spielfeld spielfeld){
        g.drawImage(this.getBild(),(int)rect.getX()-spielfeld.level.spieler.getXVerschiebung(),(int)rect.getY()-spielfeld.level.spieler.getYVerschiebung(),spielfeld);
    }
}
