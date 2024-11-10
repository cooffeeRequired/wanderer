package cz.coffee.rpggame.structures;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.models.patterns.DefaultEntity;
import cz.coffee.rpggame.services.Board;
import cz.coffee.rpggame.services.GameEngine;
import lombok.Data;

import java.util.Random;

@Data
public class GameStructure implements DefaultEntity {
    String img;
    int x;
    int y;

    int positionX;
    int positionY;
    private static final int tileSize = GameConfig.TILE_SIZE;
    private static final int numTiles = GameConfig.TILES;

    public GameStructure() {
        Random random = new Random();
        do {
            int randomX = random.nextInt(numTiles);
            int randomY = random.nextInt(numTiles);
            int posX = randomX * tileSize;
            int posY = randomY * tileSize;

            if (!isWall(posX, posY) && !isHero(posX, posY)) {
                x = posX;
                y = posY;
                break;
            }

        } while (true);

        positionX = x;
        positionY = y;
    }

    @Override
    public boolean isWall(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return row >= 0 && row < numTiles && col >= 0 && col < numTiles
                && Floor.wallMatrix(Board.LEVEL_MAP)[col][row];
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

}
