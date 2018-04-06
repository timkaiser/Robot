import java.awt.Rectangle;

abstract class Gegner extends Einheit{
    public void damage(){
    }

    public void spielerKollision(){
        Rectangle rect = this.getRect();
        Rectangle r = Start.spielfeld.getSpieler().getRect();
        if(rect.intersects(r)){
            if(Start.spielfeld.getSpieler().getYGeschwindigkeit()>0){
                Start.spielfeld.getSpieler().damage();
            }else{
            Start.spielfeld.getSpieler().sprung();
            setX(10000);
            }
        }
    }
}
