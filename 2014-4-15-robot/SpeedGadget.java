public class SpeedGadget extends Gadget{
    private Spieler spieler;
    public SpeedGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild=Bilder.getSpeedGadget();
    }

    public void reset(){ 
        spieler.setBeschleunigung(1);
        spieler.setMaxSpeed(10);
        aktiviert = false;
    }

    public void ausfuehren(){
        if(aktiviert){
            reset();
        }
        else if(!aktiviert){
            spieler.setBeschleunigung(4);
            spieler.setMaxSpeed(40);
            aktiviert = true;
        }
    }
    
    public Gadget resetTod(){
        reset();
        return null;
    }
    
}