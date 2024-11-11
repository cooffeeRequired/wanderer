package cz.coffee.rpggame.facades.templates;

@SuppressWarnings("all")
public interface DefaultEntity {

    default boolean isWall(int x, int y) {
        return true;
    }
    default boolean isHero(int x, int y) {
        return false;
    }

    default void moveRandomOneTile(int x, int y) {

    }
}
