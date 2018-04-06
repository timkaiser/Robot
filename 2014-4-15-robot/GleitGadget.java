public class GleitGadget extends Gadget{
    private Spieler spieler;
    public GleitGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild=Bilder.getGleitGadget();
    }

    public void reset(){ 
        spieler.setFallgeschwindigkeitToDefault();
        aktiviert = false;
    }

    public void ausfuehren(){
        if(aktiviert){
            reset();
        }
        else if(!aktiviert){
            spieler.setFallgeschwindigkeit(7);
            aktiviert = true;
        }
    }
    
    public Gadget resetTod(){
        reset();
        return null;
    }
    
}
