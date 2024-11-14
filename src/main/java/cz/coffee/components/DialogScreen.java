package cz.coffee.components;

import cz.coffee.utils.PositionedImage;

import javax.swing.*;
import java.awt.*;

public class DialogScreen extends JComponent {
    private final PositionedImage screenImage;
    private static final int MAX_WIDTH = 520;


    public DialogScreen() {
        screenImage = new PositionedImage("img/UI/dialog.png", 0, 0, true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        screenImage.draw(g);
    }

    public void drawWithText(Graphics g, String text) {
        paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 15));

        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();
        int x = 100;
        int y = 330;

        String[] paragraphs = text.split("\n");

        for (String paragraph : paragraphs) {
            String[] words = paragraph.split(" ");
            StringBuilder line = new StringBuilder();

            for (String word : words) {
                String testLine = line + word + " ";
                int lineWidth = metrics.stringWidth(testLine);

                if (lineWidth > MAX_WIDTH) {
                    g.drawString(line.toString(), x, y);
                    y += lineHeight;
                    line = new StringBuilder(word + " ");
                } else {
                    line.append(word).append(" ");
                }
            }

            if (!line.isEmpty()) {
                g.drawString(line.toString(), x, y);
                y += lineHeight;
            }
            y += lineHeight / 2;
        }
    }
}
