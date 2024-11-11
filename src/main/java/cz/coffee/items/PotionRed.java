package cz.coffee.items;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

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