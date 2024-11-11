package cz.coffee.items;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

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
