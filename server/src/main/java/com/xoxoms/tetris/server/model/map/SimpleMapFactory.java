package com.xoxoms.tetris.server.model.map;

import com.xoxoms.tetris.server.configuration.TetrisProperties;

public class SimpleMapFactory implements MapFactory {

    private final int height;
    private final int width;


    public SimpleMapFactory() {

        this.height = TetrisProperties.getInstance().getIntegerProperty(TetrisProperties.Keys.map_single_heightSize);
        this.width = TetrisProperties.getInstance().getIntegerProperty(TetrisProperties.Keys.map_single_widthSize);
    }

    @Override
    public Map create() {

        return new Map(new int[height][width]);
    }
}
