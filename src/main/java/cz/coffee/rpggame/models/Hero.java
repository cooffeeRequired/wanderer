package cz.coffee.rpggame.models;


import cz.coffee.rpggame.models.patterns.CouldItems;
import cz.coffee.rpggame.models.patterns.CouldPotions;
import cz.coffee.rpggame.models.patterns.Leveling;
import cz.coffee.rpggame.utils.Location;

public class Hero extends GameEntity implements Leveling, CouldPotions, CouldItems {

    int level = 1;

    public Hero() {
        super();
        setMaxHP(30 + ranMultiple(3));
        setCurrentHP(getMaxHP());
        setDp(2 * randInt());
        setSp(5* randInt());
        setLocation(0, 0);
    }

    @Override
    public void levelUp() {
        int index = randInt(10);
        if (index == 0) {
            setCurrentHP(getMaxHP());
        } else if (index > 1 && index <= 5) {
            setCurrentHP(getCurrentHP() + (getMaxHP() * 0.33));
            if (getCurrentHP() > getMaxHP()) {
                setCurrentHP(getMaxHP());
            }
        } else if (index > 5) {
            setCurrentHP(getCurrentHP() + (getMaxHP() * 0.1));
            if (getCurrentHP() > getMaxHP()) {
                setCurrentHP(getMaxHP());
            }
        }
    }

    @Override
    public void nextLevel() {

    }

    @Override
    public void redPotion() {
        setCurrentHP(getMaxHP());
        setSp(getSp() + 3);
        setDp(getDp() + 3);
    }

    @Override
    public void greenPotion() {
        setCurrentHP(10);
        setSp(getSp() - 3 * level);
        setDp(getSp() - 3 * level);
    }

    @Override
    public void gotShield() {
        setDp(getDp() + level * 12);
    }

    @Override
    public void gotSword() {
        setSp(getSp() + level * 12);
    }

    public void initialize() {
        setLocation(new Location(0, 0));
        this.levelUp();
    }

    @Override
    public String toString() {
        return "Hero{" +
                "direction='" + getDirection() + '\'' +
                ", level=" + level +
                ", uuid=" + getUuid() +
                '}';
    }
}

