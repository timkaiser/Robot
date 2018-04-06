import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Spielstart{  
    public Spielstart(String spielstand){
        new Bilder("matrix");

        Start.fenster.remove(Start.menue);        
        Start.fenster.add(Start.pausenmenue = new Pausenmenue());
        Start.pausenmenue.setVisible(false);

        Start.spielschleife=new Spielschleife();
        Level.spielstand=spielstand;

        try {
            FileInputStream iostream = new FileInputStream(spielstand+".save");
            DataInputStream  diostream = new DataInputStream(iostream);
            try {
                for(int i=diostream.available(); i>0; i--)
                    Level.levelFortschrittEinlesen(diostream.readBoolean()); 

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            try {   
                FileOutputStream output = new FileOutputStream(spielstand+".save");
                DataOutputStream  datop  = new DataOutputStream(output);

                try {
                    datop.writeBoolean(true);
                     Level.levelFortschrittEinlesen(true);
                    for(int i=0; i<20; i++){
                        datop.writeBoolean(false);
                         Level.levelFortschrittEinlesen(false);
                    }
                } catch (IOException ex) {ex.printStackTrace();}
                Level.levelFortschrittEinlesen(true);
                try {
                    output.close();
                } catch (IOException e1) {}
            } catch (IOException ioe) {ioe.printStackTrace();}
        }


        Start.fenster.add(Start.spielfeld=new Spielfeld());
        Start.fenster.repaint();
    }   

}
