package control;

import java.util.Scanner;

/* На поле размером nxm размещены корабли (2 трехпалубных, 3 двухпалубных, 4 однопалубных)
       [[0,0,0,1,1,0],
        [0,1,0,0,0,0],
        [0,1,0,1,0,0],
        [0,1,0,1,0,0]];

При ходе [2, 1] программа выдает число 3, т.к. подбит трехпалубный корабль
При ходе [1, 3] программа выдает число 0 (промах) и т.д.
Изменить условие так, чтобы каждый из кораблей мог "отразить" одну атаку, выдав 0 при первом попадании в него.
Разработать алгоритм, который подобьет все корабли если не за минимальное, то за некоторое удовлетворительное число ходов.
 */
public class SeaBattle {
    private static final int SIZE = 10;
    private static final int SHIPS_1 = 4;
    private static final int SHIPS_2 = 3;
    private static final int SHIPS_3 = 2;

    private static int[][] board = new int[SIZE][SIZE];
    private static int[] shipsLeft = new int[]{SHIPS_1, SHIPS_2, SHIPS_2};

    public static void main(String[] args) {


        initializeBoard();

        System.out.println(shoot(2, 7));
        System.out.println(shoot(1, 3));
    }

    private static void initializeBoard() {
        for (int i = 0; i < SHIPS_1; i++) {
            addShip(3);
        }
        for (int i = 0; i < SHIPS_2; i++) {
            addShip(2);
        }
        for (int i = 0; i < SHIPS_3; i++) {
            addShip(1);
        }
    }

    private static void addShip(int size) {
        int x = (int) (Math.random() * SIZE);
        int y = (int) (Math.random() * SIZE);
        int direction = (int) (Math.random() * 2);

        if (canPlaceShip(x, y, size, direction)) {
            for (int i = 0; i < size; i++) {
                if (direction == 0) {
                    board[x + i][y] = size;
                } else {
                    board[x][y + i] = size;
                }
            }
        } else {
            addShip(size);
        }
    }

    private static boolean canPlaceShip(int x, int y, int size, int direction) {
        if (direction == 0 && x + size <= SIZE) {
            for (int i = 0; i < size; i++) {
                if (board[x + i][y] != 0) {
                    return false;
                }
            }
            return true;
        } else if (direction == 1 && y + size <= SIZE) {
            for (int i = 0; i < size; i++) {
                if (board[x][y + i] != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static int shoot(int x, int y) {
        if (board[x][y] == 0) {
            return 0;
        } else {
            int shipSize = board[x][y];
            board[x][y] = 0;
            shipsLeft[shipSize - 1]--;
            return shipSize;
        }
    }
}
