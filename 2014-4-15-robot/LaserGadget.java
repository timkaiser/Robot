public class LaserGadget extends Gadget{
    private Spieler spieler;
    public LaserGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild =Bilder.getSprungGadget();
    }

    public void ausfuehren(){
        if(aktiviert){
            reset();
        }
        else if(!aktiviert){
            spieler.setSprungHoehe(400);
            aktiviert = true;
        }
    }

    public void reset(){
        spieler.setSprungHoehe();
        aktiviert = false;
    }
    
    public Gadget resetTod(){
        reset();
        return null;
    }
}
