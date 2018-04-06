public class DoppelSprungGadget extends Gadget{
    private Spieler spieler;
    public DoppelSprungGadget(Spieler sp){
        spieler = sp;
        aktiviert = false;
        bild =Bilder.getSprungGadget();
        duration=200;
    }

    public void ausfuehren(){
        if(Input.isKeyDown(Hauptmenue.getSpringenTaste())){
            if(spieler.getCounter() == 0){
                spieler.sprung();
                spieler.setCounter(spieler.getCounter()+1);
            }
            else if(spieler.getCounter() == 1 && spieler.getGeschwindigkeitY() > 0){
                spieler.sprung();
                spieler.setCounter(spieler.getCounter()+1);
            }
            
        }
        //System.out.println(spieler.getCounter());
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
