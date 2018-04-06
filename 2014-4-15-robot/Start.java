import javax.swing.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Start{
    //Fenster
    public static JFrame fenster;
    //container
    static Hauptmenue menue;
    static Spielfeld spielfeld;
    static Pausenmenue pausenmenue;
    static Spielschleife spielschleife;

    private static Font font;
    private static Font tastenfont;
    //main methode zum starten des programms: aufrufen des konstruktors
    public static void main(String[] args){
        new Start();
    }
    //konstruktor: erstellt fenster und hauptmenue
    public Start(){        
        InputStream fin = this.getClass().getResourceAsStream("quadrangle.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        fin = this.getClass().getResourceAsStream("dh_gentry_b.ttf");
        try {tastenfont = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        fenster=new JFrame();
        fenster.setSize(806,633);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setLayout(null);
        fenster.setResizable(true);
        fenster.setLocationRelativeTo(null);  
        fenster.add(menue=new Hauptmenue());
        menue.setBounds(0,0,800,600);
        einstellungenLaden();  
        fenster.setVisible(true);
        fenster.revalidate();

    }

    public static Font getFont(int groesse){
        return font.deriveFont((float) groesse);
    }

    public static Font getTastenfont(int groesse){
        return tastenfont.deriveFont((float) groesse);
    }

    private void einstellungenLaden(){
        try {
            FileInputStream iostream = new FileInputStream("einstellungen.opt");
            DataInputStream diostrem = new DataInputStream(iostream);
            try {
                menue.tastenSetzen(diostrem.readInt(),diostrem.readInt(),diostrem.readInt(),diostrem.readInt(),diostrem.readInt(),diostrem.readInt(),diostrem.readInt());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}
        menue.einstellungenSpeichern();
    }

    public void test(){
        Start.fenster.remove(Start.menue);
        JButton p=new JButton();
        p.setBounds(0,0,800,600);
        Start.fenster.add(p);
        Start.fenster.revalidate();
        Start.fenster.repaint();
    }

    public Spieler getSpieler(){
        return spielfeld.level.getSpieler();
    }
}

