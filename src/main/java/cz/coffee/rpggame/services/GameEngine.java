package cz.coffee.rpggame.services;

import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.enums.GameState;
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
        mainWindow.addKeyListener(new GameKeyEvents(this, board, hero));
        mainWindow.pack();

        board.changeState(GameState.NEW_GAME);
    }


    public void stop() {
        this.gameLoop.interrupt();
    }

    private void initializeTiles() {
        PositionedImage floorTile = new PositionedImage("img/floor.png", true);
        PositionedImage wallTile = new PositionedImage("img/wall.png", true);

        GameEngine.tiles.put("floor", floorTile);
        GameEngine.tiles.put("wall", wallTile);

    }
}
