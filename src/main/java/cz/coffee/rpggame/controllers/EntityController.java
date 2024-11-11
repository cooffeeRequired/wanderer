package cz.coffee.rpggame.controllers;

import java.awt.*;

import static cz.coffee.rpggame.GameConfig.ENTITIES;

public class EntityController {
    public void update(Graphics graphics) {
        ENTITIES.forEach(entity -> entity.update(graphics));
    };
}
