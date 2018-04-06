import java.awt.*;
import java.awt.image.BufferedImage;

public class HintergrundObjekt{
    private Animation bild;
    private int x, y,tiefe;
    public HintergrundObjekt(BufferedImage img, int x, int y, int tiefe)    {
        this(img,x,y,tiefe,img.getWidth(),1000000);
    }

    public HintergrundObjekt(BufferedImage img, int x, int y, int tiefe, int bildbreite, int bildWechselzeit)    {
        bild = new Animation(img,bildbreite,bildWechselzeit);
        bild.starteAnimation();
        this.x=x;
        this.y=y;
        this.tiefe=tiefe;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getTiefe(){
        return tiefe;
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        g.drawImage(bild.getNextPicture(),
            x-(int)(spielfeld.level.spieler.getXVerschiebung()*(1.0-((double)tiefe/10000.0))),
            y-(int)(spielfeld.level.spieler.getYVerschiebung()*(1.0-((double)tiefe/10000.0))),
            spielfeld);

    }
}
