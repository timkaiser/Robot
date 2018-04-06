import java.awt.Image;
import java.awt.Rectangle;

public class LevelStarter extends Block{  
    int neuesLevel;
    Image bildAktiv;
    Image bildInaktiv;
    int texturepack = 1;
    public LevelStarter(int x, int y, Level l,int s, int tp)    {
        rect=new Rectangle(x,y,128,128);
        texturepack=tp;
        bildAktiv=Bilder.getLevelStarterBlockBild(true);
        bildInaktiv=Bilder.getLevelStarterBlockBild(false);
        lvl = l;
        neuesLevel=s;
        
        if((neuesLevel>=1&&Level.getLevelFortschritt(neuesLevel-1))||neuesLevel==0){
            aktiviert=true;
        }else {
            aktiviert=false;
        }
    }  

    public Image getBild(){
        if(aktiviert)
            return bildAktiv;
        else
            return bildInaktiv;

    }

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        if(aktiviert&&rect.intersects(lvl.getSpieler().getX(),lvl.getSpieler().getY(),lvl.getSpieler().getWidth(),lvl.getSpieler().getHeight())){
            if(lvl.getSpieler().getInteraktionsbereitschaft()){
                Spielfeld.level=new Level(neuesLevel,texturepack);
            }
        }
        return false;
    }
}
