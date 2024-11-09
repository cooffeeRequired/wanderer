package cz.coffee.rpggame.models;

public class Zombie extends Monster {
    public Zombie() {
        super();
        if (level > 5){
            maxHP = level  * mIndex * randInt() * 10;
            currentHP = maxHP;
            dp = mIndex * randInt() * 6;
            sp = mIndex * randInt() * 6;
        } else {
            maxHP = (double) level / 2 * mIndex * randInt()+10;
            currentHP = maxHP;
            dp = (double) mIndex / 2 * randInt()+5;
            sp = mIndex * randInt()+5;
        }
        img = "img/zombie.png";
    }
}
