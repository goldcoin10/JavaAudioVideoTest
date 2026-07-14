package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {
    static boolean jfxinit = false;
    static MediaPlayer mediaPlayer;
    static String filePath = Main.filepath.toURI().toString();

    public static void playAudio(){
        if (!jfxinit) {
            Platform.startup(() -> {});
            jfxinit = true;
        }

        Platform.runLater(() -> {
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            mediaPlayer.dispose();
        });
    }
    public void printFilePath(){
        System.out.println(filePath); // Only call this for debugging
    }

    public static void pauseAudio() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) mediaPlayer.pause();
        });
    }
}