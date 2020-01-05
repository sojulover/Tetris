package tetris.model.map;

import lombok.Getter;

public class Map {

    @Getter
    private final int[][] tiles;

    public Map(int[][] tiles) {

        this.tiles = tiles;
    }
}
