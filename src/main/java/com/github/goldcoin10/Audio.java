package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;

public class Audio {
    static MediaPlayer mediaPlayer;
    static String filePath;
    static Media media;

    public static void playAudio(){
        Platform.runLater(() -> {
            Main.frame.setTitle("MediaPlayer (" + Main.filepath.getName() + ")");
            Frame.nowPlaying = Main.filepath.getName();
            mediaPlayer.play();
        });
    }
    public static void printFilePath(){
        System.out.println(filePath);
    }

    public static void pauseAudio() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                Main.frame.setTitle("MediaPlayer");
                Frame.nowPlaying = "nothing playing";
            }
        });
    }

    public static void disposeData() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    public static double currentTime() {
        if(mediaPlayer == null) return 0;
        else if(mediaPlayer.getCurrentTime().toSeconds() == 0) return 0;
        else return mediaPlayer.getCurrentTime().toSeconds();
    }

    public static double songLength() {
        if(mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCycleDuration().toSeconds();
    }

    public static void jumpFive() {
        if(mediaPlayer != null) {
            mediaPlayer.seek(Duration.seconds(currentTime() + 5));
        }
    }

    public static void backFive() {
        if(mediaPlayer != null) {
            mediaPlayer.seek(Duration.seconds(currentTime() - 5));
        }
    }
}