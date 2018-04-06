public class HintergrundStadt extends Hintergrund{   
    public HintergrundStadt(int laenge){
        super(laenge) ;
        einfuegen(new HintergrundObjekt(Bilder.getHintergrundStadt(),0,0,10000));
        for(int i=0;i<laenge/64;i++){
            einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
                    (int)(Math.random()*laenge),
                    (int)(Math.random()*100+450),
                    (int)(Math.random()*500+8000)));
        }
        for(int i=0;i<laenge/64;i++){
            einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
                    (int)(Math.random()*laenge),
                    (int)(Math.random()*100+350),
                    (int)(Math.random()*500+8500)));
        }
        for(int i=0;i<laenge/64;i++){
            einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
                    (int)(Math.random()*laenge),
                    (int)(Math.random()*100+250),
                    (int)(Math.random()*500+9000)));
        }
    }
}
// public class HintergrundStadt extends Hintergrund{   
//     public HintergrundStadt(int laenge){
//         super(laenge) ;
//         einfuegen(new HintergrundObjekt(Bilder.getHintergrundStadt(),0,0,10000,800,10000000));
//         for(int i=0;i<30;i++){
//             einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
//                     (int)(Math.random()*laenge),
//                     (int)(Math.random()*100+300),
//                     (int)(Math.random()*500+8000)));
//         }
//         for(int i=0;i<30;i++){
//             einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
//                     (int)(Math.random()*laenge),
//                     (int)(Math.random()*100+300),
//                     (int)(Math.random()*500+8500)));
//         }
//         for(int i=0;i<30;i++){
//             einfuegen(new HintergrundObjekt(Bilder.getHintergrundHaus((int)(Math.random()*4.99)),
//                     (int)(Math.random()*laenge),
//                     (int)(Math.random()*100+300),
//                     (int)(Math.random()*500+9000)));
//         }
//     }
// }
