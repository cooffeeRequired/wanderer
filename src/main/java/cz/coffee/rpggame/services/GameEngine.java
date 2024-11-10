package cz.coffee.rpggame.services;

import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;
import lombok.Getter;

import javax.swing.*;

public class GameEngine {
    public GameState state = GameState.STOPPED;

    @Getter private static Hero hero;
    @Getter private static Board board;
    @Getter private static GameEngine engine;

    public void run() {
        state = GameState.RUNNING;

        hero = new Hero();
        board = new Board(new FPSCounter(), hero);

        JFrame frame = new JFrame("Wanderer RPG");
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(new GameKeyEvents(this, board));

        board.changeState(GameState.START);
    }

    public void stop() {
        if (state.equals(GameState.RUNNING)) {
            state = GameState.STOPPED;
        }
    }
}
