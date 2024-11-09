package cz.coffee.rpggame.services;

import cz.coffee.rpggame.enums.GameState;

public interface Changeable {
    public abstract void changeState(GameState state);
}
