import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Gegner_nichtInAbgruendeFallen extends Gegner{
    String bewegungsrichtung;
    public Gegner_nichtInAbgruendeFallen(int xk,int yk){    
        x = xk;
        y = yk;
        width = 64;
        height = 22;
        beruehrbar = 0;
        bewegungsrichtung="R";
        lookR = new Animation(Bilder.getGegner_nichtInAbgruendeFallen(),64,100);
        lookL = lookR;
        lookR.starteAnimation();
        sprungHoehe = 600;
    }

    public void berechnen(){
        if(geschwindigkeitX==0)
            if(bewegungsrichtung=="L")
                bewegungsrichtung="R";
            else
                bewegungsrichtung="L";

        Rectangle r;
        if(bewegungsrichtung=="L")
            r = new Rectangle((int)(x-5),(int)(y+height+5),1,1); 
        else
            r = new Rectangle((int)(x+width+1),(int)(y+height+1),1,1); 

        boolean b=true;
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,geschwindigkeitX,geschwindigkeitY)){
                b=false;
            }
        }

        if(b)
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
