package com.xoxoms.tetris.server.model;

import lombok.Getter;
import lombok.ToString;
import com.xoxoms.tetris.server.model.map.Map;

@Getter
@ToString
public class NormalGame extends AbstractGame {

    public NormalGame(Map map) {

        super(map);
    }
}
