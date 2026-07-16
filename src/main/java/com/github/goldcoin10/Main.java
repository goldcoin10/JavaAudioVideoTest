package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class filePaths {
    static ImageIcon playIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/playIcon.png")));
    static ImageIcon pauseIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/pauseIcon.png")));
    static ImageIcon jumpFive = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/jumpFive.png")));
    static ImageIcon backFive = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/backFive.png")));
    static ImageIcon logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/logo.png")));
    static ImageIcon add = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/add.png")));
    static ImageIcon sub = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/sub.png")));
}

class Main implements Runnable {
    public static File filepath;
    public static boolean audioPlaying = false;
    static JFrame frame = new JFrame("MediaPlayer");
    static final FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files", "wav", "aac", "mp3");

    public void run() {
        renderGUI();
    }

    public void renderGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // Set custom code after this line
        frame.setIconImage(filePaths.logo.getImage());
        frame.setSize(320, 240);
        frame.setJMenuBar(Menu.renderMenuBar());
        frame.add(Frame.renderFrame());
        new JFXPanel();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Coconut.sanityCheck();
        EventQueue.invokeLater(new Main());
    }
}