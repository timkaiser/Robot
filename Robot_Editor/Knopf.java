import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Knopf extends JButton implements ActionListener{
    private char code;
    private int zahl;
    private BufferedImage img;
    private int extras[];
    private Extras extraPanel;
    public Knopf(char code,int zahl,String imageAdresse,BufferedImage img,int extrasLength,Extras extra){
        setPreferredSize(new Dimension(64,64));
        setIconImage(imageAdresse);
        this.code =code;
        this.zahl = zahl;
        this.img = img;
        addActionListener(this);
        setVisible(true);
        extras = new int[extrasLength];
        extraPanel = extra;
    }

    public Knopf(char code,int zahl){
        setSize(50,50);
        this.code =code;
        this.zahl = zahl;
        addActionListener(this);
        setVisible(true);
    }

    public void setIconImage(String adresse){
        setIcon(new ImageIcon(adresse));
    }

    public BufferedImage getImage(){
        return img;
    }

    public void setImage(BufferedImage img){
        this.img = img;
    }

    public int getZahl(){
        return zahl;
    }

    public char getCode(){
        return code;
    }

    public int[] getExtras(){
        return extras;
    }

    public int getExtras(int index){
        return extras[index];
    }

    public void setExtras(int[] extras){
        this.extras = extras;
    }

    public void setExtras(int index,int wert){
        extras[index] = wert;
    }

    public void setZahl(int z){
        zahl = z;
    }

    public void setCode(char a){
        code = a;
    }

    public void actionPerformed(ActionEvent e){
        Frame.maus.setCode(code);
        Frame.maus.setZahl(zahl);
        Frame.maus.setExtras(extras);
        extraPanel.update(this);
    }

}