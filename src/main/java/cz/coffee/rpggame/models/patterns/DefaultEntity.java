package cz.coffee.rpggame.models.patterns;

public interface DefaultEntity {
    default boolean isWall(int x, int y) {
        return false;
    }
    default boolean isHero(int x, int y) {
        return false;
    }

    default void moveRandomOneTile(int x, int y) {

    }
}
