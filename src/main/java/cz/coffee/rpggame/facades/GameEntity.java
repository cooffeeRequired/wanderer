package cz.coffee.rpggame.facades;

import cz.coffee.rpggame.utils.Console;
import cz.coffee.rpggame.utils.Location;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("unused")
public abstract class GameEntity {
    @Getter @Setter private double currentHP, maxHP, dp, sp;
    @Getter @Setter private String direction;
    @Setter @Getter private Location location = new Location(0, 0);
    @Getter @Setter private int level;
    @Getter private final UUID uuid = UUID.randomUUID();

    @Getter @Setter boolean haveKey;

    public Random rand = new Random();

    public GameEntity() {
        this.level = 1;
    }

    public int randInt() {
        return rand.nextInt(5) + 1;
    }

    public int randInt(int x) {
        return rand.nextInt(x) + 1;
    }

    public int ranMultiple(int index) {
        int result = 0;
        for (int i = 0; i < index; i++) {
            result += randInt();
        }
        return result;
    }

    public int randomDir() {
        return randInt(4);
    }

    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }

    public void spawn(Graphics g) {
        if (GameEngine.getTiles().containsKey(getDirection())) {
            GameEngine.getTiles().get(getDirection()).setPosX(location.getX()).setPosY(location.getY()).draw(g);
        } else {
            Console.printlnVia("mv-handler", "DIR: " + getDirection() + " ");
        }
    }

    public void update(Graphics g) {
        if (GameEngine.getTiles().containsKey(getDirection())) {
            GameEngine.getTiles().get(getDirection()).setPosX(location.getX()).setPosY(location.getY()).draw(g);
        }
    }
    public void spawn(int x, int y, Graphics g) {
        if (GameEngine.getTiles().containsKey(getDirection())) {
            GameEngine.getTiles().get(getDirection()).setPosX(x).setPosY(y).draw(g);
        } else {
            Console.printlnVia("mv-handler", "DIR: " + getDirection());
        }
    }

}
