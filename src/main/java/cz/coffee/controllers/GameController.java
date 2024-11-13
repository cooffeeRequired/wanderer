package cz.coffee.controllers;

import cz.coffee.GameConfig;
import cz.coffee.components.Board;
import cz.coffee.entities.*;
import cz.coffee.facades.GameEntity;
import cz.coffee.facades.GameStructure;
import cz.coffee.items.PotionGreen;
import cz.coffee.items.PotionRed;
import cz.coffee.items.Shield;
import cz.coffee.items.Sword;
import cz.coffee.structures.Floor;
import cz.coffee.structures.ZombieEgg;
import cz.coffee.utils.Location;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.Deque;

import static cz.coffee.GameConfig.*;
import static cz.coffee.components.Board.LEVEL_MAP;

public record GameController(Hero hero) {

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


    public void startGame(Graphics g) {
        Floor.drawFloor(g, GameConfig.TILES, GameConfig.TILE_SIZE);
        Floor.drawWalls(g, GameConfig.TILES, GameConfig.TILE_SIZE);

        hero.spawn(new Location(0, 0), g);

        this.initializeItems().forEach((item) -> {
            var colm = item.getLocation().getX() / TILE_SIZE;
            var row = item.getLocation().getY() / TILE_SIZE;

            if (Floor.wallMatrix(Board.LEVEL_MAP)[colm][row]) {
                item.moveRandomOneTile();
            }

            item.spawn(g);
        });
        this.initializeStructures().forEach((structure) -> structure.spawn(g));
        this.initializeEntities().forEach((entity) -> {
            var eL = entity.getLocation();
            var colm = eL.getX() / TILE_SIZE;
            var row = eL.getY() / TILE_SIZE;

            if (Floor.wallMatrix(Board.LEVEL_MAP)[colm][row]) {
                entity.moveRandomOneTile();
            }

            entity.spawn(g);
        });

        playSound("game-start.au");
    }

    private Deque<GameStructure> initializeItems() {
        ITEMS.clear();

        if (LEVEL_MAP == 1 || LEVEL_MAP == 5) {
            ITEMS.add(new Shield());
            ITEMS.add(new Sword());
            ITEMS.add(new PotionRed());
            ITEMS.add(new PotionGreen());

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

    private Deque<GameStructure> initializeStructures() {
        STRUCTURES.clear();

        return STRUCTURES;
    }

    private Deque<GameEntity> initializeEntities() {
        ENTITIES.clear();
        ENTITIES.add(new SkeletonWKey());
        ENTITIES.add(new Boss());

        for (int i = 0; i < 2; i++) {
            ENTITIES.add(new Skeleton());

            if (LEVEL_MAP > 1) {
                for (int y = 0; y < LEVEL_MAP - 2; y++) ENTITIES.add(new Zombie());
            }
        }

        return ENTITIES;
    }

    public static void playSound(String soundFile) {
        try {
            File audioFile = new File(GameController.class.getResource("/wavs/" + soundFile).getFile());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
