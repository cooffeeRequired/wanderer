package cz.coffee.components;

import cz.coffee.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static cz.coffee.GameConfig.TILE_SIZE;
import static java.awt.Color.black;

public class FPSCounter extends JComponent implements ActionListener {
    private int frames;
    private int fps;

    public FPSCounter() {
        Timer timer = new Timer(1000, this);
        timer.start();
        frames = 0;
        fps = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fps = frames;
        frames = 0;
        repaint();
    }

    public void incrementFrames() {
        frames++;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawHeroFPS((Graphics2D) g);
    }

    public void drawHeroFPS(Graphics2D g2) {
        int frameY = -20;
        int frameWidth = 2 * TILE_SIZE;
        int frameX = (GameConfig.GAME_WINDOW_WIDTH - 120);
        int frameHeight = 49;

        Color c = new Color(255, 255, 255, 180); // frame
        g2.setColor(c);
        g2.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        g2.setColor(black); // stats

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));
        int textX = frameX + 33;
        int textY = frameY + 40;
        g2.drawString("FPS: " + fps, textX, textY);
    }
}
