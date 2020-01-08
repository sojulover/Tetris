package tetris.model.map;

import lombok.Getter;

@Getter
public class Map {

    private final int[][] tiles;

    private final int centerX;
    private final int centerY;

    public Map(int[][] tiles) {

        this.tiles = tiles;
        this.centerX = tiles[0].length / 2;
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

    public boolean isEmpty(int toX, int toY, int[][] toForm) {


        for (int y = 0; y < toForm.length; y++) {


            for (int x = 0; x < toForm[0].length; x++) {

                boolean shouldCheck = toForm[y][x] > 0;
                boolean isFilled = isFilled(y + toY, x + toX);

                if (shouldCheck && isFilled) {

                    return false;
                }
            }
        }

        return true;
    }

    public boolean isFilled(int x, int y) {

        return this.tiles[y][x] > 0;
    }
}
