public class SprungGadget extends Gadget{
    private Spieler spieler;
    public SprungGadget(Spieler sp){
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
