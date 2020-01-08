package tetris.model;

import org.junit.jupiter.api.Test;
import tetris.exception.UserControlException;
import tetris.exception.UserControlExceptionTypes;
import tetris.model.control.UserControl;
import tetris.model.map.Map;
import tetris.model.map.SimpleMapFactory;

import java.util.Scanner;

class NormalGameTest {

    @Test
    void testInitNormalGame() {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());
        Map map = normalGame.getMap();
        map.printToConsole();
    }

    @Test
    void testTDDLeft() throws UserControlException {

        NormalGame normalGame = new NormalGame(new SimpleMapFactory().create());
//        left(normalGame);
//        left(normalGame);
//        left(normalGame);
//        left(normalGame);
//        left(normalGame);
//        left(normalGame);

        Scanner scanner = new Scanner(System.in);
        while (true) {

            String next = scanner.next();
            UserControl userControl;
            if (next.equals("A")) {

                userControl = UserControl.blockLeft;
            } else if (next.equals("S")) {

                userControl = UserControl.blockDown;
            } else if (next.equals("D")) {

                userControl = UserControl.blockRight;
            } else {

                throw new UserControlException.Builder(UserControlExceptionTypes.wrongControl)
                        .build();
            }

            normalGame.command(userControl, 1);
        }
    }

    private void left(NormalGame normalGame) throws UserControlException {

        normalGame.command(UserControl.blockLeft, 1);

        System.out.println(normalGame.getPosition().getX() + " " + normalGame.getPosition().getY());
    }
}