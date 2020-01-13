package tetris.model;

import lombok.Getter;
import lombok.ToString;
import tetris.exception.UserControlException;
import tetris.exception.UserControlExceptionTypes;
import tetris.model.control.Position;
import tetris.model.control.UserControl;
import tetris.model.map.Block;
import tetris.model.map.Map;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public abstract class AbstractGame implements Game {

    protected final Map map;

    protected Blocks blocks;
    protected Position position;

    public AbstractGame(Map map) {

        this.map = map;

        this.blocks = new Blocks();
        this.position = Position.newPosition(this.map, this.getCurrentBlock());
    }

    public void command(UserControl control, int point) throws UserControlException {

        if (control.isMoving()) {

            moveBlock(control, point);
        } else {

            if (control == UserControl.blockRotate) {

                int[][] rotated = this.getCurrentBlock().rotate();
                boolean rotatable = this.getMap().isEmpty(this.getPosition().getX(), this.getPosition().getY(), rotated);

                if (rotatable) {

                    this.getCurrentBlock().setForm(rotated);
                } else {

                    throw new UserControlException.Builder(UserControlExceptionTypes.blocked)
                            .build();
                }
            } else if (control == UserControl.blockHit) {

                this.hit(this.getPosition().getX(), this.getYWhenHit());
                this.blocks.next();
                this.position = Position.newPosition(this.map, this.getCurrentBlock());

                this.survive();
            } else {

                throw new UserControlException.Builder(UserControlExceptionTypes.wrongControl)
                        .build();
            }
        }

        postCommand();
    }

    private void survive() {

        List<Integer> surviveLines = new ArrayList<>();

        int mapHeight = this.map.getTiles().length;
        int mapWidth = this.map.getTiles()[0].length;

        for (int i = 0; i < mapHeight; i++) {

            // TODO. 알고리즘 리팩토링하자,,
            int filledSize = 0;

            for (int j = 0; j < mapWidth; j++) {

                if (this.map.getTiles()[i][j] > 0) {

                    filledSize++;
                }
            }

            if (filledSize > 0 && filledSize < mapWidth) {

                surviveLines.add(i);
            }
        }

        int[][] newTiles = new int[mapHeight][mapWidth];
        int startIndex = mapHeight - surviveLines.size();

        for (int i = 0; i < surviveLines.size(); i++) {

            newTiles[startIndex + i] = this.map.getTiles()[surviveLines.get(i)];
        }

        this.map.setTilesSyncronized(newTiles);
    }

    private void moveBlock(UserControl control, int point) throws UserControlException {

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


    private void postCommand() throws UserControlException {

        if (isGameOver()) {

            throw new UserControlException.Builder(UserControlExceptionTypes.gameOver)
                    .build();
        }
    }

    private boolean isGameOver() {

        int[][] tiles = this.map.getTiles();
        for (int i = 0; i < tiles[0].length; i++) {

            if (tiles[0][i] > 0) {

                return true;
            }
        }

        return false;
    }

    private void validation(int toX, int toY) throws UserControlException {

        boolean isLeftEnded = toX < 0;
        boolean isRightEnded = toX > this.map.getTiles()[0].length;
        boolean isBottomEnded = toY > map.getTiles().length;

        if (isLeftEnded || isRightEnded || isBottomEnded) {

            throw new UserControlException.Builder(UserControlExceptionTypes.blocked)
                    .build();
        }
    }

    private void hit(int x, int y) {

        int[][] form = this.getCurrentBlock().getForm();

        for (int i = 0; i < form.length; i++) {

            if ((y + i) < 0) {

                continue;
            }

            for (int j = 0; j < form[0].length; j++) {

                if (form[i][j] > 0) {

                    this.getMap().getTiles()[y + i][x + j] = form[i][j];
                }
            }
        }
    }

    private int getYWhenHit() {

        int[][] form = this.getCurrentBlock().getForm();
        int[][] tiles = this.getMap().getTiles();

        for (int i = this.position.getY() + 1; i <= tiles.length - form.length; i++) {

            if (!this.map.isEmpty(this.position.getX(), i, this.getCurrentBlock().getForm())) {

                return i - 1;
            }
        }

        return this.getMap().getHeight() - form.length;
    }

    private Block getCurrentBlock() {

        return this.getBlocks().getCurrentBlock();
    }

    @Getter
    @ToString
    public class Blocks {

        private Block currentBlock;
        private Block nextBlock;

        private Blocks() {

            this.currentBlock = Block.newRandom();
            this.nextBlock = Block.newRandom();
        }

        private void next() {

            this.currentBlock = nextBlock;
            this.nextBlock = Block.newRandom();
        }
    }
}
