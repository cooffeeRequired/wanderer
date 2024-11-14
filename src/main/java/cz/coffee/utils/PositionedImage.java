package cz.coffee.utils;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PositionedImage {

    BufferedImage image;
    @Getter Location location;

    public PositionedImage(String filename, int posX, int posY, boolean resource) {
        this.location = new Location(posX, posY);
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
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }

    }

    public PositionedImage setLocation(Location location) {
        this.location = location;

        return this;
    }
}
