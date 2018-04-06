import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Einheit{

    public int width,height;
    public double x, y, geschwindigkeitX, geschwindigkeitY;
    public Animation lookR;
    public Animation lookL;
    public boolean amLeben; 
    public String richtung="R";
    public int leben;
    public int beruehrbar;
    public int sprungHoehe;   
    protected int sprungStaerke=20;
    protected static double gravitation=1.0;
    protected double maxFallGeschwindigkeit=63;
    protected boolean sprung;

    public void spielerKollision(){};
    abstract void berechnen();
    
    public void bewegeUmXY(int streckeX,int streckeY){   
        x+= streckeX;
        y+= streckeY;
    }
    
    public void bewegen(){    
        
        
        beruehrbar--;
        //kollisionsueberpruefung horizontal
        int kollisionsBlock=0;

        String richtung="";
        if(geschwindigkeitX<0)
            richtung="l";
        else if(geschwindigkeitX>0)
            richtung="r";

        Rectangle r = new Rectangle((int)(x+geschwindigkeitX),(int)y,width,height); 
        boolean b=true;
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,geschwindigkeitX,geschwindigkeitY)){
                b=false;
                kollisionsBlock=i;
            }
        }

        //berechnen der neuen geschwindigkeit (x)
        if(b)
            x=x+geschwindigkeitX;
        else{
            annaehern(richtung,kollisionsBlock);
            geschwindigkeitX=0;
        }
        //kollisionsueberpruefung vertikal
        if(geschwindigkeitY+gravitation<maxFallGeschwindigkeit)
            geschwindigkeitY+=gravitation;

        if(geschwindigkeitY<0)
            richtung="o";
        else if(geschwindigkeitY>0)
            richtung="u";

        r = new Rectangle((int)(x),(int)(y+geschwindigkeitY),width,height); 
        b=true;
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,geschwindigkeitX,geschwindigkeitY)){
                if(Spielfeld.level.getBlock(i) instanceof BewegenderBlock&&Spielfeld.level.getBlock(i).getRichtung()=="hoch"){
                    geschwindigkeitY = -5;
                }
                else{
                    b=false;
                    kollisionsBlock=i;
                }
            }
        }    

        if(b)
            y=y+geschwindigkeitY;
        else{
            annaehern(richtung,kollisionsBlock);
            geschwindigkeitY=0;
        }
    }

    public void annaehern(String richtung, int kollisionsBlockNummer){
        Block kollisionsBlock = Spielfeld.level.getBlock(kollisionsBlockNummer);
        if(richtung=="r"||richtung=="l"){
            if(richtung=="r"){
                Rectangle r = new Rectangle((int)x+1,(int)(y),width,height); 

                while(!kollisionsBlock.kollision(r,1,0)){
                    x++;
                    r = new Rectangle((int)x+1,(int)(y),width,height); 

                }
            }else if(richtung=="l"){
                Rectangle r = new Rectangle((int)x-1,(int)(y),width,height); 

                while(!kollisionsBlock.kollision(r,-1,0)){
                    x--;
                    r = new Rectangle((int)x-1,(int)(y),width,height); 

                }
            }
        }else{
            if(richtung=="o"){
                Rectangle r = new Rectangle((int)x,(int)(y-1),width,height); 

                while(!kollisionsBlock.kollision(r,0,-1)){
                    y--;
                    r = new Rectangle((int)x,(int)(y-1),width,height); 

                }
            }else if(richtung=="u"){
                Rectangle r = new Rectangle((int)x,(int)(y+1),width,height); 

                while(!kollisionsBlock.kollision(r,0,1)){
                    y++;
                    r = new Rectangle((int)x,(int)(y+1),width,height); 

                }
            }
        }
        kollisionsBlock.istKollidiertMit(this);
    }

    public boolean kollisionMitBoden(){
        Rectangle r = new Rectangle((int)x,(int)(y+1),width,height); 
        boolean b=false;
        for(int i=0; i<Spielfeld.level.getAnzahlBloecke();i++){ 
            if(Spielfeld.level.getBlock(i).kollision(r,0,1)){
                b=true;
            }
        }
        return b;
    }

    public void sprung(){
        geschwindigkeitY=-sprungStaerke;
        //System.out.println(System.currentTimeMillis());
    }

    public void sprung(int hoehe){
        geschwindigkeitY-=((int)Math.round((Math.sqrt(8*gravitation*hoehe)-1)/2));
    }

    public void setSprung(boolean sprung){
        this.sprung = sprung;
    }

    public boolean getSprung(){
        return sprung;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(int a){
        x = a;
    }

    public void setY(int a){
        y = a;
    }

    public void setWidth(int a){
        width = a;
    }

    public void setHeight(int a){
        height = a;
    }
    
    public void setSprungHoehe(int h){
        sprungHoehe = h;
        sprungStaerke=(int)Math.round((Math.sqrt(8*gravitation*sprungHoehe)-1)/2);
    }

    public void setSprungHoehe(){
        sprungHoehe = 266;
        sprungStaerke=(int)Math.round((Math.sqrt(8*gravitation*sprungHoehe)-1)/2);
    }

    public void setGravitation(double g){
        gravitation = g;
    }

    public void setFallgeschwindigkeit(double d){
        maxFallGeschwindigkeit=d;
    }

    public void setFallgeschwindigkeitToDefault(){
        maxFallGeschwindigkeit=63;
    }

    public void beschleunigen(double ax, double ay){
        geschwindigkeitX+=ax;
        geschwindigkeitY+=ay;
    }

    public BufferedImage getBild(){

        if(richtung =="L"){
            return lookL.getNextPicture();
        }
        else {
            return lookR.getNextPicture();
        }
    }    

    public void damage(){
        if(beruehrbar<=0){
            beruehrbar=30;
        }
    }

    public void setRichtung(String r){
        switch(r){
            case "L":
            case "R":
            richtung = r;
            break;
            default:System.out.println("Bitte nur L oder R eingeben");
        }
    }

    public String getRichtung(){
        return richtung;
    }

    public int getBeruehrbar(){
        return beruehrbar;
    }

    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }

    public void sterben(){
        amLeben = false;
    }

    public int getLeben(){
        return leben;
    }

    public double getGeschwindigkeitY(){
        return geschwindigkeitY;
    }
    
    public void setLeben(int i){
        leben = i;
    }
    
    public void zeichnen(Graphics g, Spielfeld spielfeld){
        g.drawImage(this.getBild(),(int)x-spielfeld.level.spieler.getXVerschiebung(),(int)y-spielfeld.level.spieler.getYVerschiebung(),spielfeld);
    }

}
