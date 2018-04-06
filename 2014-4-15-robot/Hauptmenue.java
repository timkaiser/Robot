import javax.swing.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Hauptmenue extends JPanel implements KeyListener,  ActionListener{
    Knopf spielStarten;
    Knopf optionen;
    Knopf credits;
    Knopf beenden;

    Knopf zurueck;

    TastenWahlKnopf linksTastenWahlKnopf;
    TastenWahlKnopf rechtsTastenWahlKnopf;
    TastenWahlKnopf springenTastenWahlKnopf;
    TastenWahlKnopf gadget1EinsetzenTastenWahlKnopf;
    TastenWahlKnopf gadget2EinsetzenTastenWahlKnopf;
    TastenWahlKnopf interagierenTastenWahlKnopf;
    TastenWahlKnopf menueTastenWahlKnopf;

    Knopf spielstand1;
    Knopf spielstand2;
    Knopf spielstand3;

    static int linksTaste=KeyEvent.VK_A;
    static int rechtsTaste=KeyEvent.VK_D;
    static int springenTaste=KeyEvent.VK_SPACE;
    static int gadget1EinsetzenTaste=KeyEvent.VK_Q;
    static int gadget2EinsetzenTaste=KeyEvent.VK_E;
    static int interagierenTaste=KeyEvent.VK_F;
    static int menueTaste=KeyEvent.VK_ESCAPE;

    static int spielstand11LvlGeschaft;
    static int spielstand2LvlGeschaft;
    static int spielstand3LvlGeschaft;

    JLabel ueberschrift;
    List<Knopf> buttons;
    int focus=0;
    private Sound moveSound;
    private Sound clickSound;
    public Hauptmenue() {
        setLayout(null);

        buttons = new ArrayList<Knopf>();

        ueberschrift= new JLabel("ROBOT");
        ueberschrift.setBounds(125,0,700,250);
        ueberschrift.setFont(Start.getFont(100));
        ueberschrift.setForeground(new Color(94,0,0));
        add(ueberschrift);

        spielStarten = new Knopf(200,250,400,50,"spiel starten",this);       
        optionen = new Knopf(200,300,400,50,"optionen",this);
        credits = new Knopf(200,350,400,50,"credits",this);        
        beenden = new Knopf(200,400,400,50,"beenden",this);

        spielstand1 = new Knopf(200,150,400,100,"Spielstand 1",this);
        spielstand1.setBorderPainted(true);
        spielstand2 = new Knopf(200,250,400,100,"Spielstand 2",this);
        spielstand2.setBorderPainted(true);
        spielstand3 = new Knopf(200,350,400,100,"Spielstand 3",this);
        spielstand3.setBorderPainted(true);

        zurueck=new Knopf(200,500,400,50,"zurück",this);

        linksTastenWahlKnopf            = new TastenWahlKnopf(105,50,5000,50, "         links  ", KeyEvent.getKeyText(linksTaste).toUpperCase(),this);
        rechtsTastenWahlKnopf           = new TastenWahlKnopf(107,100,5000,50,"       rechts  ", KeyEvent.getKeyText(rechtsTaste).toUpperCase(),this);
        springenTastenWahlKnopf         = new TastenWahlKnopf(104,150,5000,50,"    springen  ", KeyEvent.getKeyText(springenTaste).toUpperCase(),this);
        gadget1EinsetzenTastenWahlKnopf = new TastenWahlKnopf(101,200,5000,50,"    gadget  1  ", KeyEvent.getKeyText(gadget1EinsetzenTaste).toUpperCase(),this);
        gadget2EinsetzenTastenWahlKnopf = new TastenWahlKnopf(99,250,5000,50,"    gadget 2  ", KeyEvent.getKeyText(gadget2EinsetzenTaste).toUpperCase(),this);
        interagierenTastenWahlKnopf     = new TastenWahlKnopf(98,300,5000,50,"interagieren  ", KeyEvent.getKeyText(interagierenTaste).toUpperCase(),this);
        menueTastenWahlKnopf            = new TastenWahlKnopf(100,350,5000,50,"       menue  ", KeyEvent.getKeyText(menueTaste).toUpperCase(),this);

        startmenueHinzufuegen();

        moveSound  = new Sound("fins__button.wav");
        clickSound = new Sound("fins__failure.wav");

        setFocusable(true);
        addKeyListener(this);

    }

    private void leeren(){
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).removeFocus();
        }
        removeAll();
        buttons.clear();
        repaint();
    }

    private void steuerungswahlHinzufuegen(){
        tastenAuktualisieren();
        
        buttons.add( linksTastenWahlKnopf);
        buttons.add( rechtsTastenWahlKnopf);
        buttons.add( springenTastenWahlKnopf);
        buttons.add( gadget1EinsetzenTastenWahlKnopf);
        buttons.add( gadget2EinsetzenTastenWahlKnopf);
        buttons.add( interagierenTastenWahlKnopf);
        buttons.add( menueTastenWahlKnopf);

        buttons.add(zurueck);

        for(int i=0; i<buttons.size(); i++){
            add(buttons.get(i));
        }

        focus=buttons.size()-1;
        buttons.get(focus).setFocus();        

        repaint();
    }

    public void tastenAuktualisieren(){
        linksTastenWahlKnopf.setText("         links  ",KeyEvent.getKeyText(linksTaste).toUpperCase());
        rechtsTastenWahlKnopf.setText("       rechts  ",KeyEvent.getKeyText(rechtsTaste).toUpperCase());
        springenTastenWahlKnopf.setText("    springen  ",KeyEvent.getKeyText(springenTaste).toUpperCase());
        gadget1EinsetzenTastenWahlKnopf.setText("    gadget  1  ",KeyEvent.getKeyText(gadget1EinsetzenTaste).toUpperCase());
        gadget2EinsetzenTastenWahlKnopf.setText("    gadget 2  ",KeyEvent.getKeyText(gadget2EinsetzenTaste).toUpperCase());
        interagierenTastenWahlKnopf.setText("interagieren  ",KeyEvent.getKeyText(interagierenTaste).toUpperCase()); 
        menueTastenWahlKnopf.setText("       menue  ",KeyEvent.getKeyText(menueTaste).toUpperCase()); 
    }

    public void tastenSetzen(int l,int r,int s,int g1,int g2,int i,int m){
        linksTaste=l;
        linksTastenWahlKnopf.setText("         links  ",KeyEvent.getKeyText(linksTaste).toUpperCase());
        rechtsTaste=r;
        rechtsTastenWahlKnopf.setText("       rechts  ",KeyEvent.getKeyText(rechtsTaste).toUpperCase());
        springenTaste=s;
        springenTastenWahlKnopf.setText("    springen  ",KeyEvent.getKeyText(springenTaste).toUpperCase());
        gadget1EinsetzenTaste=g1;
        gadget1EinsetzenTastenWahlKnopf.setText("    gadget  1  ",KeyEvent.getKeyText(gadget1EinsetzenTaste).toUpperCase());
        gadget2EinsetzenTaste=g2;
        gadget2EinsetzenTastenWahlKnopf.setText("    gadget 2  ",KeyEvent.getKeyText(gadget2EinsetzenTaste).toUpperCase());
        interagierenTaste=i;
        interagierenTastenWahlKnopf.setText("interagieren  ",KeyEvent.getKeyText(interagierenTaste).toUpperCase()); 
        menueTaste=m;
        menueTastenWahlKnopf.setText("       menue  ",KeyEvent.getKeyText(menueTaste).toUpperCase()); 
    }

    public void einstellungenSpeichern(){
        try {   
            FileOutputStream output = new FileOutputStream("einstellungen.opt");
            DataOutputStream  datop  = new DataOutputStream(output);

            try {
                datop.writeInt(linksTaste);
                datop.writeInt(rechtsTaste);
                datop.writeInt(springenTaste);
                datop.writeInt(gadget1EinsetzenTaste);
                datop.writeInt(gadget2EinsetzenTaste);
                datop.writeInt(interagierenTaste);
                datop.writeInt(menueTaste);

            } catch (IOException e) {e.printStackTrace();}

            try {
                output.close();
            } catch (IOException e1) {}
        } catch (IOException e) {e.printStackTrace();}
    }

    private void startmenueHinzufuegen(){
        buttons.add(spielStarten);
        buttons.add(optionen);
        buttons.add(credits);
        buttons.add(beenden);

        add(ueberschrift);

        for(int i=0; i<buttons.size(); i++){
            add(buttons.get(i));
        }

        focus=0;
        buttons.get(focus).setFocus();

        repaint();
    }

    public void actionPerformed(ActionEvent  e) {
        clickSound.start();
        requestFocus();

        if(e.getSource()==spielstand1){
            new Spielstart("spielstand1");
        }

        if(e.getSource()==spielstand2){
            new Spielstart("spielstand2");
        }

        if(e.getSource()==spielstand3){

            new Spielstart("spielstand3");
        }

        if(e.getSource()==zurueck){
            einstellungenSpeichern();
            leeren();
            startmenueHinzufuegen();
        }

        if(e.getSource()==spielStarten){
            leeren();
            focus=0;
            buttons.add(spielstand1);
            buttons.add(spielstand2);
            buttons.add(spielstand3);

            add(spielstand1);
            add(spielstand2);
            add(spielstand3);

            buttons.add(zurueck);
            add(zurueck);
            buttons.get(focus).setFocus();
        }
        if(e.getSource()==optionen){           
            leeren();
            steuerungswahlHinzufuegen();
            buttons.get(focus).setFocus();
        }
        if(e.getSource()==credits){
            leeren();
            focus=0;
            buttons.add(zurueck);
            add(zurueck);
            buttons.get(focus).setFocus();
        }
        if(e.getSource()==beenden){
            System.exit( 0 );
        }

        if(e.getSource()== linksTastenWahlKnopf){
            alleEntmarkieren();
            linksTastenWahlKnopf.markieren();
        }

        if(e.getSource()== rechtsTastenWahlKnopf){
            alleEntmarkieren();
            rechtsTastenWahlKnopf.markieren();
        }

        if(e.getSource()== springenTastenWahlKnopf){
            alleEntmarkieren();
            springenTastenWahlKnopf.markieren();
        }

        if(e.getSource()== gadget1EinsetzenTastenWahlKnopf){
            alleEntmarkieren();
            gadget1EinsetzenTastenWahlKnopf.markieren();
        }

        if(e.getSource()== gadget2EinsetzenTastenWahlKnopf){
            alleEntmarkieren();
            gadget2EinsetzenTastenWahlKnopf.markieren();
        }

        if(e.getSource()== interagierenTastenWahlKnopf){
            alleEntmarkieren();
            interagierenTastenWahlKnopf.markieren();
        }

        if(e.getSource()== menueTastenWahlKnopf){
            alleEntmarkieren();
            menueTastenWahlKnopf.markieren();
        }

    }

    private void alleEntmarkieren(){
        linksTastenWahlKnopf.entmarkieren();            
        rechtsTastenWahlKnopf.entmarkieren();       
        springenTastenWahlKnopf.entmarkieren();        
        gadget1EinsetzenTastenWahlKnopf.entmarkieren();
        gadget2EinsetzenTastenWahlKnopf.entmarkieren();
        interagierenTastenWahlKnopf.entmarkieren();    
        menueTastenWahlKnopf.entmarkieren();        
    }

    private boolean tasteBelegt(int taste){
        if(        linksTaste==taste||            
        rechtsTaste==taste||       
        springenTaste==taste||        
        gadget1EinsetzenTaste==taste||
        gadget2EinsetzenTaste==taste||
        interagierenTaste==taste||    
        menueTaste==taste){
            return true;
        } else{
            return false;
        }
    }

    public void keyPressed(KeyEvent k) {
        if(k.getKeyCode()==menueTaste){
            for(int i=0; i<buttons.size(); i++){
                buttons.get(i).entmarkieren();
            }
            buttons.get(focus).setFocus();
        }else

        if(linksTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                linksTaste=k.getKeyCode(); 
                linksTastenWahlKnopf.setText("         links  ",KeyEvent.getKeyText(linksTaste).toUpperCase());                
            }
            linksTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(rechtsTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                rechtsTaste=k.getKeyCode(); 
                rechtsTastenWahlKnopf.setText("       rechts  ",KeyEvent.getKeyText(rechtsTaste).toUpperCase());
            }
            rechtsTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(springenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                springenTaste=k.getKeyCode(); 
                springenTastenWahlKnopf.setText("    springen  ",KeyEvent.getKeyText(springenTaste).toUpperCase());
            }
            springenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(gadget1EinsetzenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                gadget1EinsetzenTaste=k.getKeyCode(); 
                gadget1EinsetzenTastenWahlKnopf.setText("    gadget  1  ",KeyEvent.getKeyText(gadget1EinsetzenTaste).toUpperCase());
            }
            gadget1EinsetzenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(gadget2EinsetzenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                gadget2EinsetzenTaste=k.getKeyCode(); 
                gadget2EinsetzenTastenWahlKnopf.setText("    gadget 2  ",KeyEvent.getKeyText(gadget2EinsetzenTaste).toUpperCase());
            }
            gadget2EinsetzenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(interagierenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                interagierenTaste=k.getKeyCode(); 
                interagierenTastenWahlKnopf.setText("interagieren  ",KeyEvent.getKeyText(interagierenTaste).toUpperCase()); 
            }
            interagierenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(menueTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k.getKeyCode())){
                menueTaste=k.getKeyCode(); 
                menueTastenWahlKnopf.setText("       menue  ",KeyEvent.getKeyText(menueTaste).toUpperCase()); 
            }
            menueTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(k.getKeyCode()==linksTaste){
            if(!buttons.isEmpty()){
                moveSound.start();
                buttons.get(focus).removeFocus();
                if(focus<=0){
                    focus=buttons.size()-1;
                } else{
                    focus--;
                }            
                buttons.get(focus).setFocus();
            }        

        }else

        if(k.getKeyCode()==rechtsTaste){
            if(!buttons.isEmpty()){
                moveSound.start();       
                buttons.get(focus).removeFocus();
                if(focus>=buttons.size()-1){
                    focus=0;
                } else{
                    focus++;
                }            
                buttons.get(focus).setFocus();
            }
        }else

        if(k.getKeyCode()==springenTaste){
            if(!buttons.isEmpty()){
                buttons.get(focus).doClick();
            }

        }

    }

    public void keyReleased(KeyEvent k) {

    }

    public void keyTyped(KeyEvent k) {
    }

    public static int getLinksTaste(){
        return linksTaste;
    }

    public static int getRechtsTaste(){
        return rechtsTaste;
    }

    public static int getSpringenTaste(){
        return springenTaste;
    }

    public static int getGadget1EinsetzenTaste(){
        return gadget1EinsetzenTaste;
    }

    public static int getGadget2EinsetzenTaste(){
        return gadget2EinsetzenTaste;
    }

    public static int getInteragierenTaste(){
        return interagierenTaste;
    }

    public static int getMenueTaste(){
        return menueTaste;
    }    

    public static void setLinksTaste(int taste){
        linksTaste=taste;
    }

    public static void setRechtsTaste(int taste){
        rechtsTaste=taste;
    }

    public static void setSpringenTaste(int taste){
        springenTaste=taste;
    }

    public static void setGadget1EinsetzenTaste(int taste){
        gadget1EinsetzenTaste=taste;
    }

    public static void setGadget2EinsetzenTaste(int taste){
        gadget2EinsetzenTaste=taste;
    }

    public static void setInteragierenTaste(int taste){
        interagierenTaste=taste;
    }

    public static void setMenueTaste(int taste){
        menueTaste=taste;
    }                       
}
