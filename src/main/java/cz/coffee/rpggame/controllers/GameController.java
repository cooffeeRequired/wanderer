package cz.coffee.rpggame.controllers;

import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.services.Board;
import cz.coffee.rpggame.structures.Floor;
import cz.coffee.rpggame.utils.GameObject;
import cz.coffee.rpggame.utils.PositionedImage;
import lombok.Getter;

import java.awt.*;

public class GameController {

    @Getter static GameObject entities = new GameObject();
    @Getter static GameObject structures = new GameObject();
    private final Hero hero;

    public GameController(Hero hero) {
        this.hero = hero;
    }

    public void startGame(Graphics g, int tiles, int size)
    {
        this.prepareMap();
        Floor.drawFloor(g, tiles, size);
        Floor.drawWalls(g, tiles, size);


        entities.getMonsters().forEach(monster -> {
            new PositionedImage(
                    monster.img,
                    monster.getLocation().getX(),
                    monster.getLocation().getY(),
                    true
            ).draw(g);
        });

        structures.getStructures().forEach(structure -> {
            new PositionedImage(
              structure.getImg(),
              structure.getX(),
              structure.getY(),
              true
            ).draw(g);
        });

       new PositionedImage(Board.heroDirection, Board.location.getX(), Board.location.getY(), true).draw(g);


    }

    private void prepareMap() {
        entities.clear();
        structures.clear();
        hero.initialize();

        // TODO implement map handler
    }
}
