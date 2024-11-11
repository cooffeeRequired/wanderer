package cz.coffee.structures;

import cz.coffee.GameConfig;
import cz.coffee.components.Board;
import cz.coffee.facades.GameEngine;
import cz.coffee.utils.PositionedImage;
import lombok.Getter;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {

    public static boolean isFloorDrown = false;
    public static boolean isWallsDrown = false;

    @Getter private static HashMap<List<Integer>, PositionedImage> floor = new HashMap<>();
    @Getter private static HashMap<List<Integer>, PositionedImage> walls = new HashMap<>();



    public static void drawExistingFloor(Graphics graphics) {
        checkExists(graphics, isFloorDrown, floor);
    }

    public static void drawExistingWalls(Graphics graphics) {
        checkExists(graphics, isWallsDrown, walls);
    }

    private static void checkExists(Graphics graphics, boolean exist, HashMap<List<Integer>, PositionedImage> walls) {
        if (exist) {
            for (Map.Entry<List<Integer>, PositionedImage> entry : walls.entrySet()) {
                var location = entry.getKey();
                var img = entry.getValue();

                if (!location.isEmpty()) {
                    img.setPosX(location.getFirst());
                    img.setPosY(location.get(1));
                    img.draw(graphics);
                }
            }
        }
    }


    public static void drawFloor(Graphics graphics, int tiles, int imgPixels) {

        for (int i = 0; i < tiles; i++) {
            for (int j = 0; j < tiles; j++) {
                PositionedImage tile = GameEngine.getTiles().get("floor");
                tile.setPosX(j * imgPixels);
                tile.setPosY(i * imgPixels);
                tile.draw(graphics);

                floor.put(List.of(j * imgPixels, i * imgPixels), tile);
            }
        }

        isFloorDrown = true;
    }


    public static void drawWalls(Graphics graphics, int tiles, int imgPixels) {

        for (int i = 0; i < tiles; i++) {
            for (int j = 0; j < tiles; j++) {
                if (wallMatrix(Board.LEVEL_MAP)[j][i]) {
                    PositionedImage wall = GameEngine.getTiles().get("wall");
                    wall.setPosX(j * imgPixels);
                    wall.setPosY(i * imgPixels);
                    wall.draw(graphics);

                    walls.put(List.of(j * imgPixels, i * imgPixels), wall);
                }

            }
        }

        isWallsDrown = true;
    }

    public static boolean[][] wallMatrix(int type) {
        try {
            boolean[][] wallMatrix = new boolean[GameConfig.TILE_SIZE][GameConfig.TILES];
            if (type % 4 == 1) {
                wallMatrix[0][3] = true;
                wallMatrix[0][4] = true;
                wallMatrix[1][4] = true;
                wallMatrix[2][4] = true;
                wallMatrix[2][5] = true;

                wallMatrix[7][8] = true;
                wallMatrix[7][9] = true;
                wallMatrix[8][8] = true;
                wallMatrix[8][9] = true;

                wallMatrix[5][5] = true;
                wallMatrix[5][6] = true;
                wallMatrix[5][7] = true;
                wallMatrix[6][3] = true;


                wallMatrix[7][2] = true;
                wallMatrix[7][3] = true;
                wallMatrix[7][4] = true;

                wallMatrix[3][1] = true;
                wallMatrix[4][1] = true;

                wallMatrix[2][0] = true;
                wallMatrix[2][1] = true;

                wallMatrix[4][8] = true;
                wallMatrix[4][9] = true;

                wallMatrix[8][6] = true;
                wallMatrix[9][6] = true;

                wallMatrix[1][9] = true;
                wallMatrix[2][9] = true;
                wallMatrix[2][8] = true;

                wallMatrix[8][1] = true;
                wallMatrix[8][2] = true;
                wallMatrix[9][2] = true;

            }

            if (type % 4 == 2) {
                wallMatrix[0][3] = true;
                wallMatrix[0][4] = true;
                wallMatrix[0][5] = true;
                wallMatrix[1][4] = true;
                wallMatrix[1][5] = true;
                wallMatrix[2][4] = true;
                wallMatrix[1][6] = true;
                wallMatrix[0][6] = true;
                wallMatrix[0][7] = true;
                wallMatrix[2][5] = true;
                wallMatrix[2][6] = true;

                wallMatrix[7][8] = true;
                wallMatrix[8][8] = true;

                wallMatrix[7][6] = true;
                wallMatrix[7][7] = true;
                wallMatrix[7][3] = true;


                wallMatrix[7][2] = true;
                wallMatrix[6][3] = true;
                wallMatrix[7][4] = true;

                wallMatrix[3][1] = true;
                wallMatrix[4][1] = true;

                wallMatrix[4][4] = true;
                wallMatrix[4][5] = true;
                wallMatrix[5][5] = true;

                wallMatrix[3][0] = true;

                wallMatrix[5][8] = true;
                wallMatrix[5][9] = true;

                wallMatrix[8][6] = true;
                wallMatrix[9][6] = true;

                wallMatrix[1][9] = true;
                wallMatrix[2][9] = true;

                wallMatrix[8][1] = true;
                wallMatrix[8][2] = true;
                wallMatrix[9][2] = true;

            }

            if (type % 4 == 3) {
                wallMatrix[1][0] = true;
                wallMatrix[2][1] = true;
                wallMatrix[3][2] = true;
                wallMatrix[4][3] = true;

                wallMatrix[6][5] = true;

                wallMatrix[6][6] = true;
                wallMatrix[6][7] = true;
                wallMatrix[7][6] = true;
                wallMatrix[7][7] = true;

                wallMatrix[2][8] = true;
                wallMatrix[3][8] = true;

                wallMatrix[6][2] = true;
                wallMatrix[6][3] = true;
                wallMatrix[7][2] = true;
                wallMatrix[7][3] = true;

                wallMatrix[0][4] = true;
                wallMatrix[0][5] = true;
                wallMatrix[0][6] = true;
                wallMatrix[0][7] = true;
                wallMatrix[0][8] = true;
                wallMatrix[0][9] = true;

                wallMatrix[2][0] = true;
                wallMatrix[3][1] = true;
                wallMatrix[4][2] = true;

                wallMatrix[8][7] = true;
                wallMatrix[7][8] = true;

                wallMatrix[2][5] = true;
                wallMatrix[2][6] = true;
                wallMatrix[2][7] = true;
                wallMatrix[4][8] = true;
                wallMatrix[4][9] = true;
                wallMatrix[7][1] = true;
                wallMatrix[8][3] = true;


                wallMatrix[8][8] = true;
                wallMatrix[9][8] = true;
                wallMatrix[9][9] = true;


                wallMatrix[9][0] = true;
                wallMatrix[8][0] = true;
                wallMatrix[9][1] = true;
                wallMatrix[8][1] = true;


            }

            if (type % 4 == 0) {
                wallMatrix[0][2] = true;
                wallMatrix[0][3] = true;
                wallMatrix[1][3] = true;

                wallMatrix[6][6] = true;
                wallMatrix[6][7] = true;
                wallMatrix[7][6] = true;
                wallMatrix[7][7] = true;

                wallMatrix[9][0] = true;
                wallMatrix[9][1] = true;
                wallMatrix[9][2] = true;
                wallMatrix[9][3] = true;
                wallMatrix[9][4] = true;

                wallMatrix[5][5] = true;
                wallMatrix[6][5] = true;
                wallMatrix[8][7] = true;

                wallMatrix[4][0] = true;
                wallMatrix[5][0] = true;
                wallMatrix[4][1] = true;
                wallMatrix[5][1] = true;
                wallMatrix[5][2] = true;
                wallMatrix[6][1] = true;

                wallMatrix[0][6] = true;
                wallMatrix[1][6] = true;
                wallMatrix[2][6] = true;
                wallMatrix[3][6] = true;
                wallMatrix[3][7] = true;
                wallMatrix[3][8] = true;
                wallMatrix[0][9] = true;
                wallMatrix[0][7] = true;
                wallMatrix[0][8] = true;

                wallMatrix[6][2] = true;
                wallMatrix[6][3] = true;
                wallMatrix[7][2] = true;
                wallMatrix[7][3] = true;

                wallMatrix[8][8] = true;
                wallMatrix[9][8] = true;
                wallMatrix[9][9] = true;
            }
            return wallMatrix;
        } catch (Exception e) {
            return new boolean[GameConfig.TILE_SIZE][GameConfig.TILES];
        }
    }

}
