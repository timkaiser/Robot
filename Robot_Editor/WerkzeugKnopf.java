import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class WerkzeugKnopf extends JButton implements ActionListener{
    private Werkzeug werkzeug;
    public WerkzeugKnopf(String imageAdresse,Werkzeug werkzeug){
        setPreferredSize(new Dimension(30,30));
        setIconImage(imageAdresse);
        this.werkzeug = werkzeug;
        addActionListener(this);
        setVisible(true);
     
    }

    public WerkzeugKnopf(){
        setSize(50,50);
        addActionListener(this);
        setVisible(true);
    }

    public void setIconImage(String adresse){
        setIcon(new ImageIcon(adresse));
    }
    
    public void actionPerformed(ActionEvent e){
       Frame.maus.setWerkzeug(werkzeug);
    }

}