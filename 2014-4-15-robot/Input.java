import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Input implements KeyListener{
    private static boolean[] keys = new boolean[1024];

    public static boolean isKeyDown(int keyCode){
        if(keyCode >= 0 &&keyCode<keys.length){
            return keys[keyCode];

        }
        else{
            return false;
        }
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(!Start.pausenmenue.isVisible()){
            if(keyCode>= 0 &&keyCode<keys.length){
                keys[keyCode] = true;
            }
        }else{
            Start.pausenmenue.input(keyCode);
        }
    }   

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode>= 0 &&keyCode<keys.length){
            keys[keyCode] = false;
        }
    }  

    public void keyTyped(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode>= 0 &&keyCode<keys.length){
            keys[keyCode] = true;
        }
    }
}