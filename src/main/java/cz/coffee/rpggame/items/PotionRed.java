package cz.coffee.rpggame.items;

import cz.coffee.rpggame.structures.GameStructure;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class PotionRed extends GameStructure {
    public PotionRed() {
        setImg("red-pot");
    }

    @Override
    public boolean spawned() {
        return this.spawned;
    }
}