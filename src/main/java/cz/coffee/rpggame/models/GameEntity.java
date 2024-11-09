package cz.coffee.rpggame.models;

import lombok.Data;

import java.util.Random;

@Data
public abstract class GameEntity {
    double currentHP, maxHP, dp, sp;
    public int level, PosX, PosY;
    public Random rand = new Random();

    public GameEntity() {
        this.level = 1;
    }

    public int randInt() {
        return rand.nextInt(5) + 1;
    }

    int randInt(int x) {
        return rand.nextInt(x) + 1;
    }

    public int ranMultiple(int index) {
        int result = 0;
        for (int i = 0; i < index; i++) {
            result += randInt();
        }
        return result;
    }

    public int randomDir() {
        return randInt(4);
    }
}
