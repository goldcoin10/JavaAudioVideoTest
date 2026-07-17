package com.github.goldcoin10.JavaMediaPlayer.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Menu {
    public static JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openItem());
        fileMenu.add(versionDialog());
        fileMenu.add(quit());
        return fileMenu;
    }

    public static JMenuItem openItem() {
        JMenuItem openItem = new JMenuItem("Open Audio");
        openItem.addActionListener(e -> {
            JFileChooser chooseFile = new JFileChooser();
            chooseFile.setFileFilter(Main.filter);
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

        });
        return openItem;
    }

    public static JMenuItem versionDialog() {
        JMenuItem versionDialog = new JMenuItem("Version");
        versionDialog.addActionListener(e -> {
            JOptionPane.showMessageDialog(versionDialog, "Version: " + Main.currentVersion() + "\n\n" + """
                    MIT Licence
                    
                    Copyright 2026 goldcoin10
                    
                    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”),
                    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
                    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
                    
                    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
                    
                    THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
                    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
                    DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
                    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
                    """);
        });
        return versionDialog;
    }

    public static JMenuItem quit() {
        JMenuItem quit = new JMenuItem("Exit");
        quit.addActionListener(e -> {Main.frame.dispatchEvent(new WindowEvent(Main.frame, WindowEvent.WINDOW_CLOSING));});
        return quit;
    }

    public static JMenuBar renderMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        return menuBar;
    }
}
