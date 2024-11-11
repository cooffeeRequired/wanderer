package cz.coffee.rpggame.controllers;

import cz.coffee.rpggame.GameConfig;
import cz.coffee.rpggame.facades.GameEntity;
import cz.coffee.rpggame.items.PotionGreen;
import cz.coffee.rpggame.items.PotionRed;
import cz.coffee.rpggame.items.Shield;
import cz.coffee.rpggame.items.Sword;
import cz.coffee.rpggame.models.*;
import cz.coffee.rpggame.structures.Floor;
import cz.coffee.rpggame.structures.GameStructure;
import cz.coffee.rpggame.structures.ZombieEgg;

import java.awt.*;
import java.util.Deque;

import static cz.coffee.rpggame.GameConfig.*;
import static cz.coffee.rpggame.components.Board.LEVEL_MAP;

public class GameController {

    private final Hero hero;

    public GameController(Hero hero) {
        this.hero = hero;
    }

    public void play(Graphics g) {
        if (Floor.isFloorDrown && Floor.isWallsDrown) {
            Floor.drawExistingFloor(g);
            Floor.drawExistingWalls(g);
        } else {
            Floor.drawFloor(g, GameConfig.TILES, GameConfig.TILE_SIZE);
            Floor.drawWalls(g, GameConfig.TILES, GameConfig.TILE_SIZE);
        }

        hero.update(g);
    }


    public void startGame(Graphics g)
    {
        Floor.drawFloor(g, GameConfig.TILES, GameConfig.TILE_SIZE);
        Floor.drawWalls(g, GameConfig.TILES, GameConfig.TILE_SIZE);

        hero.spawn(0, 0, g);

        //this.initializeItems().forEach((item) -> item.spawn(g));
        //this.initializeStructures().forEach((structure) -> structure.spawn(g));
        this.initializeEntities().forEach((entity) -> entity.spawn(g));
    }

    protected Deque<GameStructure> initializeItems() {
        ITEMS.clear();

        if (LEVEL_MAP == 1 || LEVEL_MAP == 5) {
            ITEMS.add(new Shield());
            ITEMS.add(new Sword());

            for (int i = 0; i < 2; i++) {
                if (LEVEL_MAP > 1) ITEMS.add(new PotionRed());
                if (LEVEL_MAP > 3) {
                    ITEMS.add(new PotionGreen());
                    ITEMS.add(new PotionRed());
                }
                if (LEVEL_MAP > 4) STRUCTURES.add(new ZombieEgg());
            }
        }

        return ITEMS;
    }

    protected Deque<GameStructure> initializeStructures() {
        STRUCTURES.clear();

        return STRUCTURES;
    }

    protected Deque<GameEntity> initializeEntities() {
        ENTITIES.clear();
        ENTITIES.add(new Skeleton());
        ENTITIES.add(new Boss());

        for (int i = 0; i < 2; i++) {
            ENTITIES.add(new Skeleton());

            if (LEVEL_MAP > 1) {
                for (int y = 0; y < LEVEL_MAP - 2; y++) ENTITIES.add(new Zombie());
            }
        }

        return ENTITIES;
    }
}
