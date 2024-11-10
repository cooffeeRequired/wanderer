package cz.coffee.rpggame.services;

import cz.coffee.rpggame.controllers.GameController;
import cz.coffee.rpggame.enums.GameState;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.models.Monster;
import cz.coffee.rpggame.structures.Floor;
import cz.coffee.rpggame.utils.Location;

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
            var hero = GameEngine.getHero();
            var ts = TILE_SIZE;

            var x = hero.getLocation().getX();
            var y = hero.getLocation().getY();

            switch (e.getKeyCode()) {
                case VK_UP: {
                    Board.heroDirection = "img/hero-up.png";
                    //hero.setDirection("img/hero-up.png");
                    var moveToLocation = new Location(x, y - ts);
                    this.move(hero, moveToLocation);
                    break;
                }
                case VK_DOWN: {
                    Board.heroDirection = "img/hero-down.png";
                    //hero.setDirection("img/hero-down.png");
                    var moveToLocation = new Location(x, y + ts);
                    this.move(hero, moveToLocation);
                    break;
                }
                case VK_LEFT: {
                    Board.heroDirection = "img/hero-left.png";
                    //hero.setDirection("img/hero-left.png");
                    var moveToLocation = new Location(x - ts, y);
                    this.move(hero, moveToLocation);
                    break;
                }
                case VK_RIGHT: {
                    Board.heroDirection = "img/hero-right.png";
                    //hero.setDirection("img/hero-right.png");
                    var moveToLocation = new Location(x + ts, y);
                    this.move(hero, moveToLocation);
                    break;
                }
                case VK_ENTER: {
                    if (bState.equals(GameState.START) || bState.equals(GameState.NEW_GAME)) {
                        System.out.println("Clicked START");
                        board.changeState(GameState.INSTR);
                    } else if (bState.equals(GameState.INSTR)) {
                        System.out.println("Clicked INSTR");
                        board.changeState(GameState.PLAYING);
                    }  //TODO handle new game / hero / monsters etc

                }
            }
        }
    }

    private void move(Hero hero, Location moveToLocation) {

        var x = moveToLocation.getX();
        var y = moveToLocation.getY();

        boolean canMoveThere = canMoveThere(x, y, Board.location);
        if (canMoveThere) {
            hero.getLocation().setX(x);
            hero.getLocation().setY(y);

            board.repaint();

            System.out.println("\u001B[33m[GameKeyEvents] Drawing hero: " + hero.getUuid() + " at: " + Board.location.getX() + ", " + Board.location.getY() + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m[GameKeyEvents] Drawing hero: " + hero.getUuid() + " at: ! Cannot move there\u001B[0m");
        }
    }

    private boolean canMoveThere(int x, int y, Location heroLocation) {
        int row = y / TILE_SIZE;
        int colm = x / TILE_SIZE;

        int heroX = heroLocation.getX();
        int heroY = heroLocation.getY();

        if (x == heroX && y == heroY) {
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
            if (monster.getLocation().getX() == x && monster.getLocation().getY() == y) {
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
