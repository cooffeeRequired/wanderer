package cz.coffee.rpggame.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPSCounter extends JComponent implements ActionListener {
    private long lastTime;
    private int frames;
    private int fps;

    public FPSCounter() {
        Timer timer = new Timer(1000, this);
        timer.start();
        lastTime = System.currentTimeMillis();
        frames = 0;
        fps = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        fps = frames;
        frames = 0;
        lastTime = currentTime;
        repaint();
    }

    public void incrementFrames() {
        frames++;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        String fpsText = "FPS: " + fps;
        g.drawString(fpsText, 10, 40);
    }
}
