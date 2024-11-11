package cz.coffee.facades.templates;

import cz.coffee.GameConfig;
import cz.coffee.components.Board;
import cz.coffee.entities.Hero;
import cz.coffee.facades.GameEngine;
import cz.coffee.structures.Floor;

@SuppressWarnings("all")
public interface DefaultEntity {

    default  boolean isWall(int x, int y) {
        int row = y / GameConfig.TILE_SIZE;
        int col = x / GameConfig.TILE_SIZE;
        return row < 0 || row >= GameConfig.TILES || col < 0 || col >= GameConfig.TILES
                || !Floor.wallMatrix(Board.LEVEL_MAP)[col][row];
    }

    default boolean isHero(int x, int y) {
        Hero hero = GameEngine.getHero();

        int heroTileX = hero.getLocation().getX() / GameConfig.TILE_SIZE;
        int heroTileY = hero.getLocation().getY()  / GameConfig.TILE_SIZE;
        int tileX = x / GameConfig.TILE_SIZE;
        int tileY = y / GameConfig.TILE_SIZE;
        return heroTileX == tileX && heroTileY == tileY;
    }
}
