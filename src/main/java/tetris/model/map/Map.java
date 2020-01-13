package tetris.model.map;

import lombok.Getter;

@Getter
public class Map {

    private int[][] tiles;

    private final int centerX;
    private final int centerY;

    public Map(int[][] tiles) {

        this.tiles = tiles;
        this.centerX = tiles[0].length / 2;
        this.centerY = 0;
    }

    public boolean isEmpty(int toX, int toY, int[][] toForm) {

        for (int y = 0; y < toForm.length; y++) {

            if ((toY + y) < 0) {

                continue;
            }

            for (int x = 0; x < toForm[0].length; x++) {

                boolean shouldCheck = toForm[y][x] > 0;
                boolean isFilled = isFilled(x + toX, y + toY);

                if (shouldCheck && isFilled) {

                    return false;
                }
            }
        }

        return true;
    }

    public synchronized void setTilesSyncronized(int[][] newTiles) {

        this.tiles = newTiles;
    }

    private boolean isFilled(int x, int y) {

        return this.tiles[y][x] > 0;
    }

    public int getHeight() {

        return this.getTiles().length;
    }
}
