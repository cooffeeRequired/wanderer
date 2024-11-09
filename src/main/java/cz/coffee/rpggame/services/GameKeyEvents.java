package cz.coffee.rpggame.services;

import cz.coffee.rpggame.controllers.GameController;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.models.Monster;
import cz.coffee.rpggame.structures.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static cz.coffee.rpggame.GameConfig.TILE_SIZE;
import static java.awt.event.KeyEvent.*;

public class GameKeyEvents implements KeyListener {

    protected final GameEngine engine;
    protected final Board board;

    public GameKeyEvents(final GameEngine engine,Board board) {
        this.engine = engine;
        this.board = board;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (engine.state.equals(GameState.RUNNING) || engine.state.equals(GameState.PLAYING)) {

            var bState = board.state;

            switch (e.getKeyCode()) {
                case VK_UP: {
                    this.moveUp();
                    break;
                }
                case VK_DOWN: {
                    this.moveDown();
                    break;
                }
                case VK_LEFT: {
                    this.moveLeft();
                    break;
                }
                case VK_RIGHT: {
                    this.moveRight();
                    break;
                }
                case VK_ENTER: {
                    if (bState.equals(GameState.START) || bState.equals(GameState.NEW_GAME)) {
                        System.out.println("Clicked START");
                        board.changeState(GameState.INSTR);
                    } else if (bState.equals(GameState.INSTR)) {
                        System.out.println("Clicked INSTR");
                        board.changeState(GameState.PLAYING);
                    } else if (bState.equals(GameState.GAME_OVER)) {
                        //TODO handle new game / hero / monsters etc
                    }
                }
            }
        }
    }

    public void moveUp() {
        Board.heroDir = "img/hero-up.png";
        var y = Board.heroY;
        y -= TILE_SIZE;
        this.move(Board.heroX, y);
    }

    public void moveDown() {
        Board.heroDir = "img/hero-down.png";
        var y = Board.heroY;
        y += TILE_SIZE;
        this.move(Board.heroX, y);
    }


    public void moveRight() {
        Board.heroDir = "img/hero-right.png";
        var x = Board.heroX;
        x += TILE_SIZE;
        this.move(x, Board.heroY);
    }

    public void moveLeft() {
        Board.heroDir= "img/hero-left.png";
        var x = Board.heroX;
        x -= TILE_SIZE;
        this.move(x, Board.heroY);
    }

    private void move(int x, int y) {
        boolean g = canMoveThere(x, y);
        if (g) {
            Board.heroX= x;
            Board.heroY= y;

            board.repaint();

            System.out.println("\u001B[33m[GameKeyEvents] Drawing hero: " + hero.getUuid() + " at: " + Board.heroX + ", " + Board.heroY + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m[GameKeyEvents] Drawing hero: " + hero.getUuid() + " at: ! Cannot move there\u001B[0m");
        }
    }

    private boolean canMoveThere(int x, int y) {
        int row = y / TILE_SIZE;
        int colm = x / TILE_SIZE;

        if (x == hero.getPosX() && y == hero.getPosY()) {
            return false;
        }

        try {
            if (Floor.wallMatrix(Board.LEVEL_MAP)[colm][row]) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        for (Monster monster : GameController.getEntities().getMonsters()) {
            if (monster.getPosX() == x && monster.getPosY() == y) {
                return true;
            }
        }

/*        for (GameStructure mapObject : GameController.getStructures().getStructures()) {
            if (mapObject.getPositionX() == x && mapObject.getPositionY() == y) {
                if (mapObject instanceof PotionRed) {
                    hero.redPotion();
                }
                if (mapObject instanceof PotionGreen) {
                    hero.greenPotion();
                }
                if (mapObject instanceof Shield) {
                    hero.gotShield();
                }
                if (mapObject instanceof Sword) {
                    hero.gotSword();
                }
                GameController.getStructures().getStructures().remove(mapObject);
                return true;
            }
        }*/
        return true;
    }
}
