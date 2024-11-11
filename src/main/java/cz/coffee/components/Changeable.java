package cz.coffee.components;

import cz.coffee.enums.GameState;
import cz.coffee.utils.GameClass;

@GameClass
public interface Changeable {
    void changeState(GameState state);
}
