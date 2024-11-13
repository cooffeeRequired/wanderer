package cz.coffee.facades;

import cz.coffee.structures.Floor;
import cz.coffee.utils.Console;
import cz.coffee.utils.Location;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

import static cz.coffee.GameConfig.TILES;
import static cz.coffee.GameConfig.TILE_SIZE;
import static cz.coffee.components.Board.LEVEL_MAP;

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
            GameEngine.getTiles().get(getDirection()).setLocation(location).draw(g);
        } else {
            Console.printlnVia("mv-handler", "DIR: " + getDirection() + " ");
        }
    }

    public void update(Graphics g) {
        if (GameEngine.getTiles().containsKey(getDirection())) {
            GameEngine.getTiles().get(getDirection()).setLocation(location).draw(g);
        }
    }
    public void spawn(Location location, Graphics g) {
        if (GameEngine.getTiles().containsKey(getDirection())) {
            GameEngine.getTiles().get(getDirection()).setLocation(location).draw(g);
        } else {
            Console.printlnVia("mv-handler", "DIR: " + getDirection());
        }
    }

    public void moveRandomOneTile() {
        do {
            var lc = this.getLocation();

            int nextX = lc.getX();
            int nextY = lc.getY();
            int direction = randomDir();

            if (direction == 0) {
                nextY = lc.getY() + TILE_SIZE; // down
            } else if (direction == 1) {
                nextY = lc.getY() - TILE_SIZE; // up
            } else if (direction == 2) {
                nextX = lc.getX() - TILE_SIZE; // left
            } else if (direction == 3) {
                nextX = lc.getX() + TILE_SIZE; // right
            }

            int row = nextY / TILE_SIZE;
            int col = nextX / TILE_SIZE;
            if (row >= 0 && row < TILES && col >= 0 && col < TILES
                    && !Floor.wallMatrix(LEVEL_MAP)[col][row]) {
                this.setLocation(nextX, nextY);
                break;
            }

        } while (true);
    }

}
