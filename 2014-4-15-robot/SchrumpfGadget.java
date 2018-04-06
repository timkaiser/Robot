import java.awt.Rectangle;

public class SchrumpfGadget extends Gadget{
    private Spieler spieler;
    private boolean gadgetVerlust;
    
    public SchrumpfGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild =Bilder.getSchrumpfGadget();
        gadgetVerlust = false;
        duration=100;
    }

    public void ausfuehren(){
        if(aktiviert){
            reset();

        }
        else if(!aktiviert){
            spieler.setHeight(63);
            spieler.setLookR(Bilder.getSpielerGeducktBild());
            aktiviert = true;
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

    public void reset(){

        if(!blockUeberSpieler()){
            spieler.bewegeUmXY(0,-50);
            spieler.sprung();
            spieler.setHeight(96);
            aktiviert = false;
            spieler.setLookR(Bilder.getSpielerBild());
        }
    }

    public Gadget resetTod(){
        if(!blockUeberSpieler()){
            spieler.bewegeUmXY(0,-50);
            spieler.setHeight(96);
            aktiviert = false;
            spieler.setLookR(Bilder.getSpielerBild());
            return null;
        }
        else{
            gadgetVerlust = true;
            return new PlatzHalterGadget(spieler);
        }
    }

    public void special(){
        
        
    }

}
