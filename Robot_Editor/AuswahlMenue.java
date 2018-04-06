import javax.swing.*;
import java.awt.event.*;
public class AuswahlMenue extends JComboBox implements ActionListener{
    private String[] namen;
    private AuswahlPanel panel;
    private String focus;
    public AuswahlMenue(String[] namen,AuswahlPanel panel){
        this.namen = namen;
        for(int i = 0; i < namen.length;i++){
            addItem(namen[i]);
        }
        setVisible(true);
        setBounds(0,0,200,25);
        addActionListener(this);
        this.panel = panel;
        if(namen.length>0){
            focus = namen[0];
        }
        else{
            focus = null;
        }
    }

    public void actionPerformed(ActionEvent e){
        JComboBox cb = (JComboBox)e.getSource();
        focus = (String)cb.getSelectedItem();
        panel.updatePanel();
    }
    
    public String getFocus(){
        return focus;
    }
}