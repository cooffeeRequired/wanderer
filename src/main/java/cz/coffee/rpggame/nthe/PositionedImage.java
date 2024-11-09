package cz.coffee.rpggame.nthe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PositionedImage {

    BufferedImage image;
    int posX, posY;

    public PositionedImage(String filename, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            var source = PositionedImage.class.getResource('/' + filename);

            image = ImageIO.read(source);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }

    }

}
