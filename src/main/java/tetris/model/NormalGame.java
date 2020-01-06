package tetris.model;

import lombok.Getter;
import lombok.ToString;
import tetris.model.map.Block;
import tetris.model.map.Map;

@Getter
@ToString
public class NormalGame implements Game {

    private final Map map;

    private Blocks blocks;
    private Position position;

    public NormalGame(Map map) {

        this.map = map;

        this.blocks = new Blocks();
        this.position = Position.newCenter(this.map);
    }

    @Getter
    @ToString
    private class Blocks {

        private Block currentBlock;
        private Block nextBlock;

        private Blocks() {

            this.currentBlock = Block.newRandom();
            this.nextBlock = Block.newRandom();
        }
    }

    @Getter
    @ToString
    private static class Position {

        private int x;
        private int y;

        private Position(int x, int y) {

            this.x = x;
            this.y = y;
        }

        private static Position newCenter(Map map) {

            return new Position(map.getCenterX(), map.getCenterY());
        }
    }
}
