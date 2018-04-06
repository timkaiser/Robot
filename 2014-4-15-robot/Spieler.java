import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Spieler extends Einheit{

    boolean interaktionsbereit=false;
    private int xVerschiebung=0;
    private int yVerschiebung=0;
    private double maxSpeed=10;
    private Gadget gadget1,gadget2;
    private double beschleunigung;
    private int counter;
    public Spieler(int xk,int yk){    
        x = xk;
        y = yk;
        width = 64;
        height = 96;
        xVerschiebung=(int)x-200;
        yVerschiebung=(int)y-300;
        beruehrbar = 0;
        richtung="R";
        lookR = new Animation(Bilder.getSpielerBild(),64,10000);
        lookL =lookR;
        setSprungHoehe(64*4+10);
        gadget1 = new DoppelSprungGadget(this);
        gadget2 = new SchrumpfGadget(this);
        sprung=false;
        gravitation=1.0;
        beschleunigung = 1.0;
        counter = 0;
    }

    public void setLookR(BufferedImage i){
        lookR =new Animation(i,64,10000);
    }
    
    public void gadgetAktivieren(int a){
        if(a == 1 && gadget1!=null){
            gadget1.aktivieren();
        }
        else if(a == 2 && gadget2!=null){
            gadget2.aktivieren();
        }
    }

    public void setMaxSpeed(double speed){
        maxSpeed = speed;
    }

    public void setBeschleunigung(double beschleunigung){
        this.beschleunigung = beschleunigung;
    }

    public void berechnen(){
        if(!(gadget1 instanceof DoppelSprungGadget)&& !(gadget2 instanceof DoppelSprungGadget)){
            if(Input.isKeyDown(Hauptmenue.getSpringenTaste())){
                boolean b = kollisionMitBoden();
                if(b)
                    sprung();
            }
        }
        else{
            if(gadget1 instanceof DoppelSprungGadget){
                gadget1.ausfuehren();
            }
            else if(gadget2 instanceof DoppelSprungGadget){
                gadget2.ausfuehren();
            }
            boolean b = kollisionMitBoden();
            if(b)
                counter = 0;
        }
        if(gadget2 != null){
            gadget1.special();
            gadget2.special();
        }
        else if(gadget1 != null){
            gadget1.special();
        }
        if(Input.isKeyDown(Hauptmenue.getLinksTaste())){
            if(geschwindigkeitX>=-maxSpeed)
                geschwindigkeitX-=beschleunigung;
        }else if(Input.isKeyDown(Hauptmenue.getRechtsTaste())){
            if(geschwindigkeitX<=maxSpeed)
                geschwindigkeitX+=beschleunigung;

        }else if(geschwindigkeitX>0){
            geschwindigkeitX--;
        }else if(geschwindigkeitX<0){
            geschwindigkeitX++;
        }
    }

    public void bewegen(){
        super.bewegen();

     
        
        if(y-yVerschiebung<200)
            yVerschiebung=(int)y-200;   
        if(y-yVerschiebung>300)
            yVerschiebung=(int)y-300;

        if(x-xVerschiebung<250 || x-xVerschiebung>400){
            xVerschiebung+=geschwindigkeitX;       
        }
    }
    
        public void bewegeUmXY(int streckeX,int streckeY){
        super.bewegeUmXY(streckeX,streckeY);

     
        
        if(y-yVerschiebung<200)
            yVerschiebung=(int)y-200;   
        if(y-yVerschiebung>300)
            yVerschiebung=(int)y-300;

        if(x-xVerschiebung<250 || x-xVerschiebung>400){
            xVerschiebung+=geschwindigkeitX;       
        }
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(int a){
        counter+=a;
    }

    public void setPosition(int xPosition, int yPosition){
        x=xPosition;
        y=yPosition;
        xVerschiebung=(int)x-300;
        yVerschiebung=(int)y-300;
    }

    public void setInteraktionsbereitschaft(boolean b){
        interaktionsbereit=b;
    }

    public boolean getInteraktionsbereitschaft(){
        return interaktionsbereit;
    }

    public int getXVerschiebung(){
        return xVerschiebung;
    }

    public int getYVerschiebung(){
        return yVerschiebung;
    }
    
    public double getXGeschwindigkeit(){
        return geschwindigkeitX;
    }

    public double getYGeschwindigkeit(){
        return geschwindigkeitY;
    }

    public Gadget getGadget1(){
        return gadget1;
    }

    public Gadget getGadget2(){
        return gadget2;
    }

    public void gadgetHinzufuegen(Gadget g, int i){
        if(i==0){
            gadget1 = g;
        }else{
            gadget2 = g;
        }
    }

    public void gadgetHinzufuegen(Gadget g){
        if(gadget1 == null){
            gadgetHinzufuegen(g,0);
        }
        else {
            gadgetHinzufuegen(g,1);
        }
    }
    
    public void damage(){
        if(beruehrbar<=0){
            if(gadget2 instanceof SchildGadget){
                g2auswerfen();
            }else if(gadget1 instanceof SchildGadget){
                g1auswerfen();
            }else if(gadget2==null){
                if(gadget1!=null){
                    g1auswerfen();
                }else{
                    Spielfeld.level=new Level(0,3);
                }
            }else if((int)(Math.random()*2)==1&&gadget2!=null){
                g2auswerfen();
            }else{
                g1auswerfen();
            }   
        }
        super.damage();
    }

    private void g1auswerfen(){
        gadget1=gadget1.auswerfen();
        if(gadget1==null){
            gadget1=gadget2;
            gadget2=null;
        }
    }

    private void g2auswerfen(){
        gadget2=gadget2.auswerfen();
    }
}