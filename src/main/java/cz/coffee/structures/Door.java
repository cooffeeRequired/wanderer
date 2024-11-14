package cz.coffee.structures;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

@GameClass
public class Door extends GameStructure {
    public Door() {
        setImg("door");
    }

    @Override
    public boolean spawned() {
        return false;
    }

}
