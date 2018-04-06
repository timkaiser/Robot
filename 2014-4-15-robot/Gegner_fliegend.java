import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Gegner_fliegend extends Gegner{
    String bewegungsrichtung;
    public Gegner_fliegend(int xk,int yk){    
        x = xk;
        y = yk;
        width = 50;
        height = 50;
        beruehrbar = 0;
        bewegungsrichtung="L";
        lookR = new Animation(Bilder.getGegner_fliegend(),50,100000);
        lookL = lookR;
        sprungHoehe = 600;
        setFallgeschwindigkeit(0);
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
