package tetris.model.control;

import lombok.Getter;
import lombok.ToString;
import tetris.model.map.Map;

@ToString
public class Position {

    private final Map map;

    @Getter
    private int x;
    @Getter
    private int y;

    public Position(Map map) {

        this.map = map;
        this.x = map.getCenterX();
        this.y = map.getCenterY();
    }

    public void move(int toX, int toY) {

        this.x = toX;
        this.y = toY;
    }
}
