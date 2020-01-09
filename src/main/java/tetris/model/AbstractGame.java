package tetris.model;

import lombok.Getter;
import lombok.ToString;
import tetris.exception.UserControlException;
import tetris.exception.UserControlExceptionTypes;
import tetris.model.control.Position;
import tetris.model.control.UserControl;
import tetris.model.map.Block;
import tetris.model.map.Map;

@Getter
@ToString
public abstract class AbstractGame implements Game {

    protected final Map map;

    protected Blocks blocks;
    protected Position position;

    public AbstractGame(Map map) {

        this.map = map;

        this.blocks = new Blocks();
        this.position = new Position(this.map);
    }

    public void command(UserControl control, int point) throws UserControlException {

        final int toX;
        final int toY;
        final int[][] toForm;

        switch (control) {

            case blockLeft:

                toX = this.getPosition().getX() - point;
                toY = this.getPosition().getY();
                toForm = this.getCurrentBlock().getForm();
                break;

            case blockRight:

                toX = this.getPosition().getX() + point;
                toY = this.getPosition().getY();
                toForm = this.getCurrentBlock().getForm();
                break;

            case blockDown:

                toX = this.getPosition().getX();
                toY = this.getPosition().getY() + point;
                toForm = this.getCurrentBlock().getForm();
                break;

            case blockHit:

                toX = this.getPosition().getX();
                toY = this.getYWhenHit();

                this.hit(toX, toY);

                return;
            default:

                throw new UserControlException.Builder(UserControlExceptionTypes.wrongControl)
                        .build();
        }

        validation(toX, toY);

        if (this.getMap().isEmpty(toX, toY, toForm)) {

            this.getPosition().move(toX, toY);
            this.getCurrentBlock().setForm(toForm);
        }
    }

    private void validation(int toX, int toY) throws UserControlException {

        if (toX < 0 || toY > map.getTiles().length) {

            throw new UserControlException.Builder(UserControlExceptionTypes.blocked)
                    .build();
        }
    }

    private void hit(int x, int y) {

        int[][] form = this.getCurrentBlock().getForm();

        for (int i = 0; i < form.length; i++) {

            for (int j = 0; j < form[0].length; j++) {

                this.getMap().getTiles()[y + i][x + j] = form[j][i];
            }
        }
    }

    private int getYWhenHit() {

        int currentX = this.getPosition().getX();
        int currentXRight = currentX + this.getCurrentBlock().getForm()[0].length - 1;

        int currentY = this.getPosition().getY();
        int currentYBottom = this.getPosition().getY() + this.getCurrentBlock().getForm().length - 1;

        int[][] tiles = this.getMap().getTiles();

        for (int i = currentYBottom + 1; i < this.getMap().getHeight(); i++) {

            for (int j = currentX; j <= currentXRight; j++) {

                if (tiles[j][i] > 0) {

                    return i - 1;
                }
            }
        }

        return this.getMap().getHeight() - 1;
    }

    private Block getCurrentBlock() {

        return this.getBlocks().getCurrentBlock();
    }
//
//    public int[][] getSnapshot() throws UserControlException {
//
//        int[][] form = this.blocks.this.getCurrentBlock().getForm();
//
//        int currentX = this.getPosition().getX();
//        int currentY = this.getPosition().getY();
//
//        for (int y = 0; y < form.length; y++) {
//
//            for (int x = 0; x < form[0].length; x++) {
//
//                int blockValue = form[y][x];
//                boolean isBlockFilled = blockValue > 0;
//                if (isBlockFilled && this.getMap().isFilled(currentX + x, currentY + y)) {
//
//                    throw new UserControlException.Builder(UserControlExceptionTypes.gameOver)
//                            .build();
//                } else {
//
//                    this.getMap().getTiles()[currentY + y][currentX + x] = blockValue;
//                }
//            }
//        }
//
//        return this.getMap().getTiles();
//    }

    @Getter
    @ToString
    public class Blocks {

        private Block currentBlock;
        private Block nextBlock;

        private Blocks() {

            this.currentBlock = Block.newRandom();
            this.nextBlock = Block.newRandom();
        }
    }
}
