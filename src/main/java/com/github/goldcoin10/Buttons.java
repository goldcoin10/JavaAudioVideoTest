package com.github.goldcoin10;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import static com.github.goldcoin10.Main.*;
import static com.github.goldcoin10.filePaths.*;

public class Buttons {
    public static JPanel renderButtons() {
        JPanel buttons = new JPanel();
        buttons.add(backFiveButton());
        buttons.add(playPauseButton());
        buttons.add(jumpFiveButton());
        return buttons;
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
                JOptionPane.showMessageDialog(null, "No file is open, please load one");
                JFileChooser chooseFile = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Files", "wav");
                chooseFile.setFileFilter(filter);
                int returnVal = chooseFile.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if(filepath != null) {
                        Audio.disposeData();
                    }
                    filepath = chooseFile.getSelectedFile();
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
}
