package cz.coffee.handlers;

import cz.coffee.GameConfig;
import cz.coffee.entities.Boss;
import cz.coffee.facades.GameEntity;
import cz.coffee.facades.Monster;

public class EntityHandler implements Handler<GameEntity> {
    @Override
    public boolean handle(GameEntity entity) {
        var eLocation = entity.getLocation();

        for (var e : GameConfig.ENTITIES) {
            if (e instanceof Monster monster) {
                var monsterLocation = monster.getLocation();

                if (monsterLocation.getX() == eLocation.getX() && monsterLocation.getY() == eLocation.getY()) {
                    if (monster instanceof Boss) {
                        System.out.println("[EntityHandler] process boss fight");
                        // ! Process boss fight
                    } else {
                        System.out.println("[EntityHandler] kill the mob");
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
