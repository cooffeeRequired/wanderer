package cz.coffee.handlers;

import cz.coffee.entities.Boss;
import cz.coffee.entities.Hero;
import cz.coffee.enums.GameState;
import cz.coffee.facades.GameEngine;
import cz.coffee.facades.GameEntity;
import cz.coffee.facades.Monster;

import java.awt.*;

import static cz.coffee.GameConfig.ENTITIES;

public class EntityHandler implements Handler<GameEntity> {
    @Override
    public boolean handle(GameEntity entity) {
        var eLocation = entity.getLocation();

        var iterator = ENTITIES.iterator();
        while (iterator.hasNext()) {
            var e = iterator.next();
            if (e instanceof Monster monster) {
                var monsterLocation = monster.getLocation();

                if (monsterLocation.getX() == eLocation.getX() && monsterLocation.getY() == eLocation.getY()) {
                    if (monster instanceof Boss) {
                        System.out.println("[EntityHandler] process boss fight");
                        GameEngine.getBoard().changeState(GameState.BOSS_FIGHT);
                        // ! Process boss fight
                    } else {
                        System.out.println("[EntityHandler] kill the mob");
                        Graphics2D g2;

                        if ((g2 = GameEngine.getBoard().fight(monster, (Hero) entity)) != null) {
                            iterator.remove();

                            GameEngine.getTiles().get("boom")
                                    .setPosX(monsterLocation.getX())
                                    .setPosY(monsterLocation.getY())
                                    .draw(g2);

                        }
                        // ! Kill the mob
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void dispatchTo(GameEntity b) {

    }

    @Override
    public void mountTo(GameEntity b) {

    }
}
