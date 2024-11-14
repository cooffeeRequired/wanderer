package cz.coffee.components;

import cz.coffee.utils.PositionedImage;

import javax.swing.*;
import java.awt.*;

public class FightUI extends JComponent {
    private final PositionedImage fightUIImage;

    public FightUI() {
        fightUIImage = new PositionedImage("img/UI/template-bos.png", 0, 0, true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fightUIImage.draw(g);
    }
}
