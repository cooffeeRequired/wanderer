package cz.coffee.items;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

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

