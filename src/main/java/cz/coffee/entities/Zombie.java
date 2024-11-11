package cz.coffee.entities;

import cz.coffee.facades.Monster;
import cz.coffee.utils.GameClass;

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
        setDirection("zombie");
    }
}
