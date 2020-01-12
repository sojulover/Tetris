package tetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tetris.exception.UserControlException;
import tetris.model.control.UserControl;
import tetris.model.map.Map;
import tetris.model.map.SimpleMapFactory;

public class NormalGameTest {

    @Test
    void testInitNormalGame() {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());
        Map map = normalGame.getMap();

        for (int y = 0; y < map.getTiles().length; y++) {

            for (int x = 0; x < map.getTiles()[y].length; x++) {

                System.out.print(map.getTiles()[y][x] + " ");
            }

            System.out.println();
        }
    }

    @Test
    void testLeft() throws UserControlException {

//        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());
//
//        Scanner sc = new Scanner(System.setIn());
//        System.out.print("Enter year(yyyy):");
//        int year = sc.nextInt();
//
//        System.out.println(year);
//        sc.close();
//
////        while (true) {
////
////            String command = in.nextLine();
////            System.out.println(command);
////        }

    }
//
//    private void control(NormalGame normalGame, UserControl control) throws UserControlException {
//
//        normalGame.command(control, 1);
//
//        int[][] snapshot = normalGame.getSnapshot();
//
//        for (int y = 0; y < snapshot.length; y++) {
//
//            for (int x = 0; x < snapshot[y].length; x++) {
//
//                System.out.print(snapshot[y][x] + " ");
//            }
//
//            System.out.println();
//        }
//    }

    private void left(NormalGame normalGame) throws UserControlException {

        normalGame.command(UserControl.blockLeft, 1);

        System.out.println(normalGame.getPosition().getX() + " " + normalGame.getPosition().getY());
    }

    @Test
    void testCalcYBottom() {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());

        int currentYBottom = normalGame.getPosition().getY() + normalGame.getBlocks().getCurrentBlock().getForm().length;

        switch (normalGame.getBlocks().getCurrentBlock().getType()) {

            case I:

                Assertions.assertEquals(4, currentYBottom);
                break;

            case J:

                Assertions.assertEquals(3, currentYBottom);
                break;

            case L:

                Assertions.assertEquals(3, currentYBottom);
                break;

            case O:

                Assertions.assertEquals(2, currentYBottom);
                break;

            case S:

                Assertions.assertEquals(2, currentYBottom);
                break;

            case T:

                Assertions.assertEquals(2, currentYBottom);
                break;

            case Z:

                Assertions.assertEquals(2, currentYBottom);
                break;
        }
    }
}