import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Zeichenpanel panel;
    private WerkzeugPanel wPanel;
    private Menue menue;
    public static AuswahlPanel auswahlPanel;
    public static Maus maus;
    private ScrollPane scroll;
    public Gitter[][] gitter;
    public static int gitterZahl;
    private Extras extraPanel;
    public Frame(int breite,int hoehe,int gitterGroese){
        setSize(1200,800);
        setLocation(200,200);
        setTitle("RobotEditor");
        setLayout(null);
        setDefaultCloseOperation(3);
        new Bilder();
        maus = new Maus();
        gitter = new Gitter[breite][hoehe];
        gitterZahl = gitterGroese;
        extraPanel = new Extras(0,400,200,400);
        add(extraPanel);
        
        wPanel = new WerkzeugPanel(0,0,1100,50);
        add(wPanel);
        
        menue = new Menue(gitter,this);
        menue.setVisible(true);
        setJMenuBar(menue);

        auswahlPanel = new AuswahlPanel(extraPanel);
        auswahlPanel.setBounds(0,50,200,400);
        this.add(auswahlPanel);

        for(int i = 0;i < gitter.length;i++){
            for(int j = 0; j < gitter[0].length;j++){
                gitter[i][j] = new Gitter(i*gitterGroese,j*gitterGroese,gitterGroese,gitterGroese);
            }
        }

        panel = new Zeichenpanel(0,0,gitter);
        scroll = new ScrollPane(panel,gitter);
        scroll.setBounds(200,50,800,600);
        scroll.setVisible(true);
        add(scroll);

        setVisible(true);
        
        new Schleife(scroll);
    }

    public static void main(String[] args){
        new Frame(25,25,64);
    }

    public Zeichenpanel getZeichenPanel(){
        return panel;
    }
    
}