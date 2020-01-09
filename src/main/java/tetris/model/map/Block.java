package tetris.model.map;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Block {

    private final BlockType type;
    @Setter
    private int[][] form;

    public static Block newRandom() {

        return new Block(BlockType.pickRandom());
    }

    public Block(BlockType blockType) {

        this.type = blockType;
        this.form = blockType.getDefaultForm();
    }

    public int[][] rotateAndGetForm() {

        this.rotate();

        return this.form;
    }

    public void rotate() {

        int[][] rotated = new int[this.form[0].length][this.form.length];

        for (int i = 0; i < this.form.length; i++) {

            for (int j = 0; j < this.form[0].length; j++) {

                rotated[j][this.form.length - 1 - i] = this.form[i][j];
            }
        }

        this.form = rotated;
    }
}
