import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser; 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener; 
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import javax.swing.filechooser.FileNameExtensionFilter; 
public class Menue extends JMenuBar{
    private JMenu menu;
    private JMenuItem neu,speichern,oeffnen,groesse;
    private  Gitter[][] gitter;
    private boolean[][] gespeichert;
    private Frame f;
    public Menue(Gitter[][] gitter,Frame frame){
        menu = new JMenu("MENÜ");
        this.gitter = gitter;

        gespeichert = new boolean[gitter.length][gitter[0].length];

        f = frame;
        neu = new JMenuItem("NEU");
        neu.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int breite = Integer.parseInt(JOptionPane.showInputDialog("Bitte breite des Levels eingeben!"));
                    int hoehe = Integer.parseInt(JOptionPane.showInputDialog("Bitte hoehe des Levels eingeben!"));
                    int groesse = Integer.parseInt(JOptionPane.showInputDialog("Bitte groesse der Blöcke eingeben!"));
                    f.dispose();
                    new Frame(breite,hoehe,groesse);
                }
            });
        menu.add(neu);

        speichern = new JMenuItem("SPEICHERN");
        speichern.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    String pfad;
                    JFileChooser chooser;

                    pfad = System.getProperty("user.home");
                    File file = new File(pfad.trim());

                    chooser = new JFileChooser(pfad);
                    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                    FileNameExtensionFilter plainFilter = new FileNameExtensionFilter(
                            "lvl", "lvl");
                    FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(
                            "lvl","lvl");

                    chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
                    chooser.setFileFilter(plainFilter);
                    chooser.setFileFilter(markUpFilter);
                    chooser.setDialogTitle("Speichern unter...");
                    chooser.setVisible(true);

                    int result = chooser.showSaveDialog(f);

                    if (result == JFileChooser.APPROVE_OPTION) {

                        pfad = chooser.getSelectedFile().toString();
                        file = new File(pfad);

                        chooser.setVisible(false);
                    }
                    chooser.setVisible(false);

                    speichern(pfad);
                }
            });
        menu.add(speichern);    

        oeffnen = new JMenuItem("OEFFNEN");
        oeffnen.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    final JFileChooser chooser = new JFileChooser("Verzeichnis wählen");
                    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    final File file = new File("/home");

                    chooser.setCurrentDirectory(file);

                    chooser.addPropertyChangeListener(new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
                                || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                                    final File f = (File) e.getNewValue();
                                }
                            }
                        });

                    chooser.setVisible(true);
                    final int result = chooser.showOpenDialog(null);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File inputVerzFile = chooser.getSelectedFile();
                        String inputVerzStr = inputVerzFile.getPath();
                        System.out.println("Eingabepfad:" + inputVerzStr);
                        zeichnen(inputVerzStr);
                    }
                    System.out.println("Abbruch");
                    chooser.setVisible(false); 
                }
            });
        menu.add(oeffnen);

        groesse = new JMenuItem("GRÖSSE");
        groesse.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    groesse();
                }

            });
        menu.add(groesse);

        add(menu);
    }

    public void groesse(){
        Gitter[][] g = gitter;
        int breite = Integer.parseInt(JOptionPane.showInputDialog("Bitte breite des Levels eingeben!"));
        int hoehe = Integer.parseInt(JOptionPane.showInputDialog("Bitte hoehe des Levels eingeben!"));
        f.dispose();
        new Frame(breite,hoehe,Frame.gitterZahl);
        if(g.length > breite){
            for(int i = 0;i< breite;i++){
                if(g[i].length > hoehe){
                    for(int j = 0;j < hoehe;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
                else if(g[i].length < hoehe){
                    for(int j = 0;j < g[i].length;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
            }
        }
        else if(g.length < breite){
            for(int i = 0;i < g.length;i++){
                if(g[i].length > hoehe){
                    for(int j = 0;j < hoehe;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
                else if(g[i].length < hoehe){
                    for(int j = 0;j < g[i].length;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
            }
        }
    }

    public void groesse(int breite,int hoehe){
        Gitter[][] g = gitter;
        f.dispose();
        new Frame(breite,hoehe,Frame.gitterZahl);
        if(g.length > breite){
            for(int i = 0;i< breite;i++){
                if(g[i].length > hoehe){
                    for(int j = 0;j < hoehe;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
                else if(g[i].length < hoehe){
                    for(int j = 0;j < g[i].length;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
            }
        }
        else if(g.length < breite){
            for(int i = 0;i < g.length;i++){
                if(g[i].length > hoehe){
                    for(int j = 0;j < hoehe;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
                else if(g[i].length < hoehe){
                    for(int j = 0;j < g[i].length;j++){
                        gitter[i][j] = g[i][j];
                    }
                }
            }
        }
    }

    public void speichern(String verzeichnis){
        for(int i= 0; i < gespeichert.length;i++){
            for(int j = 0; j< gespeichert[i].length;j++){
                gespeichert[i][j] = false;
            }
        }
        try {   
            FileOutputStream output = new FileOutputStream(verzeichnis+".lvl");
            DataOutputStream  datop  = new DataOutputStream(output);
            int anzahl=0;

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0;j < gitter[i].length;j++){
                    if(gitter[i][j].getCode()!='l'){
                        anzahl++;
                    }
                }
            }

            datop.writeInt(gitter.length);
            datop.writeInt(gitter[0].length);
            datop.writeInt(anzahl);

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0;j < gitter[i].length;j++){

                    if(gitter[i][j].getCode() == 's' && gitter[i][j].getZahl() == 4){
                        if(!gespeichert[i][j]){
                            gespeichert[i][j] = true;
                            datop.writeChar(gitter[i][j].getCode());
                            datop.writeInt(gitter[i][j].getZahl());
                            datop.writeInt(i*64);
                            datop.writeInt(j*64);

                            int x = gitter[i][j].getExtras(0);
                            int y = gitter[i][j].getExtras(1);

                            gespeichert[x][y] = true;
                            datop.writeChar(gitter[x][y].getCode());
                            datop.writeInt(gitter[x][y].getZahl());
                            datop.writeInt(x*64);
                            datop.writeInt(y*64);

                            for(int w = 0; w < gitter[x][y].getExtras().length;w++){
                                datop.writeInt(gitter[x][y].getExtras(w));

                            }

                        }
                    }
                }
            }

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0;j < gitter[i].length;j++){
                    if(gitter[i][j].getCode() != 'l'){
                        if(!gespeichert[i][j]){
                            gespeichert[i][j] = true;
                            datop.writeChar(gitter[i][j].getCode());

                            datop.writeInt(gitter[i][j].getZahl());

                            datop.writeInt(i*64);

                            datop.writeInt(j*64);

                            for(int w = 0; w < gitter[i][j].getExtras().length;w++){
                                datop.writeInt(gitter[i][j].getExtras(w));

                            }

                        }
                    }
                }
            }
        } catch (IOException ev) {ev.printStackTrace();}    

    }

    public Gitter welchesZeichenGitter(int x,int y){
        return gitter[x/64][y/64];
    }

    public void zeichnen(String verz){
        try {
            FileInputStream iostream = new FileInputStream(verz);
            DataInputStream diostream = new DataInputStream(iostream);
            try {
                int hoehe = diostream.readInt();
                int breite= diostream.readInt();
               // groesse(breite,hoehe);
                int anzahl=diostream.readInt();
                for(int i=0;i<anzahl;i++){
                    char read = diostream.readChar();
                    int readZahl = diostream.readInt();
                    if(read=='b' && readZahl == 1){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                       
                        welchesZeichenGitter(x,y).setCode('b');
                        welchesZeichenGitter(x,y).setZahl(1);
                    }
                    else if(read == 's' && readZahl == 1){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('s');
                        welchesZeichenGitter(x,y).setZahl(1);
                    }
                    else if(read=='s' && readZahl == 2){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('s');
                        welchesZeichenGitter(x,y).setZahl(2);
                        welchesZeichenGitter(x,y).setExtras(0,diostream.readInt());
                        welchesZeichenGitter(x,y).setExtras(1,diostream.readInt());
                    }
                    else if(read == 'b' && readZahl == 2){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('b');
                        welchesZeichenGitter(x,y).setZahl(2);

                    }
                    else if(read == 'b' && readZahl == 3){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('b');
                        welchesZeichenGitter(x,y).setZahl(3);

                    }
                    else if(read == 'b' && readZahl == 4){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('b');
                        welchesZeichenGitter(x,y).setZahl(4);
                    }
                    else if(read == 'g' && readZahl == 1){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('g');
                        welchesZeichenGitter(x,y).setZahl(1);

                    }
                    else if(read == 'g' && readZahl == 2){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('g');
                        welchesZeichenGitter(x,y).setZahl(2);

                    }
                    else if(read == 'g' && readZahl == 3){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('g');
                        welchesZeichenGitter(x,y).setZahl(3);

                    }
                    else if(read == 'g' && readZahl == 4){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('g');
                        welchesZeichenGitter(x,y).setZahl(4);

                    }
                    else if(read == 's' && readZahl == 3){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('s');
                        welchesZeichenGitter(x,y).setZahl(3);
                        welchesZeichenGitter(x,y).setExtras(0,diostream.readInt());
                    }

                    else if(read == 's' && readZahl == 4){
                        int x = diostream.readInt();
                        int y = diostream.readInt();
                        welchesZeichenGitter(x,y).setCode('s');
                        welchesZeichenGitter(x,y).setZahl(4);
                        char zChar = diostream.readChar();
                        int zZahl = diostream.readInt();
                        int zX = diostream.readInt();
                        int zY = diostream.readInt();

                        welchesZeichenGitter(x,y).setExtras(0,zX);
                        welchesZeichenGitter(x,y).setExtras(1,zY);

                        welchesZeichenGitter(zX,zY).setCode(zChar);
                        welchesZeichenGitter(zX,zY).setZahl(zZahl);
                        if(zChar == 's' && (zZahl ==3 || zZahl == 2)){
                            welchesZeichenGitter(zX,zY).setExtras(0,diostream.readInt());
                        }
                    }

                }   

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}
        f.getZeichenPanel().repaint();
    }

}