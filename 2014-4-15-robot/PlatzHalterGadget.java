import java.awt.Rectangle;

public class PlatzHalterGadget extends Gadget{
    private Spieler spieler;
    public PlatzHalterGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild=Bilder.getGleitGadget();
    }

    public void reset(){ 

    }

    public void ausfuehren(){

    }

    public Gadget resetTod(){
        reset();
        return null;
    }

    public void special(){

        if(!blockUeberSpieler()){
            spieler.bewegeUmXY(0,-50);
            spieler.setHeight(96);
            spieler.setLookR(Bilder.getSpielerBild());
            resetTod();
        }
    }

    public boolean blockUeberSpieler(){
        boolean a = false;
        Rectangle r = new Rectangle((int)spieler.getRect().getMinX(),(int)spieler.getRect().getMinY()-64,64,96);
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,0,-64)){
                a = true;
            }
        }
        return a;
    }

}
