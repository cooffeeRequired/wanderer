package cz.coffee.controllers;

import java.awt.*;

import static cz.coffee.GameConfig.ENTITIES;

public class EntityController {
    public void update(Graphics graphics) {
        ENTITIES.forEach(entity -> entity.update(graphics));
    }
}
