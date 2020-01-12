package tetris.model.map;

import tetris.exception.UserControlException;
import tetris.model.NormalGame;
import tetris.model.control.UserControl;

import java.util.Arrays;
import java.util.Scanner;

public class Run {

    public static void main(String[] args) throws UserControlException {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());

        Scanner sc = new Scanner(System.in);

        while (true) {

            String command = sc.nextLine();
            System.out.println(command);

            if (command.equals("w")) {

                normalGame.command(UserControl.blockRotate, 1);
            } else if (command.equals("a")) {

                normalGame.command(UserControl.blockLeft, 1);
            } else if (command.equals("s")) {

                normalGame.command(UserControl.blockDown, 1);
            } else if (command.equals("d")) {

                normalGame.command(UserControl.blockRight, 1);
            } else if (command.equals("h")) {

                normalGame.command(UserControl.blockHit, 1);
            }

            int[][] tiles = deepCopy(normalGame.getMap().getTiles());
            for (int i = 0; i < normalGame.getBlocks().getCurrentBlock().getForm().length; i++) {

                int currentY = i + normalGame.getPosition().getY();
                if (currentY < 0) {

                    continue;
                }

                for (int j = 0; j < normalGame.getBlocks().getCurrentBlock().getForm()[0].length; j++) {

                    if (normalGame.getBlocks().getCurrentBlock().getForm()[i][j] == 0) {

                        continue;
                    }

                    tiles[currentY][j + normalGame.getPosition().getX()] = normalGame.getBlocks().getCurrentBlock().getForm()[i][j];
                }
            }


            for (int i = 0; i < tiles.length; i++) {

                System.out.print(String.format("[%02d] |", i));

                for (int j = 0; j < tiles[0].length; j++) {

                    System.out.print(tiles[i][j] + " ");
                }

                System.out.println("|");
            }
        }
    }

    public static int[][] deepCopy(int[][] original) {

        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return result;
    }
}
