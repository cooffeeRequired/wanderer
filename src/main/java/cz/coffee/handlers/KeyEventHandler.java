package cz.coffee.handlers;

import cz.coffee.GameConfig;
import cz.coffee.components.Board;
import cz.coffee.entities.Hero;
import cz.coffee.enums.GameState;
import cz.coffee.facades.GameEngine;
import cz.coffee.utils.Location;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static cz.coffee.GameConfig.TILE_SIZE;
import static java.awt.event.KeyEvent.*;

public class KeyEventHandler implements KeyListener {

    public static boolean upPressed = false;
    public static boolean downPressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;


    protected final GameEngine engine;
    protected final Board board;
    protected final Hero hero;
    protected final MovementHandler movementHandler;

    public KeyEventHandler(final GameEngine engine, Board board, Hero hero) {
        this.engine = engine;
        this.board = board;
        this.hero = hero;
        this.movementHandler = new MovementHandler(board);
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameConfig.SMOOTH_MOVING) {
            switch (e.getKeyCode()) {
                case VK_UP: {
                    hero.setDirection("hero-up");
                    upPressed = true;
                    break;
                }
                case VK_DOWN: {
                    hero.setDirection("hero-down");
                    downPressed = true;
                    break;
                }
                case VK_LEFT: {
                    hero.setDirection("hero-left");
                    leftPressed = true;
                    break;
                }
                case VK_RIGHT: {
                    hero.setDirection("hero-right");
                    rightPressed = true;
                    break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var bState = board.state;

        var hero = GameEngine.getHero();
        var ts = TILE_SIZE;

        var x = hero.getLocation().getX();
        var y = hero.getLocation().getY();

        switch (e.getKeyCode()) {
            case VK_UP: {
                if (GameConfig.SMOOTH_MOVING) {
                    upPressed = false;
                } else {
                    hero.setDirection("hero-up");
                    this.movementHandler.move(hero, new Location(x, y - ts));
                }
                break;
            }
            case VK_DOWN: {
                if (GameConfig.SMOOTH_MOVING) {
                    downPressed = false;
                } else {
                    hero.setDirection("hero-down");
                    this.movementHandler.move(hero, new Location(x, y  + ts));
                }
                break;
            }
            case VK_LEFT: {
                if (GameConfig.SMOOTH_MOVING) {
                    leftPressed = false;
                } else {
                    hero.setDirection("hero-left");
                    this.movementHandler.move(hero, new Location(x - ts, y));
                }
                break;
            }
            case VK_RIGHT: {
                if (GameConfig.SMOOTH_MOVING) {
                    rightPressed = false;
                } else {
                    hero.setDirection("hero-right");
                    this.movementHandler.move(hero, new Location(x + ts, y));
                }
                break;
            }
            case VK_ENTER: {
                switch (bState) {
                    case NEW_GAME:
                        board.changeState(GameState.INSTR);
                        break;
                    case INSTR:
                        board.changeState(GameState.INITIALIZED);
                        break;
                    default: {

                    }

                }
            }
        }

    }
}
