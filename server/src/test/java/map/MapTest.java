package map;

import com.xoxoms.tetris.server.model.map.Map;
import com.xoxoms.tetris.server.model.map.SimpleMapFactory;
import org.junit.jupiter.api.Test;

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
