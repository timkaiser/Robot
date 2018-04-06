import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
public class Zeichenpanel extends JPanel implements Scrollable, MouseListener,MouseMotionListener{

    private int x,y,width,height;
    private Gitter[][] gitter;
    private boolean links,rechts;
    public Zeichenpanel(int x,int y,Gitter[][] g){
        this.x = x;
        this.y = y;
        this.width = Frame.gitterZahl*g.length;
        this.height = Frame.gitterZahl*g[0].length;

        setLayout(null);
        setBounds(x,y,width,height);
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.BLACK);
        repaint();
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        gitter = g;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        for(int i = 0; i < gitter.length;i++){
            for(int j = 0; j < gitter[i].length;j++) {

                g.drawRect(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight());
            }
        }

        for(int i = 0; i < gitter.length;i++){
            for(int j = 0; j < gitter[i].length;j++) {
                gitter[i][j].zeichne(g,this);

            }
        }

        for(int i = 0; i < gitter.length;i++){
            for(int j = 0; j < gitter[i].length;j++) {
                if(gitter[i][j].getCode() == 's' && gitter[i][j].getZahl() == 4){
                    int x = gitter[i][j].getExtras(0);
                    int y = gitter[i][j].getExtras(1);
                    g.setColor(Color.RED);
                    g.drawLine(gitter[i][j].getX()+Frame.gitterZahl/2,gitter[i][j].getY()+Frame.gitterZahl/2,gitter[x][y].getX()+Frame.gitterZahl/2,gitter[x][y].getY()+Frame.gitterZahl/2);
                }
            }
        }

        g.setColor(Color.WHITE);
        for(int i = 0; i < gitter.length;i++){
            for(int j = 0; j < gitter[i].length;j++) {
                g.drawString("" + i +"/" + j , gitter[i][j].getX()+Frame.gitterZahl/2,gitter[i][j].getY()+Frame.gitterZahl/2);
            }
        }

    }

    public boolean getScrollableTracksViewportHeight(){
        return false;
    }

    public boolean getScrollableTracksViewportWidth(){
        return false;
    }

    public int getScrollableBlockIncrement(Rectangle r,int a,int b){
        return Frame.gitterZahl/2;
    }

    public int getScrollableUnitIncrement(Rectangle r,int a,int b){
        return Frame.gitterZahl/2;
    }

    public Dimension getPreferredScrollableViewportSize(){
        return new Dimension(800,600);
    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            links = true;
            if(Frame.maus.getWerkzeug() instanceof RechteckWerkzeug){
                Frame.maus.getWerkzeug().ausfuehren(gitter,e.getX(),e.getY());
            }
        }
        if(e.getButton() == 3){
            rechts = true;
        }
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == 1){
            links = false;
            if(Frame.maus.getWerkzeug() instanceof RechteckWerkzeug){
                Frame.maus.getWerkzeug().ausfuehren(gitter,e.getX(),e.getY());
            }
        }
        if(e.getButton() == 3){
            rechts = false;
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        /*

        Rectangle mausRect = new Rectangle(e.getX(),e.getY(),1,1);
        Rectangle felder;

        for(int i = 0;i < gitter.length;i++){
        for(int j  = 0; j < gitter[0].length;j++){
        felder = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),Frame.gitterZahl,Frame.gitterZahl);

        if(mausRect.intersects(felder)){
        if(gitter[i][j].getCode() == 'l'){
        if(links){
        gitter[i][j].setCode(Frame.maus.getCode());
        gitter[i][j].setZahl(Frame.maus.getZahl());
        }
        if(rechts){
        gitter[i][j].setCode('l');
        gitter[i][j].setZahl(0);
        }
        }
        else{

        }
        }
        }
        }

        repaint();
         */
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        if(links){
            /*
            Rectangle mausRect = new Rectangle(e.getX(),e.getY(),1,1);
            Rectangle felder;

            for(int i = 0;i < gitter.length;i++){
            for(int j  = 0; j < gitter[0].length;j++){
            felder = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),Frame.gitterZahl,Frame.gitterZahl);

            if(mausRect.intersects(felder)){
            if(Frame.maus.getCode() == 's' && Frame.maus.getZahl() == 1){
            for(int w = 0;w < gitter.length;w++){
            for(int x  = 0; x < gitter[0].length;x++){

            if(gitter[w][x].getCode() == 's'&& gitter[w][x].getZahl() == 1){
            gitter[w][x].setCode('l');
            gitter[w][x].setZahl(0);
            }

            }
            }
            }
            gitter[i][j].setCode(Frame.maus.getCode());
            gitter[i][j].setZahl(Frame.maus.getZahl());
            gitter[i][j].setExtras(Frame.maus.getExtras());
            }
            }
            }
             */
            if(!(Frame.maus.getWerkzeug() instanceof RechteckWerkzeug)){
                Frame.maus.getWerkzeug().ausfuehren(gitter,e.getX(),e.getY());
            }
        }
        if(rechts){
            Rectangle mausRect = new Rectangle(e.getX(),e.getY(),1,1);
            Rectangle felder;

            for(int i = 0;i < gitter.length;i++){
                for(int j  = 0; j < gitter[0].length;j++){
                    felder = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),Frame.gitterZahl,Frame.gitterZahl);

                    if(mausRect.intersects(felder)){

                        gitter[i][j].setCode('l');
                        gitter[i][j].setZahl(0);
                    }
                }
            }

        }
        repaint();
    }

}