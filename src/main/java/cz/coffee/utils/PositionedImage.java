package cz.coffee.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PositionedImage {

    BufferedImage image;
    int posX, posY;

    public PositionedImage(String filename, int posX, int posY, boolean resource) {
        this.posX = posX;
        this.posY = posY;
        try {
            if (resource) {
                var rs = PositionedImage.class.getResource('/' + filename);

                if (rs != null) {
                    image = ImageIO.read(rs);
                }
            } else {
                image = ImageIO.read(new File(filename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PositionedImage(String filename, boolean resource) {
        try {
            if (resource) {
                var rs = PositionedImage.class.getResource('/' + filename);
                if (rs != null) {
                    image = ImageIO.read(rs);
                }
            } else {
                image = ImageIO.read(new File(filename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }

    }

    public PositionedImage setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public PositionedImage setPosY(int posY) {
        this.posY = posY;
        return this;
    }

}
