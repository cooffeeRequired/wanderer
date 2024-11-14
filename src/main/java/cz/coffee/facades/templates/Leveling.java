package cz.coffee.facades.templates;

@SuppressWarnings("unused")
public interface Leveling {
    default void setLevel(int level) {
    }
    default int getLevel() {
        return 0;
    }
    void levelUp();
    void nextLevel();
}
