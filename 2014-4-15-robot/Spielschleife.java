import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;

public class Spielschleife implements Runnable{
    private boolean laeuft=true;
    private long duration, last,lastsprung,sprungzeit= 1500,gadgetZeit = 1500,lastGadget1,lastGadget2;
    public Spielschleife(){
        Thread gameloop = new Thread (this);
        gameloop.start();
    }

    public void run(){
        Date schlafzeit=new Date();
        while(laeuft){
            if(!Start.pausenmenue.isVisible()&& Start.spielfeld!=null){
                duration = System.currentTimeMillis() - last;
                last = System.currentTimeMillis();
                lastsprung += duration;
                lastGadget1 += duration;
                lastGadget2 += duration;
                input();
                berechnung();
                spielerKollision();

                Start.spielfeld.repaint(); 
            }

            try {
                long t=(schlafzeit.getTime()+   25   )-(new Date()).getTime();

                if(t>0){ Thread.sleep(t); };
                //System.out.println(t);
                schlafzeit=new Date();
            } catch (InterruptedException e) {}
        }
    }

    private void spielerKollision(){
        for(int i = 1;i < Spielfeld.level.getAnzahlEinheiten();i++){
            Spielfeld.level.getEinheit(i).spielerKollision();
        }
    }

    private void input(){
        // if(!Start.pausenmenue.isVisible()){
        if(Input.isKeyDown(Hauptmenue.getGadget1EinsetzenTaste())){
            if(lastGadget1>500){
                Spielfeld.level.getSpieler().gadgetAktivieren(1);             
                lastGadget1 = 0;
            }
        }
        if(Input.isKeyDown(Hauptmenue.getGadget2EinsetzenTaste())){
            if(lastGadget2>500){
                Spielfeld.level.getSpieler().gadgetAktivieren(2);             
                lastGadget2 = 0;
            }
        }
        if(Input.isKeyDown(Hauptmenue.getInteragierenTaste())){
            if(!Spielfeld.level.getSpieler().getInteraktionsbereitschaft()){
                Spielfeld.level.getSpieler().setInteraktionsbereitschaft(true);
            }else{
                Spielfeld.level.getSpieler().setInteraktionsbereitschaft(false);
            }
        }else{
            Spielfeld.level.getSpieler().setInteraktionsbereitschaft(false);
        }
        if(Input.isKeyDown(Hauptmenue.getMenueTaste())){
            Start.pausenmenue.setVisible(true);
        }
    }

    public void beenden(){
        laeuft=false;
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
        }
    }

    public void berechnung(){
        for(int i = 0;i < Spielfeld.level.getAnzahlEinheiten();i++){
            Spielfeld.level.getEinheit(i).berechnen();
        }

        for(int i = 0;i < Spielfeld.level.getAnzahlEinheiten();i++){
            Spielfeld.level.getEinheit(i).bewegen();
        }                            

        for(int i = 0;i < Spielfeld.level.getAnzahlBloecke();i++){
            Spielfeld.level.getBlock(i).special();
        }

    }
}