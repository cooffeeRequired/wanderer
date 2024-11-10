package cz.coffee.rpggame.utils;

import lombok.Data;

@Data
public class Location {
    private int x, y, pitch;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
