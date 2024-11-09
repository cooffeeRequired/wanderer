package cz.coffee.rpggame.services;

import cz.coffee.rpggame.components.FPSCounter;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;

import javax.swing.*;

public class GameEngine implements Runnable {
    private Thread thread;
    public GameState state = GameState.STOPPED;
    public static Board board;

    public void start() {
        this.run();
    }

    @Override
    public void run() {
        state = GameState.RUNNING;

        thread = new Thread(() -> {
            JFrame frame = new JFrame("Wanderer RPG");
            board = new Board(new FPSCounter(), new Hero());

            frame.add(board);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
            frame.addKeyListener(new GameKeyEvents(this board));

            board.changeState(GameState.START);



//            while (state == GameState.RUNNING) {
//                board.repaint();
//            }
        });
        thread.start();
    }

    public void stop() {
        if (state.equals(GameState.RUNNING) && thread.isAlive()) {
            thread.interrupt();
        }
    }
}
