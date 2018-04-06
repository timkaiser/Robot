import javax.swing.*;
import java.awt.*;
import java.util.*;
public class KnopfPanel extends JPanel{
    private java.util.List<Knopf> knoepfe;
    private String name;
    public KnopfPanel(int x,int y,int width,int height,String name){
        setBounds(x,y,width,height);
        setLayout(new FlowLayout());
        this.name = name;
        setVisible(false);
        knoepfe = new ArrayList<Knopf>();
    }

    public String getName(){
        return name;
    }

    public void addKnopf(Knopf k){
        knoepfe.add(k);
        add(k);
    }
    
    public Knopf getKnopf(int index){
        if(index >= 0 && index < knoepfe.size()){
            return knoepfe.get(index);
        }
        else{
            return null;
        }
    }
    
    public int getGroese(){
        return knoepfe.size();
    }
}