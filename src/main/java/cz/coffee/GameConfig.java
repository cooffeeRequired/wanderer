package cz.coffee;

import cz.coffee.facades.GameEntity;
import cz.coffee.facades.GameStructure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The {@code GameConfig} class holds the configuration constants for the game.
 * It includes settings for window dimensions, tile size, frame rate, and
 * collections for game entities, items, and structures.
 */
public abstract class GameConfig {

    /**
     * The width of the game window in pixels.
     */
    public static final int GAME_WINDOW_WIDTH = 720;

    /**
     * The height of the game window in pixels.
     */
    public static final int GAME_WINDOW_HEIGHT = 720;

    /**
     * The size of each tile in pixels.
     */
    public static final int TILE_SIZE = 72;

    /**
     * The number of tiles in the game grid.
     */
    public static final int TILES = 10;

    /**
     * The frames per second (FPS) for the game.
     */
    public static final double FPS = 60;

    /**
     * A deque to hold all game entities.
     */
    public static final Deque<GameEntity> ENTITIES = new ArrayDeque<>();

    /**
     * A deque to hold all game items.
     */
    public static final Deque<GameStructure> ITEMS = new ArrayDeque<>();

    /**
     * A deque to hold all game structures.
     */
    public static final Deque<GameStructure> STRUCTURES = new ArrayDeque<>();


    /**
     * Indicates whether smooth moving is enabled in the game.
     * This affects the way entities move across the game grid.
     */
    public static final boolean SMOOTH_MOVING = false;
}
