import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class Knopf extends JButton{
    Rectangle rect;
    boolean markiert=false;

    public Knopf(int x,int y,int breite, int hoehe,  String text, ActionListener acLi){    {
            rect=new Rectangle(x,y,breite,hoehe);
            setText(text);
            setFont(Start.getFont(24));
            setBounds(rect);
            setForeground(new Color(100,100,100));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setHorizontalAlignment(LEFT);
            setFocusable(false);
            addActionListener(acLi);

        }

    }

    public void setFocus(){
        setForeground(new Color(100,0,0));
        repaint();

    }

    public void removeFocus(){
        setForeground(new Color(100,100,100));
    }

    public void markieren(){
        setForeground(new Color(150,50,0));
        markiert=true;
    }

    public void entmarkieren(){
        setForeground(new Color(100,100,100));
        markiert=false;
    }

    public boolean markiert(){
        return markiert;
    }
}
