import java.awt.image.BufferedImage;
import java.util.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bilder{
    private String pack="kanalisation"; //"schrottplatz", "stadt", "matrix", "kanalisation"

    public static BufferedImage 

    metallAllesRand,metallOhneAlles,metallOhneLinks,metallOhneLinksOben,
    metallOhneLinksObenRechts,metallOhneLinksRechts,metallOhneOben,metallOhneObenRechts,
    metallOhneObenRechtsUnten,metallOhneRechts,metallOhneRechtsUnten,metallOhneRechtsUntenLinks,
    metallOhneUnten,metallOhneUntenLinks,metallOhneUntenLinksOben,metallOhneUntenOben,sprungBlock,

    einwegLinks,einwegRechts,einwegMitte,einwegAlles,

    levelStarterBlockAktiv, levelStarterBlockInaktiv,

    gegner_laufend, gegner_springend, gegner_fliegend, gegner_nichtInAbgruendeFallen,

    sprungGadget,gleitGadget,speedGadget,schildGadget, schrumpfGadget,

    gui_gadgets1, gui_gadgets2,

    hintergrundStadt,hintergrundMatrix,hintergrundKanalisation;

    public static List<BufferedImage> haeuser, matrixcode;

    public static BufferedImage spieler,spielerGeduckt;

    public Bilder(String texturepack){
        
        pack=texturepack;
        
        einwegLinks                 = bildHinzufuegen(pack,"VonUntenDurchBlock.png",0.0,0.0);
        einwegMitte                 = bildHinzufuegen(pack,"VonUntenDurchBlock.png",1.0,0.0);
        einwegRechts                = bildHinzufuegen(pack,"VonUntenDurchBlock.png",2.0,0.0);
        einwegAlles                 = bildHinzufuegen(pack,"VonUntenDurchBlock.png",3.0,0.0);

        metallAllesRand             = bildHinzufuegen(pack,"metall.png", 3.0, 3.0);
        metallOhneAlles             = bildHinzufuegen(pack,"metall.png", 1.0, 1.0);
        metallOhneLinks             = bildHinzufuegen(pack,"metall.png", 2.0, 3.0);
        metallOhneLinksOben         = bildHinzufuegen(pack,"metall.png", 2.0, 2.0);
        metallOhneLinksObenRechts   = bildHinzufuegen(pack,"metall.png", 1.0, 2.0);
        metallOhneLinksRechts       = bildHinzufuegen(pack,"metall.png", 1.0, 3.0);
        metallOhneOben              = bildHinzufuegen(pack,"metall.png", 3.0, 2.0);
        metallOhneObenRechts        = bildHinzufuegen(pack,"metall.png", 0.0, 2.0);
        metallOhneObenRechtsUnten   = bildHinzufuegen(pack,"metall.png", 0.0, 1.0);
        metallOhneRechts            = bildHinzufuegen(pack,"metall.png", 0.0, 3.0);
        metallOhneRechtsUnten       = bildHinzufuegen(pack,"metall.png", 0.0, 0.0);
        metallOhneRechtsUntenLinks  = bildHinzufuegen(pack,"metall.png", 1.0, 0.0);
        metallOhneUnten             = bildHinzufuegen(pack,"metall.png", 3.0, 0.0);
        metallOhneUntenLinks        = bildHinzufuegen(pack,"metall.png", 2.0, 0.0);
        metallOhneUntenLinksOben    = bildHinzufuegen(pack,"metall.png", 2.0, 1.0);
        metallOhneUntenOben         = bildHinzufuegen(pack,"metall.png", 3.0, 1.0);
        sprungBlock                 = bildHinzufuegen(pack,"sprungBlock.png");

        levelStarterBlockAktiv      = bildHinzufuegen("allgemein","LevelStartBlock.png",2.0,0.0,2.0,2.0);
        levelStarterBlockInaktiv    = bildHinzufuegen("allgemein","LevelStartBlock.png",0.0,0.0,2.0,2.0);

        gui_gadgets1                = bildHinzufuegen("allgemein","gui_gadgets1.png");
        gui_gadgets2                = bildHinzufuegen("allgemein","gui_gadgets2.png");

        hintergrundStadt            = bildHinzufuegen("stadt","Hintergrund.png");

        haeuser = new ArrayList<BufferedImage>();
        haeuser.add( bildHinzufuegen("stadt","haus1.png"));
        haeuser.add( bildHinzufuegen("stadt","haus2.png"));
        haeuser.add( bildHinzufuegen("stadt","haus3.png"));
        haeuser.add( bildHinzufuegen("stadt","haus4.png"));
        haeuser.add( bildHinzufuegen("stadt","haus5.png"));

        hintergrundMatrix = bildHinzufuegen("matrix","Hintergrund.png");

        matrixcode = new ArrayList<BufferedImage>();
        matrixcode.add( bildHinzufuegen("matrix","Matrixcode1.png"));
        matrixcode.add( bildHinzufuegen("matrix","Matrixcode2.png"));
        matrixcode.add( bildHinzufuegen("matrix","Matrixcode3.png"));
        matrixcode.add( bildHinzufuegen("matrix","Matrixcode4.png"));
        matrixcode.add( bildHinzufuegen("matrix","Matrixcode5.png"));
        
        
        hintergrundKanalisation = bildHinzufuegen("kanalisation","Hintergrund.png");

        spieler = bildHinzufuegen("allgemein","robot.png");
        spielerGeduckt = bildHinzufuegen("allgemein","robotGeduckt.png");

        gegner_laufend = bildHinzufuegen(pack,"gegner_laufend.png");
        gegner_springend = bildHinzufuegen(pack,"gegner_springend.png");
        gegner_fliegend = bildHinzufuegen(pack,"gegner_fliegend.png");
        gegner_nichtInAbgruendeFallen = bildHinzufuegen(pack,"gegner_nichtInAbgruendeFallen.png");

        gleitGadget  = bildHinzufuegen("allgemein","gleitGadgetIcon.png");
        sprungGadget = bildHinzufuegen("allgemein","sprungGadgetIcon.png");
        speedGadget = bildHinzufuegen("allgemein","speedGadgetIcon.png");
        schildGadget = bildHinzufuegen("allgemein","schildGadgetIcon.png");        
        schrumpfGadget  = bildHinzufuegen("allgemein","schrumpfGadgetIcon.png");
    }

    //Bild aus pfad
    public BufferedImage bildHinzufuegen(String texturepack,String pfad){
        BufferedImage img = null;
        try {       img = ImageIO.read(getClass().getResource("texturen//"+texturepack+"//"+pfad));
        } catch (IOException e) {}
        return  img;
    }
    //Bildausschnitt aus pfad an der Position x/y mit Hoehe h und Breite b in Pixeln
    public BufferedImage bildHinzufuegen(String texturepack,String pfad, int x, int y, int h, int b){
        BufferedImage img = null;
        try {       img = (ImageIO.read(getClass().getResource("texturen//"+texturepack+"//"+pfad))).getSubimage(x,y,h,b);
        } catch (IOException e) {}
        return  img;
    }
    //Bildausschnitt aus pfad an der Position x/y (in Pixeln) mit Hoehe und Breite 64px 
    public BufferedImage bildHinzufuegen(String texturepack,String pfad, int x, int y){
        BufferedImage img = null;
        try {       img = (ImageIO.read(getClass().getResource("texturen//"+texturepack+"//"+pfad))).getSubimage(x,y,64,64);
        } catch (IOException e) {}
        return  img;
    }
    //Bildausschnitt aus pfad an der Position x/y mit Hoehe h und Breite b in Bloecken (1 Block = 64px)
    public BufferedImage bildHinzufuegen(String texturepack,String pfad, double x, double y, double h, double b){
        BufferedImage img = null;
        try {       img = (ImageIO.read(getClass().getResource("texturen//"+texturepack+"//"+pfad))).getSubimage((int)x*64,(int)y*64,(int)h*64,(int)b*64);
        } catch (IOException e) {}
        return  img;
    }
    //Bildausschnitt aus pfad an der Position x/y (in Bloecken) mit Hoehe und Breite 64px 
    public BufferedImage bildHinzufuegen(String texturepack,String pfad, double x, double y){
        BufferedImage img = null;
        try {       img = (ImageIO.read(getClass().getResource("texturen//"+texturepack+"//"+pfad))).getSubimage((int)x*64,(int)y*64,64,64);
        } catch (IOException e) {}
        return  img;
    }

    public static BufferedImage getGuiGadgets1(){
        return gui_gadgets1;
    }

    public static BufferedImage getGuiGadgets2(){
        return gui_gadgets2;
    }

    public static BufferedImage getSprungBlock(){
        return sprungBlock;
    }

    public static BufferedImage getSprungGadget(){
        return sprungGadget;
    }

    public static BufferedImage getGleitGadget(){
        return gleitGadget;
    }

    public static BufferedImage getSpeedGadget(){
        return speedGadget;
    }

    public static BufferedImage getSchildGadget(){
        return schildGadget;
    }

    public static BufferedImage getSchrumpfGadget(){
        return schrumpfGadget;
    }

    public static BufferedImage getGegner_laufend(){
        return gegner_laufend;
    }

    public static BufferedImage getGegner_springend(){
        return gegner_springend;
    }

    public static BufferedImage getGegner_fliegend(){
        return gegner_fliegend;
    }

    public static BufferedImage getGegner_nichtInAbgruendeFallen(){
        return gegner_nichtInAbgruendeFallen;
    }

    public static BufferedImage getLevelStarterBlockBild(boolean aktiv){
        if(aktiv)
            return levelStarterBlockAktiv;
        else
            return levelStarterBlockInaktiv;
    }

    public static BufferedImage getEinwegBlockBild(boolean links,boolean rechts){
        if(links){
            if(rechts){
                return einwegAlles;
            }
            else if(!rechts){
                return einwegLinks;
            }
        }
        else if(!links){
            if(rechts){
                return einwegRechts;
            }
            else if(!rechts){
                return einwegMitte;

            }
        }

        return null;
    }

    public static BufferedImage getMetallBlockBild(boolean oben,boolean rechts,boolean unten,boolean links){

        if(oben){
            if(rechts){
                if(links){
                    if(unten){
                        return metallAllesRand;
                    }
                    if(!unten){
                        return metallOhneUnten;
                    }
                }
                if(!links){
                    if(unten){
                        return metallOhneLinks;
                    }
                    if(!unten){
                        return metallOhneUntenLinks;
                    }
                }
            }
            if(!rechts){
                if(links){
                    if(unten){
                        return metallOhneRechts;
                    }
                    if(!unten){
                        return metallOhneRechtsUnten;
                    }
                }
                if(!links){
                    if(unten){
                        return metallOhneLinksRechts;
                    }
                    if(!unten){
                        return metallOhneRechtsUntenLinks;
                    }
                }
            }
        }
        if(!oben){
            if(rechts){
                if(links){
                    if(unten){
                        return metallOhneOben;
                    }
                    if(!unten){
                        return metallOhneUntenOben;
                    }
                }
                if(!links){
                    if(unten){
                        return metallOhneLinksOben;
                    }
                    if(!unten){
                        return metallOhneUntenLinksOben;
                    }
                }
            }
            if(!rechts){
                if(links){
                    if(unten){
                        return metallOhneObenRechts;
                    }
                    if(!unten){
                        return metallOhneObenRechtsUnten;
                    }
                }
                if(!links){
                    if(unten){
                        return metallOhneLinksObenRechts;
                    }
                    if(!unten){
                        return metallOhneAlles;
                    }
                }
            }
        }
        return null;
    }

    public static BufferedImage getHintergrundStadt(){
        return hintergrundStadt;
    }

    public static BufferedImage getHintergrundHaus(int i){
        return haeuser.get(i);
    }

    public static BufferedImage getHintergrundMatrix(){
        return hintergrundMatrix;
    }

    public static BufferedImage getHintergrundMatrixcode(int i){
        return matrixcode.get(i);
    }
    
    public static BufferedImage getHintergrundKanalisation(){
        return hintergrundKanalisation;
    }

    public static BufferedImage getSpielerBild(){
        return spieler;
    }

    public static BufferedImage getSpielerGeducktBild(){
        return spielerGeduckt;
    }
}