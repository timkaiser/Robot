import java.util.*;
import java.awt.Graphics;
import java.awt.Image;

public class Hintergrund{
    List<HintergrundObjekt> objekte;
    int laenge;
    public Hintergrund(int l)    {
        objekte = new ArrayList<HintergrundObjekt>();
        laenge=l;       
    }

    protected void einfuegen(HintergrundObjekt o){
        boolean b=false;
        for (int i=0;i<objekte.size()&&!b;i++){
            if(objekte.get(i).getTiefe()<o.getTiefe()){
                objekte.add(i, o);
                b=true;
            }
        }
        if(!b){
            objekte.add(objekte.size(), o);
        }
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        for(int i=0;i<objekte.size();i++){
            objekte.get(i).zeichnen(g, spielfeld);
        }
    }
}