import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Extras extends JScrollPane{

    private JTextField[] label;
    private JPanel panel;
    private JButton speichern;
    public Extras(int x,int y,int width,int height){
        setBounds(x,y,width,height);
        setVisible(true);

        panel = new JPanel();
        panel.setBounds(x,y,width,height);
        panel.setBackground(Color.GREEN);
        panel.setPreferredSize(new Dimension(width,height));
        panel.setVisible(true);
        setViewportView(panel);

        label = new JTextField[0];

        int[] extras = Frame.maus.getExtras();

        speichern = new JButton("SPEICHERN");
        speichern.setPreferredSize(new Dimension(200,50));
        speichern.setVisible(true);
        speichern.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    speichern();
                }
            });
        panel.add(speichern);
    }

    public void speichern(){
        int[] tmp = new int[label.length];
        for(int i = 0; i < tmp.length;i++){
            int t = Integer.parseInt(label[i].getText());
            tmp[i] = t;
        }
        Frame.maus.setExtras(tmp);
    }

    public void update(Knopf k){
        for(int i = 0; i < label.length;i++){
            panel.remove(label[i]);
        }
        revalidate();
        repaint();
        label = new JTextField[k.getExtras().length];
        if(label.length > 10 ){
            panel.setPreferredSize(new Dimension(getWidth(),31*label.length));
        }
        else{
            panel.setPreferredSize(new Dimension(getWidth(),500));
        }
       
        for(int i = 0; i < label.length;i++){
            label[i] = new JTextField();
            label[i].setPreferredSize(new Dimension(100,25));
            label[i].setText(""+k.getExtras(i));
            panel.add(label[i]);

        }
        setViewportView(panel);
        revalidate();
        repaint();
    }

}