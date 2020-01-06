package tetris.model;

import org.junit.jupiter.api.Test;
import tetris.model.map.Map;
import tetris.model.map.SimpleMapFactory;

import static org.junit.jupiter.api.Assertions.*;

class NormalGameTest {

    @Test
    void testInitNormalGame() {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());
        Map map = normalGame.getMap();
        map.printToConsole();
    }
}