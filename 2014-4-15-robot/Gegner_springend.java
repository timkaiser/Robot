import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Gegner_springend extends Gegner{
    String bewegungsrichtung;
    public Gegner_springend(int xk,int yk){    
        x = xk;
        y = yk;
        width = 50;
        height = 50;
        beruehrbar = 0;
        bewegungsrichtung="L";
        lookR = new Animation(Bilder.getGegner_springend(),64,10000);
        lookL = lookR;
        sprungHoehe = 600;
    }

    public void berechnen(){
        if(geschwindigkeitX==0)
            if(bewegungsrichtung=="L")
                bewegungsrichtung="R";
            else
                bewegungsrichtung="L";
        if(bewegungsrichtung=="L"){
            geschwindigkeitX=-5;
        }else{
            geschwindigkeitX=5;
        }

        Rectangle r = new Rectangle((int)x,(int)(y+1),width,height); 
        boolean b=true;
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,0,1)){
                b=false;
            }
        }
        if(!b)sprung();
    }
}
