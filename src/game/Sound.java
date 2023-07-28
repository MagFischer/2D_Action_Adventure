package game;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/Pimpnes.wav");
        soundURL[1] = getClass().getResource("/sound/unlock.wav");
        soundURL[2] = getClass().getResource("/sound/levelup.wav");
        soundURL[3] = getClass().getResource("/sound/fanfare.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
    
}
