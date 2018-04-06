import java.awt.Rectangle;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TastenWahlKnopf extends Knopf{
    Rectangle rect;
    boolean markiert=false;
    String tastencode;
    public TastenWahlKnopf(int x,int y,int breite, int hoehe,  String text, String text2, ActionListener acLi)   {
        super(x, y, breite,hoehe,text,acLi);
        tastencode=text2;
    }

    public void setText(String t1,String t2){
        setText(t1);
        if(t2.equalsIgnoreCase("LEERTASTE")){
            t2=" ";
        }
        tastencode=t2;
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        g.setFont(Start.getTastenfont(60));
        g.drawString(tastencode,220,33);
    }
}

