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
import java.awt.Graphics;

public class Pausenmenue extends JPanel implements ActionListener{
    Knopf fortsetzen;
    Knopf optionen;
    Knopf zurueckZumMenue;
    Knopf beenden;

    Knopf zurueck;

    TastenWahlKnopf linksTastenWahlKnopf;
    TastenWahlKnopf rechtsTastenWahlKnopf;
    TastenWahlKnopf springenTastenWahlKnopf;
    TastenWahlKnopf gadget1EinsetzenTastenWahlKnopf;
    TastenWahlKnopf gadget2EinsetzenTastenWahlKnopf;
    TastenWahlKnopf interagierenTastenWahlKnopf;
    TastenWahlKnopf menueTastenWahlKnopf;

    List<Knopf> buttons;
    int focus=0;
    private Sound moveSound;
    private Sound clickSound;
    public Pausenmenue() {
        setLayout(null);
        setBounds(180,0,450,600);
        setBackground(new Color(200,200,200));

        setFocusable(false);
        buttons = new ArrayList<Knopf>();
        fortsetzen=new Knopf(0,250,400,50,"fortsetzen",this);       
        fortsetzen.setFocus();        
        optionen=new Knopf(0,300,400,50,"optionen",this);
        zurueckZumMenue=new Knopf(0,350,400,50,"hauptmenü",this);        
        beenden=new Knopf(0,400,400,50,"beenden",this);

        zurueck=new Knopf(100,500,400,50,"zurück",this);



        linksTastenWahlKnopf            = new TastenWahlKnopf(10,50,5000,50, "         links  ", KeyEvent.getKeyText(Start.menue.getLinksTaste()).toUpperCase(),this);
        rechtsTastenWahlKnopf           = new TastenWahlKnopf(13,100,5000,50,"       rechts  ", KeyEvent.getKeyText(Start.menue.getRechtsTaste()).toUpperCase(),this);
        springenTastenWahlKnopf         = new TastenWahlKnopf(10,150,5000,50,"    springen  ", KeyEvent.getKeyText(Start.menue.getSpringenTaste()).toUpperCase(),this);
        gadget1EinsetzenTastenWahlKnopf = new TastenWahlKnopf(6,200,5000,50,"    gadget  1  ", KeyEvent.getKeyText(Start.menue.getGadget1EinsetzenTaste()).toUpperCase(),this);
        gadget2EinsetzenTastenWahlKnopf = new TastenWahlKnopf(4,250,5000,50,"    gadget 2  ", KeyEvent.getKeyText(Start.menue.getGadget2EinsetzenTaste()).toUpperCase(),this);
        interagierenTastenWahlKnopf     = new TastenWahlKnopf(3,300,5000,50,"interagieren  ", KeyEvent.getKeyText(Start.menue.getInteragierenTaste()).toUpperCase(),this);
        menueTastenWahlKnopf            = new TastenWahlKnopf(5,350,5000,50,"       menue  ", KeyEvent.getKeyText(Start.menue.getMenueTaste()).toUpperCase(),this);

        tastenAuktualisieren();

        startmenueHinzufuegen();

        moveSound=new Sound("fins__button.wav");
        clickSound=new Sound("fins__failure.wav");

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
        linksTastenWahlKnopf.setText("         links  ",KeyEvent.getKeyText(Start.menue.getLinksTaste()).toUpperCase());
        rechtsTastenWahlKnopf.setText("       rechts  ",KeyEvent.getKeyText(Start.menue.getRechtsTaste()).toUpperCase());
        springenTastenWahlKnopf.setText("    springen  ",KeyEvent.getKeyText(Start.menue.getSpringenTaste()).toUpperCase());
        gadget1EinsetzenTastenWahlKnopf.setText("    gadget  1  ",KeyEvent.getKeyText(Start.menue.getGadget1EinsetzenTaste()).toUpperCase());
        gadget2EinsetzenTastenWahlKnopf.setText("    gadget 2  ",KeyEvent.getKeyText(Start.menue.getGadget2EinsetzenTaste()).toUpperCase());
        interagierenTastenWahlKnopf.setText("interagieren  ",KeyEvent.getKeyText(Start.menue.getInteragierenTaste()).toUpperCase()); 
        menueTastenWahlKnopf.setText("       menue  ",KeyEvent.getKeyText(Start.menue.getMenueTaste()).toUpperCase()); 
    }

    public void einstellungenSpeichern(){
        try {   
            FileOutputStream output = new FileOutputStream("einstellungen.opt");
            DataOutputStream  datop  = new DataOutputStream(output);

            try {
                datop.writeInt(Start.menue.getLinksTaste());
                datop.writeInt(Start.menue.getRechtsTaste());
                datop.writeInt(Start.menue.getSpringenTaste());
                datop.writeInt(Start.menue.getGadget1EinsetzenTaste());
                datop.writeInt(Start.menue.getGadget2EinsetzenTaste());
                datop.writeInt(Start.menue.getInteragierenTaste());
                datop.writeInt(Start.menue.getMenueTaste());

            } catch (IOException e) {e.printStackTrace();}

            try {
                output.close();
            } catch (IOException e1) {}
        } catch (IOException e) {e.printStackTrace();}
    }

