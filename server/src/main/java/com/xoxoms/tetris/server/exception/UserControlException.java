package com.xoxoms.tetris.server.exception;

public class UserControlException extends AbstractTetrisException {

    private UserControlException(TetrisExceptionTypes type) {

        super(type);
    }

    public static class Builder {

        private final TetrisExceptionTypes type;

        public Builder(TetrisExceptionTypes type) {

            this.type = type;
        }

        public UserControlException build() {

            return new UserControlException(this.type);
        }
    }
}
