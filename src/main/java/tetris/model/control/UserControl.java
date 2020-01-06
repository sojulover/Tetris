package tetris.model.control;

public enum UserControl implements Control {

    blockDown,
    blockLeft,
    blockRight,
    blockHit,
    blockRotate,

    ;

    @Override
    public boolean isValid() {
        return false;
    }
}
