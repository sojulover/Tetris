package tetris.exception;

import lombok.Getter;

public enum UserControlExceptionTypes implements TetrisExceptionTypes {

    blocked(1000),
    wrongControl(1001),
    gameOver(1002);

    @Getter
    private int serialNumber;

    UserControlExceptionTypes(int serialNumber) {

        this.serialNumber = serialNumber;
    }
}
