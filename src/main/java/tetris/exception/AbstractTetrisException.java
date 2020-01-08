package tetris.exception;

public abstract class AbstractTetrisException extends Exception {

    private TetrisExceptionTypes type;

    public AbstractTetrisException(TetrisExceptionTypes type) {

        super();

        this.type = type;
    }
}
