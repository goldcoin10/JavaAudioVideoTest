package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio {
    static boolean jfxinit = false;
    static MediaPlayer mediaPlayer;
    static String filePath = Main.filepath.toURI().toString();

    public static void playAudio(){
        if (!jfxinit) {
            Platform.startup(() -> {});
            Platform.runLater(() -> {
                Media media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
            });
            jfxinit = true;
        }

        Platform.runLater(() -> {
            mediaPlayer.play();
        });
    }
    public static void printFilePath(){
        System.out.println(filePath); // Only call this for debugging
    }

    public static void pauseAudio() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) mediaPlayer.pause();
        });
    }

    public static void disposeData() {
        mediaPlayer.stop();
        mediaPlayer.dispose();
    }

    public static double currentTime() {
        if(mediaPlayer.getCurrentTime().toSeconds() == 0) return 0;
        else return mediaPlayer.getCurrentTime().toSeconds();
    }

    public static double songLength() {
        return mediaPlayer.getCycleDuration().toSeconds();
    }

    public static void jumpFive() {
        mediaPlayer.seek(Duration.seconds(currentTime() + 5));
    }

    public static void backFive() {
        mediaPlayer.seek(Duration.seconds(currentTime() - 5));
    }
}