    private void startmenueHinzufuegen(){
        tastenAuktualisieren();
        
        buttons.add(fortsetzen);
        buttons.add(optionen);
        buttons.add(zurueckZumMenue);
        buttons.add(beenden);

        for(int i=0; i<buttons.size(); i++){
            add(buttons.get(i));
        }

        focus=0;
        buttons.get(focus).setFocus();

        repaint();
    }

    private void fortsetzen(){
        setVisible(false);
    }

    public void actionPerformed(ActionEvent  e) {
        repaint();
        clickSound.start();

        if(e.getSource()==zurueck){
            einstellungenSpeichern();
            leeren();
            startmenueHinzufuegen();
        }
        if(e.getSource()==fortsetzen){         
            fortsetzen();
        }
        if(e.getSource()==optionen){           
            leeren();
            steuerungswahlHinzufuegen();
            buttons.get(focus).setFocus();
        }
        if(e.getSource()==zurueckZumMenue){
            Level.speichern();
            Level.geschafteLevel.clear();
            Start.fenster.remove(Start.pausenmenue);
            Start.fenster.remove(Start.spielfeld);
            Start.fenster.add(Start.menue);    
            Start.spielschleife.beenden();
            Start.fenster.repaint();
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
        if(Start.menue.getLinksTaste()==taste||            
        Start.menue.getRechtsTaste()==taste||       
        Start.menue.getSpringenTaste()==taste||        
        Start.menue.getGadget1EinsetzenTaste()==taste||
        Start.menue.getGadget2EinsetzenTaste()==taste||
        Start.menue.getInteragierenTaste()==taste||    
        Start.menue.getMenueTaste()==taste){
            return true;
        } else{
            return false;
        }
    }

    public void input(int k) {
        if(k==Start.menue.getMenueTaste()){
            zurueck();
        }else

        if(linksTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setLinksTaste(k); 
                linksTastenWahlKnopf.setText("         links  ",KeyEvent.getKeyText(Start.menue.getLinksTaste()).toUpperCase());                
            }
            linksTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(rechtsTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setRechtsTaste(k); 
                rechtsTastenWahlKnopf.setText("       rechts  ",KeyEvent.getKeyText(Start.menue.getRechtsTaste()).toUpperCase());
            }
            rechtsTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(springenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setSpringenTaste(k); 
                springenTastenWahlKnopf.setText("    springen  ",KeyEvent.getKeyText(Start.menue.getSpringenTaste()).toUpperCase());
            }
            springenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(gadget1EinsetzenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setGadget1EinsetzenTaste(k); 
                gadget1EinsetzenTastenWahlKnopf.setText("    gadget  1  ",KeyEvent.getKeyText(Start.menue.getGadget1EinsetzenTaste()).toUpperCase());
            }
            gadget1EinsetzenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(gadget2EinsetzenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setGadget2EinsetzenTaste(k); 
                gadget2EinsetzenTastenWahlKnopf.setText("    gadget 2  ",KeyEvent.getKeyText(Start.menue.getGadget2EinsetzenTaste()).toUpperCase());
            }
            gadget2EinsetzenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(interagierenTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setInteragierenTaste(k); 
                interagierenTastenWahlKnopf.setText("interagieren  ",KeyEvent.getKeyText(Start.menue.getInteragierenTaste()).toUpperCase()); 
            }
            interagierenTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(menueTastenWahlKnopf.markiert()){
            if(!tasteBelegt(k)){
                Start.menue.setMenueTaste(k); 
                menueTastenWahlKnopf.setText("       menue  ",KeyEvent.getKeyText(Start.menue.getMenueTaste()).toUpperCase()); 
            }
            menueTastenWahlKnopf.entmarkieren();
            buttons.get(focus).setFocus();
        }else

        if(k==Start.menue.getLinksTaste()){
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

        if(k==Start.menue.getRechtsTaste()){
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

        if(k==Start.menue.getSpringenTaste()){
            if(!buttons.isEmpty()){
                buttons.get(focus).doClick();
            }

        }
    }

    private void zurueck(){
        boolean b=false;
        for(int i=0;i<buttons.size();i++){
            if(buttons.get(i).markiert()){
                b=true;
            }
        }
        if(b){
            alleEntmarkieren();
            buttons.get(focus).setFocus();
        }else{
            if(buttons.contains(zurueck)){
                einstellungenSpeichern();
                leeren();
                startmenueHinzufuegen();
            }else
                fortsetzen();
        }
    }
                     
}
