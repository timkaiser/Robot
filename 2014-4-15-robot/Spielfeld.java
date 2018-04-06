import javax.swing.JPanel;
import java.awt.*;

public class Spielfeld extends JPanel  {
    static Level level;
    private Interface inter;
    public Spielfeld(){
        setSize(800,600);
        setBackground(Color.BLACK/*new Color(240,190,140)*/);
        level=new Level(0,3);
        setLayout(null);
        setFocusable(true);
        addKeyListener(new Input());
        inter = new Interface();
        repaint();
    }

    public void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        
        setSize(Start.fenster.getSize());
        
        level.zeichnen(g,this);                
        
        inter.zeichnen(g,this);
        
        //hitboxen(g);
        requestFocus();
    }
    
    public Spieler getSpieler(){
        return level.getSpieler();
    }

    protected Graphics hitboxen(Graphics g){
        g.setColor(Color.YELLOW);
        for(int i=0; i<level.getAnzahlEinheiten();i++){
            //  g.drawRect(level.getEinheit(i).getX(),level.getEinheit(i).getY(),level.getEinheit(i).getWidth(),level.getEinheit(i).getHeight());

            if(level.getEinheit(i).getRichtung() == "L"){
                g.drawRect((int)level.getEinheit(i).getX()-5-level.spieler.getXVerschiebung(),(int)(level.getEinheit(i).getY()+level.getEinheit(i).getHeight())-level.spieler.getYVerschiebung(),(int)level.getEinheit(i).getWidth(),1);
                g.drawRect((int)level.getEinheit(i).getX()-5-level.spieler.getXVerschiebung(),(int)level.getEinheit(i).getY()-level.spieler.getYVerschiebung(),1,(int)level.getEinheit(i).getHeight());
                g.drawRect((int)(level.getEinheit(i).getX()+level.getEinheit(i).getWidth()-5)-level.spieler.getXVerschiebung(),(int)level.getEinheit(i).getY()-level.spieler.getYVerschiebung(),1,(int)level.getEinheit(i).getHeight());
            }
            else if(level.getEinheit(i).getRichtung() == "R"){
                g.drawRect((int)level.getEinheit(i).getX()+5-level.spieler.getXVerschiebung(),(int)(level.getEinheit(i).getY()+level.getEinheit(i).getHeight())-level.spieler.getYVerschiebung(),(int)level.getEinheit(i).getWidth(),1);
                g.drawRect((int)level.getEinheit(i).getX()+5-level.spieler.getXVerschiebung(),(int)level.getEinheit(i).getY()-level.spieler.getYVerschiebung(),1,(int)level.getEinheit(i).getHeight());
                g.drawRect((int)(level.getEinheit(i).getX()+level.getEinheit(i).getWidth()+5)-level.spieler.getXVerschiebung(),(int)level.getEinheit(i).getY()-level.spieler.getYVerschiebung(),1,(int)level.getEinheit(i).getHeight());

            }

        }
        for(int i=0; i<level.getAnzahlBloecke();i++){
            if(level.getBlock(i) instanceof Trigger)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.RED);
            g.drawRect((int)level.getBlock(i).getRect().getX()-level.spieler.getXVerschiebung(),(int)level.getBlock(i).getRect().getY()-level.spieler.getYVerschiebung(),(int)level.getBlock(i).getRect().getWidth(),(int)level.getBlock(i).getRect().getHeight());
        }
        return g;
    }
}
