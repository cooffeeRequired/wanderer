package cz.coffee.rpggame.components;

import cz.coffee.rpggame.utils.PositionedImage;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JComponent {
    private final PositionedImage startScreenImage;

    public StartScreen() {
        startScreenImage = new PositionedImage("img/startScreen.png", 0, 0, true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        startScreenImage.draw(g);
    }
}
