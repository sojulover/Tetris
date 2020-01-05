package map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tetris.model.map.Block;
import tetris.model.map.BlockType;

class BlockTypeTest {

    @Test
    void testPrintBlockForms() {

        for (BlockType value : BlockType.values()) {

            int[][] form = value.getDefaultForm();

            for (int i = 0; i < form.length; i++) {

                for (int j = 0; j < form[i].length; j++) {

                    System.out.print(form[i][j]);
                }

                System.out.println();
            }
        }
    }

    @Test
    void testRotation() {

        Block block = new Block(BlockType.I);
        print(block.getForm());
        print(block.rotateAndGetForm());
        System.out.println("rotated");

        Block block2 = new Block(BlockType.I);
        print(block2.getForm());
    }

    @Test
    void testRotate() {

        Block block = new Block(BlockType.Z);
        int[][] form = block.getForm();

        print(form);
        form = rotate(form);

        print(form);
        form = rotate(form);

        print(form);
        form = rotate(form);

        print(form);
        form = rotate(form);

        print(form);
    }

    private int[][] rotate(int[][] form) {

        int[][] rotated = new int[form[0].length][form.length];

        for (int i = 0; i < form.length; i++) {

            for (int j = 0; j < form[0].length; j++) {

                // 0 0 -> 0 1
                // 0 1 -> 1 1
                // 0 2 -> 2 1

                // 1 0 -> 0 0
                // 1 1 -> 1 0
                // 1 2 -> 2 0

                rotated[j][form.length - 1 - i] = form[i][j];
            }
        }

        return rotated;
    }


    @Test
    void testRotateAndGet() {

        Block jBlock = new Block(BlockType.J);
        print(jBlock.getForm());
        Assertions.assertArrayEquals(jBlock.getForm(), new int[][]{
                {0, 2},
                {0, 2},
                {2, 2},
        });

        jBlock.rotate();
        print(jBlock.getForm());

        Assertions.assertArrayEquals(jBlock.getForm(), new int[][]{
                {2, 0, 0},
                {2, 2, 2}
        });

    }

    private void print(int[][] form) {

        for (int i = 0; i < form.length; i++) {

            for (int j = 0; j < form[i].length; j++) {

                System.out.print(form[i][j]);
            }

            System.out.println();
        }
    }
}