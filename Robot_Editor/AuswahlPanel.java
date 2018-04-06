import javax.swing.*;
import java.awt.*;

public class AuswahlPanel extends JPanel{
    private AuswahlMenue auswahlMenue;
    private KnopfPanel[] panel;
    private String[] namen = {"BLOECKE","GEGNER","EXTRAS"};
    public AuswahlPanel(Extras extras){
        setLayout(null);
        setBackground(Color.BLACK);
        setSize(200,400);
        
        auswahlMenue = new AuswahlMenue(namen,this);  
        auswahlMenue.setVisible(true);
        add(auswahlMenue);

        panel = new KnopfPanel[namen.length];

        for(int i = 0; i < panel.length;i++){
            panel[i] = new KnopfPanel(0,25,getWidth(),getHeight(),namen[i]);
            add(panel[i]);
        }

        panel[0].setBackground(Color.RED);
        panel[1].setBackground(Color.BLACK);
        panel[2].setBackground(Color.BLUE);
        panel[0].addKnopf(new Knopf('b',1,"metalboden.png",Bilder.getMetallBlock(),0,extras));
        panel[0].addKnopf(new Knopf('b',2,"Einweg.png",Bilder.getEinwegBlock(),0,extras));
        panel[0].addKnopf(new Knopf('b',4,"sprung.png",Bilder.getSprungBlock(),0,extras));
        panel[0].addKnopf(new Knopf('b',5,"glas.png",Bilder.getGlas(),0,extras));
        panel[1].addKnopf(new Knopf('g',1,"testsubjekt0000.png",Bilder.getGegner(1),0,extras));
        panel[1].addKnopf(new Knopf('g',2,"Gegner_Fliegend.png",Bilder.getGegner(2),0,extras));
        panel[1].addKnopf(new Knopf('g',3,"gegner_Springend.png",Bilder.getGegner(3),0,extras));
        panel[1].addKnopf(new Knopf('g',4,"gegner_nichtInAbgruendeFallen.png",Bilder.getGegner(4),0,extras));
        panel[2].addKnopf(new Knopf('s',4,"Trigger.png",Bilder.getTrigger(),2,extras));
        panel[2].addKnopf(new Knopf('s',2,"LevelStart.png",Bilder.getLevelBlock('s'),2,extras));
        panel[2].addKnopf(new Knopf('s',3,"LevelEnde.png",Bilder.getLevelBlock('e'),1 ,extras));
        panel[2].addKnopf(new Knopf('s',1,"Start.png",Bilder.getStartBlock(),0,extras));
        
       
        
        updatePanel();
        setVisible(true);
    }

    public KnopfPanel getPanel(int index){
        return panel[index];
    }
    
    public int getPanelSize(){
        return panel.length;
    }
    
    public void updatePanel(){
        String a = auswahlMenue.getFocus();
        show(a);
    }

    public void show(String name){
        for(int i = 0; i < panel.length;i++){
            if(panel[i].getName() == name){
                panel[i].setVisible(true);
            }
            else{panel[i].setVisible(false);}
        }
    }
}