package cz.coffee.handlers;

import cz.coffee.components.Board;
import cz.coffee.facades.GameEntity;
import cz.coffee.structures.Floor;
import cz.coffee.utils.Console;
import cz.coffee.utils.Location;

import static cz.coffee.GameConfig.TILE_SIZE;

@SuppressWarnings("all")
public class MovementHandler {


    private final Board board;
    private final EntityHandler entityHandler;
    private final ItemHandler itemHandler;

    public MovementHandler(Board board) {
        this.board = board;
        this.entityHandler = new EntityHandler();
        this.itemHandler = new ItemHandler();
    }


    public void move(GameEntity entity, Location moveToLocation) {
        boolean canMoveThere = canMoveThere(moveToLocation.getX(), moveToLocation.getY(), entity.getLocation(), entity);
        if (canMoveThere) {
            entity.setLocation(moveToLocation);
            Console.printlnVia("mv-handler", String.format("entity{%s} at X: %d, Y: %d", entity.getUuid(), entity.getLocation().getX(), entity.getLocation().getY()));
        }
    }

    protected boolean canMoveThere(int x, int y, Location location, GameEntity entity) {
        int row = y / TILE_SIZE;
        int colm = x / TILE_SIZE;

        int heroX = location.getX();
        int heroY = location.getY();

        if (x == heroX && y == heroY) return false;
        if (Floor.wallMatrix(Board.LEVEL_MAP)[colm][row]) return false;


        return this.entityHandler.handle(entity) && this.itemHandler.handle(entity);
    }
}
