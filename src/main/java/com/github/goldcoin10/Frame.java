package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Objects;

import static com.github.goldcoin10.Main.*;
import static com.github.goldcoin10.filePaths.pauseIcon;
import static com.github.goldcoin10.filePaths.playIcon;

public class Frame {
    public static String nowPlaying;
    public static JPanel renderFrame() {
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(backFiveButton());
        buttons.add(playPauseButton());
        buttons.add(jumpFiveButton());

        JPanel center = new JPanel(new GridBagLayout());
        center.add(buttons);

        JPanel bottomCenter = new JPanel(new GridBagLayout());
        bottomCenter.add(songPercentageBar());

        JPanel bottomNorth = new JPanel(new GridBagLayout());
        bottomNorth.add(nowPlayingLabel());

        JPanel root = new JPanel(new BorderLayout());
        root.add(center, BorderLayout.CENTER);
        root.add(bottomCenter, BorderLayout.SOUTH);
        root.add(volumeBar(), BorderLayout.EAST);
        root.add(bottomNorth, BorderLayout.NORTH);
        return root;
    }

    public static JButton backFiveButton(){
        JButton backFive = new JButton(filePaths.backFive);
        backFive.setPreferredSize(new Dimension(48, 48));
        backFive.addActionListener(e -> {
            if (audioPlaying) {
                Audio.backFive();
            }
        });
        return backFive;
    }

    public static JButton playPauseButton() {
        JButton playPauseButton = new JButton(playIcon);
        playPauseButton.setPreferredSize(new Dimension(48, 48));
        playPauseButton.addActionListener(e -> {
            if (!audioPlaying && Audio.mediaPlayer != null) {
                playPauseButton.setIcon(pauseIcon);
                Audio.playAudio();
                audioPlaying = true;
            } else if(audioPlaying && Audio.mediaPlayer != null) {
                playPauseButton.setIcon(playIcon);
                Audio.pauseAudio();
                audioPlaying = false;
            }
            else {
                JFileChooser chooseFile = new JFileChooser();
                chooseFile.setFileFilter(filter);
                int returnVal = chooseFile.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if(Audio.mediaPlayer != null) {
                        Audio.disposeData();
                    }
                    Platform.runLater(() -> {
                        Main.filepath = chooseFile.getSelectedFile();
                        Audio.filePath = Main.filepath.toURI().toString();
                        Audio.media = new Media(Audio.filePath);
                        Audio.mediaPlayer = new MediaPlayer(Audio.media);
                    });
                }
            }
        });
        return playPauseButton;
    }

    public static JButton jumpFiveButton(){
        JButton jumpFive = new JButton(filePaths.jumpFive);
        jumpFive.setPreferredSize(new Dimension(48, 48));
        jumpFive.addActionListener(e -> {
            if (audioPlaying) {
                Audio.jumpFive();
            }
        });
        return jumpFive;
    }

    public static JProgressBar songPercentageBar() {
        JProgressBar songPercentageBar = new JProgressBar();

        Timer progressChanger = new Timer(500, e -> {
            double length = Audio.songLength();
            double current = Audio.currentTime();
            if (filepath == null || Audio.mediaPlayer == null) {
                songPercentageBar.setValue(0);
            } else if (Double.isNaN(length) || Double.isInfinite(length) || length <= 0) {
                songPercentageBar.setValue(0);
            }
            double percent = (current / length)*100;
            songPercentageBar.setValue((int) (percent));
            songPercentageBar.setString("Progress: " + Math.round(percent) + "%");
            songPercentageBar.setStringPainted(true);

        });
        progressChanger.start();

        return songPercentageBar;
    }

    public static JSlider volumeBar() {
        JSlider volumeBar = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        volumeBar.setMajorTickSpacing(0);
        volumeBar.setMinorTickSpacing(25);
        volumeBar.setMinorTickSpacing(50);
        volumeBar.setMinorTickSpacing(75);
        volumeBar.setMajorTickSpacing(100);
        volumeBar.setPaintTicks(true);
        volumeBar.setPaintLabels(true);
        volumeBar.addChangeListener(e -> {
            if (Audio.mediaPlayer != null) {
                Audio.mediaPlayer.setVolume((double) volumeBar.getValue() / 100);
            }
        });


        return volumeBar;
    }

    public static JLabel nowPlayingLabel() {
        JLabel nowPlayingLabel = new JLabel("nothing playing");
        Timer checkWhatsPlaying = new Timer(500, e -> {
            if(Audio.filePath != null) {
                nowPlayingLabel.setText(Main.filepath.getName());
            } else {
                nowPlayingLabel.setText("nothing playing");
            }
        });
        checkWhatsPlaying.start();
        return nowPlayingLabel;
    }
}
