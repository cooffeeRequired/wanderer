package cz.coffee.facades;

import cz.coffee.GameConfig;
import cz.coffee.components.Board;
import cz.coffee.entities.Hero;
import cz.coffee.facades.templates.DefaultEntity;
import cz.coffee.structures.Floor;
import cz.coffee.utils.Location;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

import static cz.coffee.GameConfig.TILES;
import static cz.coffee.GameConfig.TILE_SIZE;
import static cz.coffee.components.Board.LEVEL_MAP;

@Data
public abstract class GameStructure implements DefaultEntity {
    String img;

    @Getter Location location;
    private static final int tileSize = GameConfig.TILE_SIZE;
    private static final int numTiles = GameConfig.TILES;

    public GameStructure() {
        int x, y;

        Random random = new Random();
        do {
            int randomX = random.nextInt(numTiles);
            int randomY = random.nextInt(numTiles);
            int posX = randomX * tileSize;
            int posY = randomY * tileSize;

            if (isWall(posX, posY) && !isHero(posX, posY)) {
                x = posX;
                y = posY;
                break;
            }

        } while (true);

        this.location = new Location(x, y);
    }


    @Getter @Setter protected boolean spawned = false;

    @SuppressWarnings("unused")
    public abstract boolean spawned();

    public void spawn(Graphics g) {
        GameEngine.getTiles()
                .get(getImg())
                .setLocation(location)
                .draw(g);
    }
    @SuppressWarnings("unused")
    public void spawn(int x, int y, Graphics g) {}

    public void update(Graphics g) {
        GameEngine.getTiles()
                .get(getImg())
                .setLocation(location)
                .draw(g);
    }

    @Override
    public boolean isWall(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return row < 0 || row >= numTiles || col < 0 || col >= numTiles
                || !Floor.wallMatrix(Board.LEVEL_MAP)[col][row];
    }
    @Override
    public boolean isHero(int x, int y) {
        Hero hero = GameEngine.getHero();

        int heroTileX = hero.getLocation().getX() / tileSize;
        int heroTileY = hero.getLocation().getY()  / tileSize;
        int tileX = x / tileSize;
        int tileY = y / tileSize;
        return heroTileX == tileX && heroTileY == tileY;
    }

    public void moveRandomOneTile() {
        do {
            int nextX = location.getX();
            int nextY = location.getY();
            int direction = new Random().nextInt(5);

            if (direction == 0) {
                nextY = nextY + TILE_SIZE; // down
            } else if (direction == 1) {
                nextY = nextY - TILE_SIZE; // up
            } else if (direction == 2) {
                nextX = nextX- TILE_SIZE; // left
            } else if (direction == 3) {
                nextX = nextX+ TILE_SIZE; // right
            }

            int row = nextY / TILE_SIZE;
            int col = nextX / TILE_SIZE;
            if (row >= 0 && row < TILES && col >= 0 && col < TILES
                    && !Floor.wallMatrix(LEVEL_MAP)[col][row]) {
                this.setLocation(location);
                break;
            }

        } while (true);
    }


}
