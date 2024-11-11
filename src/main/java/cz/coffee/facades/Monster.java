package cz.coffee.facades;

import cz.coffee.GameConfig;
import cz.coffee.facades.templates.DefaultEntity;
import cz.coffee.utils.GameClass;

@GameClass
public class Monster extends GameEntity implements DefaultEntity {
    public int mIndex;

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

            if (isWall(PosX, PosY) && !isHero(PosX, PosY)) {
                x = PosX;
                y = PosY;
                break;
            }
        } while (true);

        getLocation().setLocation(x, y);
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
}
