package cz.coffee.rpggame.handlers;

import cz.coffee.rpggame.components.Board;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.facades.GameEngine;
import cz.coffee.rpggame.entities.Hero;
import cz.coffee.rpggame.utils.Location;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static cz.coffee.rpggame.GameConfig.TILE_SIZE;
import static java.awt.event.KeyEvent.*;

public class KeyEventHandler implements KeyListener {

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
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        var bState = board.state;


        var hero = GameEngine.getHero();
        var ts = TILE_SIZE;

        var x = hero.getLocation().getX();
        var y = hero.getLocation().getY();

        switch (e.getKeyCode()) {
            case VK_UP: {
                hero.setDirection("hero-up");
                var moveToLocation = new Location(x, y - ts);
                this.movementHandler.move(hero, moveToLocation);
                break;
            }
            case VK_DOWN: {
                hero.setDirection("hero-down");
                var moveToLocation = new Location(x, y + ts);
                this.movementHandler.move(hero, moveToLocation);
                break;
            }
            case VK_LEFT: {
                hero.setDirection("hero-left");
                var moveToLocation = new Location(x - ts, y);
                this.movementHandler.move(hero, moveToLocation);
                break;
            }
            case VK_RIGHT: {
                hero.setDirection("hero-right");
                var moveToLocation = new Location(x + ts, y);
                this.movementHandler.move(hero, moveToLocation);
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
