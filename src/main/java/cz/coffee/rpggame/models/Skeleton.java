package cz.coffee.rpggame.models;

import cz.coffee.rpggame.utils.GameClass;

@GameClass
public class Skeleton extends Monster {
    public Skeleton() {
        super();
        img = "img/Skeleton.png";
        if (getLevel() > 5){
            setMaxHP(getLevel()  * mIndex * randInt() * 5);
            setCurrentHP(getMaxHP());

            setDp(mIndex * randInt() * 4);
            setSp(mIndex * randInt() * 4);
        } else {
            setMaxHP((double) (getLevel() / 2) * mIndex * randInt());
            setCurrentHP(getMaxHP());
            setDp((double) mIndex / 2 * randInt());
            setSp(mIndex * randInt());
        }
    }
}
