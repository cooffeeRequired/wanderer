package cz.coffee.rpggame.items;

import cz.coffee.rpggame.structures.GameStructure;
import cz.coffee.rpggame.utils.GameClass;

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
