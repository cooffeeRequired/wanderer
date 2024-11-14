package cz.coffee.structures;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

@GameClass
public class ZombieEgg extends GameStructure {
    public ZombieEgg() {
        setImg("zombieEgg");
    }

    @Override
    public boolean spawned() {
        return false;
    }
}
