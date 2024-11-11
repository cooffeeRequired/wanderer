package cz.coffee.rpggame.components;

import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public interface Changeable {
    void changeState(GameState state);
}
