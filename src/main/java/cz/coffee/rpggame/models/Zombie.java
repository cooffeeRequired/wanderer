package cz.coffee.rpggame.models;

import cz.coffee.rpggame.facades.Monster;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class Zombie extends Monster {
    public Zombie() {
        super();
        if (getLevel() > 5){
            setMaxHP(getLevel()  * mIndex * randInt() * 10);
            setCurrentHP(getMaxHP());
            setDp(mIndex * randInt() * 6);
            setSp(mIndex * randInt() * 6);
        } else {
            setMaxHP((double) getLevel() / 2 * mIndex * randInt()+10);
            setCurrentHP(getMaxHP());
            setDp((double) mIndex / 2 * randInt()+5);
            setSp(mIndex * randInt()+5);
        }
        img = "zombie";
    }
}
