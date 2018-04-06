import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound{
    AudioClip sound;
    public Sound(String pfad){
        URL url = Sound.class.getResource(pfad);
        sound= Applet.newAudioClip(url);
    }

    public void start(){
        sound.play();
    }
    
    public void stop(){
        sound.stop();
    }
}