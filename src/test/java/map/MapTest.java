package map;

import org.junit.jupiter.api.Test;
import tetris.model.map.Map;
import tetris.model.map.SimpleMapFactory;

class MapTest {

    @Test
    void testPrintMap() {

        Map map = new SimpleMapFactory().create();

        int[][] tiles = map.getTiles();
        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[i].length; j++) {

                System.out.print("* ");
            }

            System.out.println();
        }
    }
}