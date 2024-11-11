package cz.coffee.rpggame.structures;

import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class Door extends GameStructure {
    public Door() {
        img = "door";
    }

    @Override
    public boolean spawned() {
        return false;
    }

}
