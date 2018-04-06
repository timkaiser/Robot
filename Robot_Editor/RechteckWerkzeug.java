import java.awt.Rectangle;

public class RechteckWerkzeug extends Werkzeug{
    private int xAnfang,yAnfang;
    private boolean anfangVorhanden = false;;
    public void ausfuehren(Gitter[][] gitter,int mausX,int mausY){
        if(!anfangVorhanden){
            xAnfang = mausX;
            yAnfang = mausY;
           
            anfangVorhanden = true;
        }
        else{
            Rectangle mausRect = new Rectangle(xAnfang,yAnfang,(mausX)-xAnfang,(mausY)-yAnfang);
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
            anfangVorhanden=false;
        }
    }

}