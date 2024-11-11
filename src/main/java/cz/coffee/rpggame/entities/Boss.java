package cz.coffee.rpggame.entities;

import cz.coffee.rpggame.facades.Monster;
import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class Boss extends Monster {
    public Boss() {
        super();
        if (getLevel() > 5) {
            setMaxHP(getLevel()/1.5 * mIndex * randInt() + randInt());
            setCurrentHP(getMaxHP());
            setDp(mIndex  * randInt() + ranMultiple(2));
            setSp(mIndex * randInt() + mIndex*4);

        } else {
            setCurrentHP(2 * mIndex * randInt() + randInt());
            setCurrentHP(getMaxHP());
            var newDP = ((float) mIndex / 2 * randInt()) + randInt();
            var newSP = mIndex * randInt() + mIndex * 2;

            setDp(newDP);
            setSp(newSP);
        }
        setDirection("boss");
    }
}
