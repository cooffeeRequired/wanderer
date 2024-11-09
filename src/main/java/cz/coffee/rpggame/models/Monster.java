package cz.coffee.rpggame.models;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.models.patterns.DefaultEntity;
import cz.coffee.rpggame.services.Board;
import cz.coffee.rpggame.services.GameEngine;
import cz.coffee.rpggame.structures.Floor;

public class Monster extends GameEntity implements DefaultEntity {
    private int x;
    private int y;
    public String img;
    int mIndex;

    private static final int tileSize = GameConfig.TILE_SIZE;
    private static final int tiles = GameConfig.TILES;

    public Monster() {
        this.setMonsterIndex();
        int x;
        int y;
        do {
            int randomX = this.rand.nextInt(tiles);
            int randomY = this.rand.nextInt(tiles);
            int PosX = randomX * tileSize;
            int PosY = randomY * tileSize;

            if (!isWall(PosX, PosY) && !isHero(PosX, PosY)) {
                x = PosX;
                y = PosY;
                break;
            }
        } while (true);

        PosX = x;
        PosY = y;
    }

    @Override
    public boolean isWall(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return row >= 0 && row < tiles && col >= 0 && col < tiles && Floor.wallMatrix(Board.LEVEL_MAP)[col][row];
    }

    @Override
    public boolean isHero(int x, int y) {
        Hero hero = GameEngine.board.getHero();

        int heroTileX = hero.PosX/ tileSize;
        int heroTileY = hero.PosY / tileSize;
        int tileX = x / tileSize;
        int tileY = y / tileSize;
        return heroTileX == tileX && heroTileY == tileY;
    }

    public void setMonsterIndex() {
        int i = rand.nextInt(10) + 1;

        if (i == 1) {
            mIndex = level + 2;
        } else if (i <= 5) {
            mIndex = level + 1;
        } else {
            mIndex = level;
        }
    }

    @Override
    public void moveRandomOneTile(int x, int y) {
        do {

            int nextX = getPosX();
            int nextY = getPosY();
            int direction = randomDir();

            if (direction == 0) {
                nextY = this.PosY + GameConfig.TILE_SIZE; // down
            } else if (direction == 1) {
                nextY = PosY - GameConfig.TILE_SIZE;; // up
            } else if (direction == 2) {
                nextX = PosX - GameConfig.TILE_SIZE;; // left
            } else if (direction == 3) {
                nextX = PosX + GameConfig.TILE_SIZE;; // right
            }

            int row = nextY / tileSize;
            int col = nextX / tileSize;
            if (row >= 0 && row < GameConfig.TILES && col >= 0 && col < GameConfig.TILES && !Floor.wallMatrix(Board.LEVEL_MAP)[col][row]) {
                this.PosX = nextX;
                this.PosY = nextY;
                break;
            }
        } while (true);
    }
}
