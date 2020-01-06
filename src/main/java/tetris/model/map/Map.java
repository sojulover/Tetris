package tetris.model.map;

import lombok.Getter;

@Getter
public class Map {

    private final int[][] tiles;

    private final int centerX;
    private final int centerY;

    public Map(int[][] tiles) {

        this.tiles = tiles;
        this.centerX = tiles.length / 2;
        this.centerY = 0;
    }

    public void printToConsole() {

        for (int y = 0; y < this.tiles.length; y++) {

            for (int x = 0; x < this.tiles[y].length; x++) {

                System.out.print(this.tiles[y][x] + " ");
            }
            System.out.println();
        }
    }
}
