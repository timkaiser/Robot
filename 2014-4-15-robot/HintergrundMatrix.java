
public class HintergrundMatrix extends Hintergrund{
    public HintergrundMatrix(int l){
        super(l);
        einfuegen(new HintergrundObjekt(Bilder.getHintergrundMatrix(),0,0,10000,800,10000000));
        for(int i=0;i<100;i++){
            int tiefe=(int)(Math.random()*2000+7000);
            einfuegen(new HintergrundObjekt(Bilder.getHintergrundMatrixcode((int)(Math.random()*4.99)),
                    (int)(Math.random()*(laenge*(1.0-((double)tiefe/10000.0))+800)),
                    (int)(Math.random()*500),
                    tiefe,
                    6,200));
        }
       
    }

}
