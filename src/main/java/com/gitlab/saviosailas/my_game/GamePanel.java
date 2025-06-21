package com.gitlab.saviosailas.my_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int fps = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // 1 second = 1e+9 = 1 × 10⁹ = 1,000,000,000 (one billion) nanoseconds
        // drawInterval =. 1/60 sec = 1e+9/60 ns
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Game loop
        while (true) {
            // 1. Update: update information like character position
            update();
            // 2. Draw: draw screen with the updated information
            repaint(); // call the paintComponent();
            long remainingSleepTime = (long) nextDrawTime - System.nanoTime();
            // 1 ms = 1e+6 ns = 1,000,000 (one million) nanoseconds
            // convert nanoseconds to milliseconds | ms = ns/1,000,000
            remainingSleepTime = remainingSleepTime / 1000000;
            if (remainingSleepTime < 0) {
                remainingSleepTime = 0;
            }
            try {
                Thread.sleep(remainingSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextDrawTime += drawInterval;
        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // JPanel implemention

        // Convert Graphics g to Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
