import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;

public class Schleife implements Runnable{
    private boolean laeuft=true;
    private ScrollPane p;
    public Schleife(ScrollPane p){
        Thread gameloop = new Thread (this);
        gameloop.start();
        this.p = p;
    }

    public void run(){
        while(laeuft){       
            
            p.requestFocus();
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
            }
        }
    }

    private void input(){

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

    }
}