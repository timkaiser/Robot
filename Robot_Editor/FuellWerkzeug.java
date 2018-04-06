import java.awt.*;
public class FuellWerkzeug extends Werkzeug{

    public void ausfuehren(Gitter gitter[][],int mausX,int mausY){
        Rectangle maus = new Rectangle(mausX,mausY,1,1);
        Rectangle feld;
        char code;
        int zahl;
        for(int i = 0;i < gitter.length;i++){
            for(int j  = 0; j < gitter[0].length;j++){
                feld = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight());
                if(maus.intersects(feld)){
                    code = gitter[i][j].getCode();
                    zahl = gitter[i][j].getZahl();
                    fuellen(gitter,gitter[i][j],code,zahl,new Gitter(0,0,0,0,Frame.maus.getCode(),Frame.maus.getZahl(),Frame.maus.getExtras()));
                }
            }
        }
    }

    public void fuellen(Gitter[][] g,Gitter vorher,char code,int zahl,Gitter nachher){
        int x = vorher.getX()/Frame.gitterZahl;
        int y = vorher.getY()/Frame.gitterZahl;
        vorher.male(nachher);
        
        if(x+1 < g.length){
            if(g[x+1][y].getCode() == code && g[x+1][y].getZahl() == zahl){
                fuellen(g,g[x+1][y],code,zahl,nachher);
            }
        }
        if(y+1 < g[0].length){
            if(g[x][y+1].getCode() == code && g[x][y+1].getZahl() == zahl){
                fuellen(g,g[x][y+1],code,zahl,nachher);
            }
        }
        if(x-1 >=0){
            if(g[x-1][y].getCode() == code && g[x-1][y].getZahl() == zahl){
                fuellen(g,g[x-1][y],code,zahl,nachher);
            }
        }
        if(y-1 >=0){
            if(g[x][y-1].getCode() == code && g[x][y-1].getZahl() == zahl){
                fuellen(g,g[x][y-1],code,zahl,nachher);
            }
        }
        
        
    }

}