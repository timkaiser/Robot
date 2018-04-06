import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Interface{
    BufferedImage gadgets1, gadgets2;
    public Interface()    {
        gadgets1=Bilder.getGuiGadgets1();
        gadgets2=Bilder.getGuiGadgets2();
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        if(Spielfeld.level.getSpieler().getGadget1()!=null&&Spielfeld.level.getSpieler().getGadget2()!=null){
            g.drawImage(gadgets2,0,0,spielfeld);       
            g.drawImage(gadgets1,64,0,spielfeld);
        }else if(Spielfeld.level.getSpieler().getGadget1()!=null){
            g.drawImage(gadgets1,0,0,spielfeld);
        }
        if(Spielfeld.level.getSpieler().getGadget1()!=null)
            Spielfeld.level.getSpieler().getGadget1().zeichnen(0,0,g,spielfeld);
        if(Spielfeld.level.getSpieler().getGadget2()!=null)
            Spielfeld.level.getSpieler().getGadget2().zeichnen(64,0,g,spielfeld);
    }

}
