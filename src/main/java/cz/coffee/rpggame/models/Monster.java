package cz.coffee.rpggame.models;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.models.patterns.DefaultEntity;
import cz.coffee.rpggame.services.Board;
import cz.coffee.rpggame.structures.Floor;

public class Monster extends GameEntity implements DefaultEntity {
    public String img;
    int mIndex;

    private static final int tileSize = GameConfig.TILE_SIZE;
    private static final int tiles = GameConfig.TILES;

    public Monster() {
        this.setMonsterIndex();
        int x;
        int y;
        do {
            int randomX = rand.nextInt(tiles);
            int randomY = rand.nextInt(tiles);
            int PosX = randomX * tileSize;
            int PosY = randomY * tileSize;

            if (!isWall(PosX, PosY) && !isHero(PosX, PosY)) {
                x = PosX;
                y = PosY;
                break;
            }
        } while (true);

        getLocation().setLocation(x, y);
    }

    @Override
    public boolean isWall(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return row >= 0 && row < tiles && col >= 0 && col < tiles && Floor.wallMatrix(Board.LEVEL_MAP)[col][row];
    }


    public void setMonsterIndex() {
        int i = rand.nextInt(10) + 1;

        if (i == 1) {
            mIndex = getLevel() + 2;
        } else if (i <= 5) {
            mIndex = getLevel() + 1;
        } else {
            mIndex = getLevel();
        }
    }

    @Override
    public void moveRandomOneTile(int x, int y) {
        do {

            int nextX = getLocation().getX();
            int nextY = getLocation().getY();
            int direction = randomDir();

            var location = getLocation();

            if (direction == 0) {
                nextY = location.getY() + GameConfig.TILE_SIZE; // down
            } else if (direction == 1) {
                nextY = location.getY()  - GameConfig.TILE_SIZE;; // up
            } else if (direction == 2) {
                nextX = location.getX()  - GameConfig.TILE_SIZE;; // left
            } else if (direction == 3) {
                nextX = location.getX()  + GameConfig.TILE_SIZE;; // right
            }

            int row = nextY / tileSize;
            int col = nextX / tileSize;
            if (row >= 0 && row < GameConfig.TILES && col >= 0 && col < GameConfig.TILES && !Floor.wallMatrix(Board.LEVEL_MAP)[col][row]) {
                getLocation().setLocation(nextX, nextY);
                break;
            }
        } while (true);
    }
}
