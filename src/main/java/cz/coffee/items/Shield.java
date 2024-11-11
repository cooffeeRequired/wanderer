package cz.coffee.items;

import cz.coffee.facades.GameStructure;
import cz.coffee.utils.GameClass;

@GameClass
public class Shield extends GameStructure {
    public Shield() {
        setImg("shield");
    }

    @Override
    public boolean spawned() {
        return this.spawned;
    }

}
