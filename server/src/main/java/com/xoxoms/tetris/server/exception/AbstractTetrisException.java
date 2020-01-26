package com.xoxoms.tetris.server.exception;

public abstract class AbstractTetrisException extends Exception {

    private TetrisExceptionTypes type;

    public AbstractTetrisException(TetrisExceptionTypes type) {

        super(String.format("%d", type.getSerialNumber()));

        this.type = type;
    }
}
