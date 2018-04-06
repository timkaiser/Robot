import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Gegner_laufend extends Gegner{
    String bewegungsrichtung;
    public Gegner_laufend(int xk,int yk){           
        width = 94;
        height = 81;
        
         x = xk;
        y = yk+64-height;
        
        beruehrbar = 0;
        bewegungsrichtung="L";
        lookR = new Animation(Bilder.getGegner_laufend(),50,10000);
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
    }
}
