package com.github.goldcoin10;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;

class filePaths {
    static ImageIcon playIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/playIcon.png")));
    static ImageIcon pauseIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/pauseIcon.png")));
    static ImageIcon jumpFive = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/jumpFive.png")));
    static ImageIcon backFive = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/backFive.png")));
}

class Main implements Runnable {
    public static File filepath;
    public static boolean audioPlaying = false;

    public void run() {
        renderGUI();
    }

    public void renderGUI() {
        JFrame frame = new JFrame("MediaPlayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // Set custom code after this line
        frame.setLayout(new GridBagLayout());
        frame.setSize(320, 240);
        frame.setJMenuBar(Menu.renderMenuBar());
        frame.add(Buttons.renderButtons());
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Coconut.sanityCheck();
        EventQueue.invokeLater(new Main());
    }
}