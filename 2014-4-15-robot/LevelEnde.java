import java.awt.Image;
import java.awt.Rectangle;

public class LevelEnde extends Block{  
    private int freizuschaltendesLevel=0;
    public LevelEnde(int x, int y, int freizuschaltendesLevel)    {
        rect=new Rectangle(x,y,128,128);
        bild=Bilder.getLevelStarterBlockBild(true);
        this.freizuschaltendesLevel=freizuschaltendesLevel;
    }  

    public boolean kollision(Rectangle r, double  geschwindigkeitX,double geschwindigkeitY){
        if(aktiviert&&rect.intersects(Start.spielfeld.getSpieler().getRect())){
            if(Start.spielfeld.getSpieler().getInteraktionsbereitschaft()){
                Level.geschafteLevel.remove(freizuschaltendesLevel);
                Level.geschafteLevel.add(freizuschaltendesLevel,true);

                Level.speichern();
                
                Spielfeld.level=new Level(0,3);
            }
        }
        return false;
    }
}
