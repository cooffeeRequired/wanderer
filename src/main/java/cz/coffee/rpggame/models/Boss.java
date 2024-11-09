package cz.coffee.rpggame.models;

public class Boss extends Monster {
    public Boss() {
        super();
        img = "img/boss.png";
        if (level > 5) {
            maxHP = level/1.5 * mIndex * randInt() + randInt();
            currentHP = maxHP;
            dp = mIndex  * randInt() + ranMultiple(2);
            sp = mIndex * randInt() + mIndex*4;
        } else {
            maxHP = 2 * mIndex * randInt() + randInt();
            currentHP = maxHP;
            dp = ((float) mIndex / 2 * randInt()) + randInt();
            sp = mIndex * randInt() + mIndex * 2;
        }
    }
}
