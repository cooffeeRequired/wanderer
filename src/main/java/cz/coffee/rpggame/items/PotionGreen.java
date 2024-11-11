package cz.coffee.rpggame.items;

import cz.coffee.rpggame.structures.GameStructure;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class PotionGreen extends GameStructure {
    public PotionGreen() {
        setImg("green-pot");
    }

    @Override
    public boolean spawned() {
        return this.spawned;
    }

}

