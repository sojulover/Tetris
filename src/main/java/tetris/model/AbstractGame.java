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

                toX = this.position.getX() - point;
                toY = this.getPosition().getY();
                toForm = this.getBlocks().getCurrentBlock().getForm();
                break;

            case blockRight:

                toX = this.position.getX() + point;
                toY = this.getPosition().getY();
                toForm = this.getBlocks().getCurrentBlock().getForm();
                break;

            case blockDown:

                toX = this.position.getX();
                toY = this.getPosition().getY() + point;
                toForm = this.getBlocks().getCurrentBlock().getForm();
                break;

            default:

                throw new UserControlException.Builder(UserControlExceptionTypes.wrongControl)
                        .build();
        }

        validation(toX, toY);

        if (this.map.isEmpty(toX, toY, toForm)) {

            this.position.move(toX, toY);
            this.blocks.getCurrentBlock().setForm(toForm);
        }
    }

    private void validation(int toX, int toY) throws UserControlException {

        if (toX < 0 || toY > map.getTiles().length) {

            throw new UserControlException.Builder(UserControlExceptionTypes.blocked)
                    .build();
        }
    }

    public int[][] getSnapshot() throws UserControlException {

        int[][] form = this.blocks.getCurrentBlock().getForm();

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        for (int y = 0; y < form.length; y++) {

            for (int x = 0; x < form[0].length; x++) {

                int blockValue = form[y][x];
                boolean isBlockFilled = blockValue > 0;
                if (isBlockFilled && this.map.isFilled(currentX + x, currentY + y)) {

                    throw new UserControlException.Builder(UserControlExceptionTypes.gameOver)
                            .build();
                } else {

                    this.map.getTiles()[currentX + x][currentY + y] = blockValue;
                }
            }
        }

        return this.map.getTiles();
    }

    @Getter
    @ToString
    private class Blocks {

        private Block currentBlock;
        private Block nextBlock;

        private Blocks() {

            this.currentBlock = Block.newRandom();
            this.nextBlock = Block.newRandom();
        }
    }
}
