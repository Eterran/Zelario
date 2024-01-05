package UIpack;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SoundPlayer {
    public void playSound(String soundFileName) {
        try {
            FileInputStream fis = new FileInputStream(soundFileName);
            Player playMP3 = new Player(fis);
            playMP3.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    private int pausedOnFrame = 0;
    public void pauseSound(String soundFileName) {
        try {
            FileInputStream fis = new FileInputStream(soundFileName);
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    public void resumeSound(String soundFileName) {
        try {
            FileInputStream fis = new FileInputStream(soundFileName);
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    AdvancedPlayer player = new AdvancedPlayer(fis);
    player.setPlayBackListener(new PlaybackListener() {
        @Override
        public void playbackFinished(PlaybackEvent event) {
            pausedOnFrame = event.getFrame();
        }
    });
    player.play();
}
