package tetris.model;

import lombok.Getter;
import lombok.ToString;
import tetris.model.map.Map;

@Getter
@ToString
public class NormalGame extends AbstractGame {

    public NormalGame(Map map) {

        super(map);
    }
}
