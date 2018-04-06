public class Maus{
    private Werkzeug werk;
    private char code;
    private int zahl;
    int[] extras;
    public Maus(){
        werk = new StandardWerkzeug();
        code = 'l';
        zahl = 0;
        extras = new int[10];
    }

    public void setWerkzeug(Werkzeug w){
        werk = w;
    }
    
    public Werkzeug getWerkzeug(){
        return werk;
    }
    
    public char getCode(){
        return code;
    }
    
    public int getZahl(){
        return zahl;
    }
    
    public void setCode(char code){
        this.code = code;
    }
    
    public void setZahl(int zahl){
        this.zahl =  zahl;
    }
    
    public int getExtras(int index){
        return extras[index];
    }
    
    public int[] getExtras(){
        return extras;
    }
    
    public void setExtras(int[] extrasFeld){
        extras = extrasFeld;
    }
    
    public void setExtras(int index,int wert){
        extras[index] = wert;
    }

}