package cz.coffee.facades;

import cz.coffee.components.Board;
import cz.coffee.components.FPSCounter;
import cz.coffee.entities.Hero;
import cz.coffee.enums.GameState;
import cz.coffee.handlers.KeyEventHandler;
import cz.coffee.utils.PositionedImage;
import lombok.Getter;

import javax.swing.*;
import java.util.WeakHashMap;

public class GameEngine {
    @Getter private static Hero hero;
    @Getter private static Board board;
    @Getter private static GameEngine engine;

    @Getter private static WeakHashMap<String, PositionedImage> tiles = new WeakHashMap<>();

    Thread gameLoop;


    public void start() {
        final JFrame mainWindow = new JFrame("Wanderer RPG");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);

        initializeTiles();

        hero = new Hero();
        board = new Board(new FPSCounter(), hero);

        board.changeState(GameState.RUNNING);

        gameLoop = board.getGameLoop();

        mainWindow.add(board);
        mainWindow.addKeyListener(new KeyEventHandler(this, board, hero));
        mainWindow.pack();

        board.changeState(GameState.NEW_GAME);
    }


    @SuppressWarnings("unused")
    public void stop() {
        this.gameLoop.interrupt();
    }

    private void initializeTiles() {
        var floorTile = new PositionedImage("img/tiles/floor.png", true);
        var wallTile = new PositionedImage("img/tiles/wall.png", true);

        var heroUpTile = new PositionedImage("img/hero/u.png", true);
        var heroDownTile = new PositionedImage("img/hero/d.png", true);
        var heroLeftTile = new PositionedImage("img/hero/l.png", true);
        var heroRightTile = new PositionedImage("img/hero/r.png", true);

        var redPotionTile = new PositionedImage("img/items/potionRed.png", true);
        var greenPotionTile = new PositionedImage("img/items/potionGreen.png", true);
        var shieldTile = new PositionedImage("img/items/shield.png", true);
        var swordTile = new PositionedImage("img/items/sword.png", true);

        var zombieTile = new PositionedImage("img/monsters/zombie.png", true);
        var skeletonTile = new PositionedImage("img/monsters/skeleton.png", true);
        var bosseTile = new PositionedImage("img/monsters/boss.png", true);
        var skeletonWithKeyTile = new PositionedImage("img/monsters/skeleton_w.png", true);

        var doorTile = new PositionedImage("img/items/door.png", true);
        var finalDoorTile = new PositionedImage("img/items/finalDoor.png", true);
        var zombieEggTile = new PositionedImage("img/monsters/zombieEgg.png", true);

        var boomTile = new PositionedImage("img/tiles/boom.png", true);


        GameEngine.tiles.put("floor", floorTile);
        GameEngine.tiles.put("wall", wallTile);

        GameEngine.tiles.put("hero-up", heroUpTile);
        GameEngine.tiles.put("hero-down", heroDownTile);
        GameEngine.tiles.put("hero-left", heroLeftTile);
        GameEngine.tiles.put("hero-right", heroRightTile);

        GameEngine.tiles.put("red-pot", redPotionTile);
        GameEngine.tiles.put("green-pot", greenPotionTile);
        GameEngine.tiles.put("shield", shieldTile);
        GameEngine.tiles.put("sword", swordTile);

        GameEngine.tiles.put("zombie", zombieTile);
        GameEngine.tiles.put("skeleton", skeletonTile);
        GameEngine.tiles.put("boss", bosseTile);
        GameEngine.tiles.put("skeleton_w", skeletonWithKeyTile);

        GameEngine.tiles.put("door", doorTile);
        GameEngine.tiles.put("finalDoor", finalDoorTile);
        GameEngine.tiles.put("zombieEgg", zombieEggTile);

        GameEngine.tiles.put("boom", boomTile);

    }
}
