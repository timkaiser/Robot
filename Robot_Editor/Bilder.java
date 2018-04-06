import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bilder{
    public static BufferedImage trigger,metallblock,einweg,bewegenderBlock,startBlock,lstartBlock,lendeBlock,sprungBlock,gegnerFliegend,gegnerLaufend,gegnerSpringend,gegnerNichtFallend,glas;
    public Bilder(){
        sprungBlock=bildHinzufügen("sprung.png");
        metallblock = bildHinzufügen("metalboden.png");
        einweg = bildHinzufügen("Einweg.png");
        bewegenderBlock = bildHinzufügen("BewegenderBlock.png");
        startBlock = bildHinzufügen("Start.png");
        lstartBlock = bildHinzufügen("LevelStart.png");
        lendeBlock = bildHinzufügen("LevelEnde.png");
        gegnerFliegend = bildHinzufügen("gegner_Fliegend.png");
        gegnerSpringend = bildHinzufügen("gegner_springend.png");
        gegnerLaufend = bildHinzufügen("testsubjekt0000.png");
        gegnerNichtFallend = bildHinzufügen("gegner_nichtInAbgruendeFallen.png");
        glas = bildHinzufügen("glas.png");
        trigger = bildHinzufügen("Trigger.png");
    }
    
     public BufferedImage bildHinzufügen(String pfad){
        BufferedImage img = null;
        try {       img = ImageIO.read(getClass().getResource(pfad));
        } catch (IOException e) {}
        return  img;
    }
    
    public static BufferedImage getMetallBlock(){
        return metallblock;
    }
    
    public static BufferedImage getEinwegBlock(){
        return einweg;
    }
    
    public static BufferedImage getBewegenderBlock(){
        return bewegenderBlock;
    }
    
    public static BufferedImage getStartBlock(){
        return startBlock;
    }
    
    public static BufferedImage getSprungBlock(){
        return sprungBlock;
    }
    
    public static BufferedImage getLevelBlock(char a){
        if(a == 's'){
            return lstartBlock;
        }
        else if(a == 'e'){
            return lendeBlock;
        }
        else return lstartBlock;
    }
    
    public static BufferedImage getGegner(int i){
        if(i == 1){
            return gegnerLaufend;
        }
        else if(i == 2){
            return gegnerFliegend;
        }
        else if(i == 3){
            return gegnerSpringend;
        }
        else if(i == 4){
            return gegnerNichtFallend;
        }
        else {
            return gegnerLaufend;
        }
    }
    
    public static BufferedImage getGlas(){
        return glas;
    }
    
    public static BufferedImage getTrigger(){
        return trigger;
    }
}