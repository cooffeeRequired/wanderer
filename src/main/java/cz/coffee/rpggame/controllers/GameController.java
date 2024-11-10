package cz.coffee.rpggame.controllers;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.models.*;
import cz.coffee.rpggame.structures.*;
import cz.coffee.rpggame.utils.GameObject;
import cz.coffee.rpggame.utils.PositionedImage;
import lombok.Getter;

import java.awt.*;

import static cz.coffee.rpggame.services.Board.LEVEL_MAP;

public class GameController {

    @Getter static GameObject entities = new GameObject();
    @Getter static GameObject structures = new GameObject();
    private final Hero hero;

    public GameController(Hero hero) {
        this.hero = hero;
    }

    public void play(Graphics g) {
        this.defaultPaint(g);
    }

    private void defaultPaint(Graphics g) {
        Floor.drawFloor(g, GameConfig.TILES, GameConfig.TILE_SIZE);
        Floor.drawWalls(g, GameConfig.TILES, GameConfig.TILE_SIZE);


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

        var location = hero.getLocation();
        new PositionedImage(hero.getDirection(), location.getX(), location.getY(), true).draw(g);
    }

    public void startGame(Graphics g)
    {
        this.prepareMap();
        this.defaultPaint(g);
    }

    private void prepareMap() {
        entities.clear();
        structures.clear();
        hero.initialize();

        if (LEVEL_MAP >= 1) {
            if (LEVEL_MAP == 1 || LEVEL_MAP == 5) {
                structures.add(new Shield());
                structures.add(new Sword());
            }
            entities.add(new SkeletonWKey());
            entities.add(new Boss());

            for (int i = 0; i < 2; i++) {
                entities.add(new Skeleton());


                if (LEVEL_MAP > 1) {
                    structures.add(new PotionRed());
                    for (int y = 0; y < LEVEL_MAP - 2; y++) {
                        entities.add(new Zombie());
                    }
                }
                if (LEVEL_MAP > 3) {
                    structures.add(new PotionGreen());
                    structures.add(new PotionRed());
                }
                if (LEVEL_MAP > 4) {
                    structures.add(new ZombieEgg());
                }
            }
        }
    }
}
