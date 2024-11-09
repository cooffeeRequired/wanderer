package cz.coffee.rpggame.models;


import cz.coffee.rpggame.models.patterns.CouldItems;
import cz.coffee.rpggame.models.patterns.CouldPotions;
import cz.coffee.rpggame.models.patterns.Leveling;
import cz.coffee.rpggame.services.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Hero extends GameEntity implements Leveling, CouldPotions, CouldItems {

    @Getter private final UUID uuid = UUID.randomUUID();

    int level = 1;
    @Getter @Setter public String direction = "img/hero-down.png";
    public Board board;

    @Getter @Setter boolean haveKey;


    public Hero() {
        super();
        maxHP = 30 + ranMultiple(3);
        currentHP = maxHP;
        dp = 2 * randInt();
        sp = 5 * randInt();
        PosX = PosY = 0;
    }

    @Override
    public void levelUp() {
        int index = randInt(10);
        if (index == 0) {
            this.currentHP = this.maxHP;
        } else if (index > 1 && index <= 5) {
            this.currentHP = this.currentHP + (this.maxHP * 0.33);
            if (this.currentHP > this.maxHP) {
                this.currentHP = this.maxHP;
            }
        } else if (index > 5) {
            this.currentHP = this.maxHP + (this.maxHP * 0.1);
            if (this.currentHP > this.maxHP) {
                this.currentHP = this.maxHP;
            }
        }
    }

    @Override
    public void nextLevel() {

    }

    @Override
    public void redPotion() {
        this.currentHP = this.maxHP;
        this.sp += 3;
        this.dp += 3;
    }

    @Override
    public void greenPotion() {
        this.currentHP = 10;
        this.sp -= 3 * level;
        this.dp -= 3 * level;
    }

    @Override
    public void gotShield() {
        this.dp += level * 12;
    }

    @Override
    public void gotSword() {
        this.sp += level * 12;
    }

    public void initialize() {
        this.PosX = 0;
        this.PosY = 0;
        this.levelUp();
    }

    @Override
    public String toString() {
        return "Hero{" +
                "direction='" + direction + '\'' +
                ", level=" + level +
                ", uuid=" + uuid +
                '}';
    }
}

