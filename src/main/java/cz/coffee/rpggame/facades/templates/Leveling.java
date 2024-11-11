package cz.coffee.rpggame.facades.templates;

public interface Leveling {
    default void setLevel(int level) {
        return;
    }
    default int getLevel() {
        return 0;
    }
    void levelUp();
    void nextLevel();
}
