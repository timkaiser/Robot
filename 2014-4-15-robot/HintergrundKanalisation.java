public class HintergrundKanalisation extends Hintergrund{   
    public HintergrundKanalisation(int laenge, int hoehe){
        super(laenge) ;
        for(int i=0; i<laenge/640+1; i++){
            for(int j=0; j<hoehe/640+1; j++){
                einfuegen(new HintergrundObjekt(Bilder.getHintergrundKanalisation(),i*640,j*640,0));
            }
        }
    }
}

