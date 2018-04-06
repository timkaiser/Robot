import java.awt.*;

public class SchildGadget extends Gadget{
    int trefferzahl=3;
    public SchildGadget(int i)    {
        trefferzahl=i;
        bild = Bilder.getSchildGadget();
    }

    public Gadget auswerfen(){
        super.auswerfen();
        if(trefferzahl>1){
            return new SchildGadget(trefferzahl-1);
        }else{
            return null;
        }
    }

    public void reset(){ 
        aktiviert = false;
    }

    public void ausfuehren(){
        if(aktiviert){
            reset();
        }
        else if(!aktiviert){
            aktiviert = true;
        }
    }  
    
    public void zeichnen(int x, int y, Graphics g, Spielfeld spielfeld){
        super.zeichnen(x, y, g, spielfeld);
        g.setColor(Color.BLACK);
        g.setFont(Start.getFont(14));
        g.drawString(""+trefferzahl, x+47, 60);
    }
    
    public Gadget resetTod(){
        reset();
        return null;
    }
    
}
