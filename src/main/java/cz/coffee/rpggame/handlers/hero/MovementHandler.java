package cz.coffee.rpggame.handlers.hero;

import cz.coffee.rpggame.controllers.GameController;
import cz.coffee.rpggame.models.Boss;
import cz.coffee.rpggame.models.GameEntity;
import cz.coffee.rpggame.models.Hero;
import cz.coffee.rpggame.models.Monster;
import cz.coffee.rpggame.services.Board;
import cz.coffee.rpggame.structures.*;
import cz.coffee.rpggame.utils.Location;

import static cz.coffee.rpggame.GameConfig.TILE_SIZE;

public class MovementHandler {

    private final Board board;
    private final EntityHandler entityHandler;
    private final ItemHandler itemHandler;
    private final AttributeHandler attributeHandler;

    public MovementHandler(Board board) {
        this.board = board;
        this.entityHandler = new EntityHandler();
        this.itemHandler = new ItemHandler();
        this.attributeHandler = new AttributeHandler();
    }


    public void move(GameEntity entity, Location moveToLocation) {

        var x = moveToLocation.getX();
        var y = moveToLocation.getY();

        boolean canMoveThere = canMoveThere(x, y, entity.getLocation(), entity);
        if (canMoveThere) {
            entity.getLocation().setX(x);
            entity.getLocation().setY(y);
            board.repaint();
            System.out.println("\u001B[33m[MovementHandler] Drawing hero: " + entity.getUuid() + " at: " + entity.getLocation().getX() + ", " + entity.getLocation().getY() + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m[MovementHandler] Drawing hero: " + entity.getUuid() + " at: ! Cannot move there\u001B[0m");
        }
    }

    protected boolean canMoveThere(int x, int y, Location location, GameEntity entity) {
        int row = y / TILE_SIZE;
        int colm = x / TILE_SIZE;

        int heroX = location.getX();
        int heroY = location.getY();

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
                if (monster instanceof Boss) {
                    System.out.println("\u001B[36m[MovementHandler] Drawing hero: " + entity.getUuid() + " at: ! Cannot move there, BOSS FIGHT\u001B[0m");
                } else {
                    System.out.println("\u001B[36m[MovementHandler] Drawing hero: " + entity.getUuid() + " at: ! Cannot move there, MONSTER! \u001B[0m");
                }
                // TODO proceed an boss fight / needed fight UI / BOSS UI
                return false;
            }
        }

        if (entity instanceof Hero hero ) {
            for (GameStructure mapObject : GameController.getStructures().getStructures()) {
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
            }
            return true;
        }
        return false;
    }
}
