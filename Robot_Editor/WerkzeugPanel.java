import javax.swing.*;
import java.awt.*;

public class WerkzeugPanel extends JPanel{
    private WerkzeugKnopf[] knoepfe;
    public WerkzeugPanel(int x,int y,int width,int height){
        setBounds(x,y,width,height);
        setBackground(Color.YELLOW);
        
        knoepfe = new WerkzeugKnopf[3];
        knoepfe[0] = new WerkzeugKnopf("Punkt.png",new StandardWerkzeug());
        add(knoepfe[0]);
        knoepfe[1] = new WerkzeugKnopf("Farbeimer.png",new FuellWerkzeug());
        add(knoepfe[1]);
        knoepfe[2] = new WerkzeugKnopf("RectTool.png",new RechteckWerkzeug());
        add(knoepfe[2]);
        
        setVisible(true);
    }



}