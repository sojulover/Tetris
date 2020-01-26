package com.xoxoms.tetris.server.model.control;

import lombok.Getter;

public enum UserControl implements Control {

    blockDown(true),
    blockLeft(true),
    blockRight(true),
    blockHit(false),
    blockRotate(false),

    ;

    @Getter
    private boolean moving;

    UserControl(boolean moving) {
        this.moving = moving;
    }
}
