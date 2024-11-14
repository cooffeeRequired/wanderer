package cz.coffee.components;

import cz.coffee.utils.PositionedImage;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JComponent {
    private final PositionedImage startScreenImage;

    public StartScreen() {
        startScreenImage = new PositionedImage("img/UI/startScreen2.png", 0, 0, true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        startScreenImage.draw(g);
    }
}
