import java.awt.*;

public class Gitter{
    private int x,y,width,height;
    private char code;
    private int zahl;
    int[] extras;
    private boolean markiert;
    public Gitter(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        code = 'l';
        zahl = 0;
        extras = new int[10];
        markiert = false;
    }

    public Gitter(int x,int y,int width,int height,char code,int zahl,int[] extras){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.code = code;
        this.zahl = zahl;
        this.extras = extras;
    }

    public void zeichne(Graphics g,Zeichenpanel feld){
        Knopf vergleich = null;

        for(int i = 0; i < Frame.auswahlPanel.getPanelSize();i++){
            for(int j = 0; j < Frame.auswahlPanel.getPanel(i).getGroese();j++){
                vergleich = Frame.auswahlPanel.getPanel(i).getKnopf(j);

                if(vergleich != null && code == vergleich.getCode() && zahl== vergleich.getZahl()){
                    g.drawImage(vergleich.getImage(),x,y,width,height,feld);
                    for(int w = 0; w < extras.length;w++){

                        g.drawString(""+extras[w],x,y+5+w*10);
                    }
                }

            }
        }
    }

    public void male(Gitter g){
        setCode(g.getCode());
        setZahl(g.getZahl());
        setExtras(g.getExtras());
    }
    
    public void markiere(){
        markiert = true;
    }

    public void entmarkiere(){
        markiert = false;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public char getCode(){
        return code;
    }

    public void setCode(char code){
        this.code = code;
    }

    public int getZahl(){
        return zahl;
    }

    public void setZahl(int zahl){
        this.zahl = zahl;
    }

    public int[] getExtras(){
        return extras;
    }

    public void setExtras(int i,int a){
        extras[i] = a;
    }

    public int getExtras(int i){
        return extras[i];
    }

    public void setExtras(int[] i){
        extras = i;
    }

    public void showExtras(){

    }

}