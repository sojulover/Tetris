package tetris.model.control;

import lombok.Getter;
import lombok.ToString;
import tetris.model.map.Block;
import tetris.model.map.Map;

@ToString
public class Position {

    private final Map map;

    @Getter
    private int x;
    @Getter
    private int y;

    private Position(Map map, Block currentBlock) {

        this.map = map;

        final int leftWeight = currentBlock.getForm()[0].length / 2 + currentBlock.getForm()[0].length % 2;
        this.x = map.getCenterX() - leftWeight;
        this.y = map.getCenterY() - currentBlock.getForm().length;
    }

    public void move(int toX, int toY) {

        this.x = toX;
        this.y = toY;
    }

    public static Position newPosition(Map map, Block currentBlock) {

        return new Position(map, currentBlock);
    }
}
