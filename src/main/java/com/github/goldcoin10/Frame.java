package com.github.goldcoin10;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

import static com.github.goldcoin10.Main.*;
import static com.github.goldcoin10.filePaths.pauseIcon;
import static com.github.goldcoin10.filePaths.playIcon;

public class Frame {
    public static JPanel renderFrame() {
        JPanel buttons = new JPanel();
        buttons.add(backFiveButton());
        buttons.add(playPauseButton());
        buttons.add(jumpFiveButton());

        JPanel root = new JPanel(new BorderLayout());
        root.add(buttons, BorderLayout.CENTER);
        root.add(songPercentage(), BorderLayout.SOUTH);
        return root;
    }

    public static JButton backFiveButton(){
        JButton backFive = new JButton(filePaths.backFive);
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
            if (!audioPlaying && filepath != null) {
                playPauseButton.setIcon(pauseIcon);
                Audio.playAudio();
                audioPlaying = true;
            } else if(audioPlaying && filepath != null) {
                playPauseButton.setIcon(playIcon);
                Audio.pauseAudio();
                audioPlaying = false;
            }
            else {
                JFileChooser chooseFile = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Audio Files", "wav", "aac", "mp3");
                chooseFile.setFileFilter(filter);
                int returnVal = chooseFile.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if(Audio.mediaPlayer != null) {
                        Audio.disposeData();
                    }
                    Main.filepath = chooseFile.getSelectedFile();
                    Audio.filePath = Main.filepath.toURI().toString();
                    Audio.jfxinit = false;
                }
            }
        });
        return playPauseButton;
    }

    public static JButton jumpFiveButton(){
        JButton jumpFive = new JButton(filePaths.jumpFive);
        jumpFive.addActionListener(e -> {
            if (audioPlaying) {
                Audio.jumpFive();
            }
        });
        return jumpFive;
    }

    public static JProgressBar songPercentage() {
        JProgressBar songPercentage = new JProgressBar();

        Timer progressChanger = new Timer(500, e -> {
            double length = Audio.songLength();
            double current = Audio.currentTime();
            if (filepath == null || Audio.mediaPlayer == null) {
                songPercentage.setValue(0);
            } else if (Double.isNaN(length) || Double.isInfinite(length) || length <= 0) {
                songPercentage.setValue(0);
            }
            double percent = (current / length)*100;
            songPercentage.setValue((int) (percent));
            songPercentage.setString(Math.round(percent) + "%");
            songPercentage.setStringPainted(true);
        });
        progressChanger.start();

        return songPercentage;
    }
}
