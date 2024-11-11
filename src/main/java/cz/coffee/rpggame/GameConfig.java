package cz.coffee.rpggame;

import cz.coffee.rpggame.facades.GameEntity;
import cz.coffee.rpggame.structures.GameStructure;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class GameConfig {
    public static final int GAME_WINDOW_WIDTH = 720;
    public static final int GAME_WINDOW_HEIGHT = 720;
    public static final int TILE_SIZE = 72;
    public static final int TILES = 10;

    public static final double FPS = 60;

    public static final Deque<GameEntity> ENTITIES = new ArrayDeque<>();
    public static final Deque<GameStructure> ITEMS = new ArrayDeque<>();
    public static final Deque<GameStructure> STRUCTURES = new ArrayDeque<>();
}
