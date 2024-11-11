package cz.coffee.rpggame.facades;

import cz.coffee.rpggame.components.Board;
import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.handlers.KeyEventHandler;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.utils.PositionedImage;
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


    public void stop() {
        this.gameLoop.interrupt();
    }

    private void initializeTiles() {
        PositionedImage floorTile = new PositionedImage("img/tiles/floor.png", true);
        PositionedImage wallTile = new PositionedImage("img/tiles/wall.png", true);

        PositionedImage heroUpTile = new PositionedImage("img/hero/hero-up.png", true);
        PositionedImage heroDownTile = new PositionedImage("img/hero/hero-down.png", true);
        PositionedImage heroLeftTile = new PositionedImage("img/hero/hero-left.png", true);
        PositionedImage heroRightTile = new PositionedImage("img/hero/hero-right.png", true);

        PositionedImage redPotionTile = new PositionedImage("img/items/potionRed.png", true);
        PositionedImage greenPotionTile = new PositionedImage("img/items/potionGreen.png", true);
        PositionedImage shieldTile = new PositionedImage("img/items/shield.png", true);
        PositionedImage swordTile = new PositionedImage("img/items/sword.png", true);

        PositionedImage zombieTile = new PositionedImage("img/monsters/zombie.png", true);
        var skeletonTile = new PositionedImage("img/monsters/skeleton.png", true);
        var bosseTile = new PositionedImage("img/monsters/boss.png", true);
        var skeletonWithKeyTile = new PositionedImage("img/monsters/skeleton_w.png", true);

        var doorTile = new PositionedImage("img/items/door.png", true);
        var finalDoorTile = new PositionedImage("img/items/finalDoor.png", true);
        var zombieEggTile = new PositionedImage("img/monsters/zombieEgg.png", true);


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



    }
}
