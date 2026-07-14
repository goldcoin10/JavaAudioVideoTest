package com.github.goldcoin10;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class Main implements Runnable {
    private JFrame frame;
    public static File filepath;

    public void run() {
        renderGUI();
    }

    private void renderGUI() {
        JFrame frame = new JFrame("MediaPlayer");
        frame.setSize(320, 240);
        frame.setJMenuBar(renderMenuBar());
        frame.setVisible(true);
    }
    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        openItem.addActionListener(e -> {
            JFileChooser chooseFile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "WAV Files", "wav");
            int returnVal = chooseFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                filepath = chooseFile.getSelectedFile();
                Audio.playAudio();
            }

        });
        return fileMenu;
    }

    private JMenuBar renderMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        return menuBar;
    }

    public static void main(String[] args) throws IOException {
        Coconut.sanityCheck();
        EventQueue.invokeLater(new Main());
    }
}