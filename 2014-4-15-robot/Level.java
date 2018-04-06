import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Level{ 
    List<Block> bloecke;
    List<Einheit> einheiten;
    static Spieler spieler;
    FileInputStream iostream;
    DataInputStream diostream;
    int level=0;
    int hoehe=50*64;
    int breite=100*64;
    String texturepack;

    Hintergrund hintergrund;

    static List<Boolean> geschafteLevel=new ArrayList<Boolean>();
    static String spielstand;
    public Level(int lvl, int tp){
        level=lvl;
        if(spieler == null){
            spieler=new Spieler(0,0);
        }

        switch (tp){
            case 0:
            texturepack="schrottplatz";
            hintergrund = new HintergrundStadt(breite);
            break;
            case 1:
            texturepack="stadt";
            hintergrund = new HintergrundStadt(breite);
            break;
            case 2:
            texturepack="kanalisation";
            hintergrund = new HintergrundKanalisation(breite,hoehe);
            break;
            case 3:
            texturepack="matrix";
            hintergrund = new HintergrundMatrix(breite);
            break;
        }

        new Bilder(texturepack);

        bloecke =  new ArrayList<Block>();
        einheiten = new ArrayList<Einheit>();

        einheiten.add(spieler);        

        try {
            iostream = new FileInputStream("level"+lvl+".lvl");
            diostream = new DataInputStream(iostream);
            try {
                breite=diostream.readInt();
                hoehe=diostream.readInt();
                int anzahl=diostream.readInt();
                for(int i=0;i<anzahl;i++){
                    blockLaden();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}        

        /*Block b1=new Sprechblasenblock(64*9,64*8,this);        
        bloecke.add(b1);
        Block b2=new LevelStarter(64*9,64*9,this,1);
        bloecke.add(b2);
        Block b3=new Timer(0,0,2000,this,b2);        
        bloecke.add(b3); 
        Block b4=new Signalverdoppler(64*8,64*9,this,b1,b3);        
        bloecke.add(b4);  
        bloecke.add(new Trigger(64*7,64*9,this,b4));
         */
        einordnen();

    }

    private Block blockLaden(){
        Block b = null;
        try {
            char read1 = diostream.readChar();
            int  read2 = diostream.readInt();
            /*
             * b1 normaler Block
             * b2 Einwegblock
             * b3 bewegender Block
             * b4 Sprungblock
             * 
             * g1 Gegner laufend
             * g2 Gegner fliegend
             * g3 Gegner springend
             * g4 Gegner nicht in Abgruende fallend
             * 
             * s1 Spawnpoint
             * s2 Levelstarter
             * s3 Levelende
             */
            if(read1=='b'){
                if(read2==1){
                    b = new NormalerBlock(Bilder.getMetallBlockBild(true,true,true,true), diostream.readInt(),diostream.readInt(),this);
                    bloecke.add(b);
                }else if(read2==2){
                    b = new EinwegBlock(Bilder.getEinwegBlockBild(true,true),diostream.readInt(),diostream.readInt(),this);   
                    bloecke.add(b);                 
                }else if(read2==3){
                    b = new BewegenderBlock(diostream.readInt(),diostream.readInt(),this);
                    bloecke.add(b);
                }else if(read2==4){
                    b = new SprungBlock(diostream.readInt(),diostream.readInt(),this);
                    bloecke.add(b);
                }                
            }else if(read1=='g'){
                if(read2==1){
                    einheiten.add(new Gegner_laufend(diostream.readInt(),diostream.readInt()));
                }
                if(read2==2){
                    einheiten.add(new Gegner_fliegend(diostream.readInt(),diostream.readInt()));
                }
                if(read2==3){
                    einheiten.add(new Gegner_springend(diostream.readInt(),diostream.readInt()));
                }
                if(read2==4){
                    einheiten.add(new Gegner_nichtInAbgruendeFallen(diostream.readInt(),diostream.readInt()));
                }
            }else if(read1=='s'){  
                if(read2==1){
                    spieler.setPosition( diostream.readInt(), diostream.readInt());
                }else if(read2==2){
                    b = new LevelStarter(diostream.readInt(),diostream.readInt(),this,diostream.readInt(),diostream.readInt());
                    bloecke.add(b);
                }else if(read2==3){
                    b = new LevelEnde(diostream.readInt(),diostream.readInt(),diostream.readInt());
                    bloecke.add(b);
                }else if(read2==4){
                }
            }else if(read1=='t'){
                if(read2==1){
                    Block tmp = blockLaden();
                    new Trigger(diostream.readInt(),diostream.readInt(),this,blockLaden());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }

    public int getAnzahlBloecke(){
        return bloecke.size();
    }

    public Block getBlock(int index){
        return bloecke.get(index);
    }

    public int getAnzahlEinheiten(){
        return einheiten.size();
    }

    public Einheit getEinheit(int index){
        return einheiten.get(index);
    }

    public Spieler getSpieler(){       
        return spieler;
    }

    public void einordnen(){

        //This Methode is bullshit
        for(int i = 0;i < bloecke.size();i++){

            if(getBlock(i) instanceof EinwegBlock){
                if(getLinksVonBlock(i) instanceof EinwegBlock){
                    if(getRechtsVonBlock(i) instanceof EinwegBlock){
                        getBlock(i).setBild(Bilder.getEinwegBlockBild(false,false));
                    }
                    else if(!(getRechtsVonBlock(i) instanceof EinwegBlock)){
                        getBlock(i).setBild(Bilder.getEinwegBlockBild(false,true));
                    }
                }
                if(getRechtsVonBlock(i) instanceof EinwegBlock){
                    if(getLinksVonBlock(i) instanceof EinwegBlock){
                        getBlock(i).setBild(Bilder.getEinwegBlockBild(false,false));
                    }
                    else if(!(getLinksVonBlock(i) instanceof EinwegBlock)){
                        getBlock(i).setBild(Bilder.getEinwegBlockBild(true,false));
                    }
                }
            }

            //Metall
            if(getBlock(i) instanceof NormalerBlock){
                if(getObenVonBlock(i) instanceof NormalerBlock){
                    if(getObenVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,true,true)){
                        getObenVonBlock(i).setBild(Bilder.getMetallBlockBild(true,true,false,true));
                        getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,true));
                    }
                    else if(getObenVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,true,false)){
                        getObenVonBlock(i).setBild(Bilder.getMetallBlockBild(true,true,false,false));
                        getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,true));
                    }
                    else if(getObenVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,false)){
                        getObenVonBlock(i).setBild(Bilder.getMetallBlockBild(false,true,false,false));
                        getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,true));
                    }
                    else if(getObenVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                        getObenVonBlock(i).setBild(Bilder.getMetallBlockBild(false,true,false,true));
                        getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,true));
                    }
                }
                if(getLinksVonBlock(i) instanceof NormalerBlock){
                    if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,false,false)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(false,false,false,false));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,false,false)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(true,false,false,false));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,true,true)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(true,false,true,true));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(false,false,true,true));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,false,true)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(true,false,false,true));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,false,true)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(false,false,false,true));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(true,true,true,false)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(true,false,true,false));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                    else if(getLinksVonBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,false)){
                        getLinksVonBlock(i).setBild(Bilder.getMetallBlockBild(false,false,true,false));
                        if(getBlock(i).getBild() == Bilder.getMetallBlockBild(false,true,true,true)){
                            getBlock(i).setBild(Bilder.getMetallBlockBild(false,true,true,false));
                        }
                        else {
                            getBlock(i).setBild(Bilder.getMetallBlockBild(true,true,true,false));
                        }
                    }
                }

            }

        }
    }

    public static void speichern(){
        try {   
            FileOutputStream output = new FileOutputStream(spielstand+".save");
            DataOutputStream  datop  = new DataOutputStream(output);

            try {
                for(int i=0; i<geschafteLevel.size(); i++){
                    datop.writeBoolean(geschafteLevel.get(i));
                }
            } catch (IOException ex) {ex.printStackTrace();}
            try {
                output.close();
            } catch (IOException e1) {}
        } catch (IOException ioe) {ioe.printStackTrace();}
    }

    public Block getUntenVonBlock(int j){
        Rectangle links;
        Rectangle block = bloecke.get(j).getRect();
        links = new Rectangle((int)block.getX(),(int)block.getY()+64,(int)block.getWidth(),(int)block.getHeight());
        for(int i = 0;i < bloecke.size();i++){
            if(getBlock(i).getRect().intersects(links)){
                return getBlock(i);
            }

        }
        return null;
    }

    public Block getObenVonBlock(int j){
        Rectangle links;
        Rectangle block = bloecke.get(j).getRect();
        links = new Rectangle((int)block.getX(),(int)block.getY()-64,(int)block.getWidth(),(int)block.getHeight());
        for(int i = 0;i < bloecke.size();i++){
            if(getBlock(i).getRect().intersects(links)){
                return getBlock(i);
            }

        }
        return null;
    }

    public Block getLinksVonBlock(int j){
        Rectangle links;        
        Rectangle block = bloecke.get(j).getRect();
        links = new Rectangle((int)block.getX()-64,(int)block.getY(),(int)block.getWidth(),(int)block.getHeight());
        for(int i = 0;i < bloecke.size();i++){
            if(getBlock(i).getRect().intersects(links)){
                return getBlock(i);
            }

        }
        return null;
    }

    public Block getRechtsVonBlock(int j){
        Rectangle rechts;
        Rectangle block = bloecke.get(j).getRect();
        rechts = new Rectangle((int)block.getX()+64,(int)block.getY(),(int)block.getWidth(),(int)block.getHeight());
        for(int i = 0;i < bloecke.size();i++){
            if(getBlock(i).getRect().intersects(rechts)){
                return getBlock(i);
            }

        }
        return null;
    }

    public static void levelFortschrittEinlesen(boolean b){
        geschafteLevel.add(b);
    }

    public static boolean getLevelFortschritt(int i){
        return geschafteLevel.get(i);
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        hintergrund.zeichnen(g,spielfeld);

        for(int i=0; i<getAnzahlBloecke();i++){
            bloecke.get(i).zeichnen(g,spielfeld);
        }
        for(int i=0; i<getAnzahlEinheiten();i++){           
            einheiten.get(i).zeichnen(g,spielfeld);
        }
    }
}
