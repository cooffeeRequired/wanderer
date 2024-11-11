package cz.coffee.rpggame.items;

import cz.coffee.rpggame.structures.GameStructure;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class Sword extends GameStructure {
    public Sword() {
        setImg("sword");
    }

    @Override
    public boolean spawned() {
        return spawned;
    }
}
