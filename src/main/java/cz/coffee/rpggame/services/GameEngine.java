package cz.coffee.rpggame.services;

import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;
import lombok.Getter;

import javax.swing.*;

public class GameEngine {
    @Getter private static Hero hero;
    @Getter private static Board board;
    @Getter private static GameEngine engine;

    public void run() {
        hero = new Hero();
        board = new Board(new FPSCounter(), hero);

        board.changeState(GameState.RUNNING);

        JFrame frame = new JFrame("Wanderer RPG");
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(new GameKeyEvents(this, board, hero));

        board.changeState(GameState.NEW_GAME);
    }

    public void stop() {
        if (board.state.equals(GameState.RUNNING)) {
            board.changeState(GameState.STOPPED);
        }
    }
}
