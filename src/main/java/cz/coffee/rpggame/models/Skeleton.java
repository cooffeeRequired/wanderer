package cz.coffee.rpggame.models;

public class Skeleton extends Monster {
    public Skeleton() {
        super();
        img = "img/Skeleton.png";
        if (level > 5){
            maxHP = level  * mIndex * randInt() * 5;
            currentHP = maxHP;
            dp = mIndex * randInt() * 4;
            sp = mIndex * randInt() * 4;
        } else {
            maxHP = (double) (level / 2) * mIndex * randInt();
            currentHP = maxHP;
            dp = (double) mIndex / 2 * randInt();
            sp = mIndex * randInt();
        }
    }
}
