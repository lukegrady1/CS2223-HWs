public class IsLegalPosition {

    public static void main(String[] args) {
        //nxn size of board
        int n = 8;

        //queen positions, numbers represent what column they are in and the order they are is the row
        Integer[] qPositions = {1, 6, 8, 3, 7, 4, 2, 5};

        //Creates board
        Integer[][] b = creatingBoard(n, qPositions);

        //display the board after its made
        displayBoard(b);

        //Prints out whether the positions are legal
        System.out.println("True or False, are the positions legal? " + isLegalPosition(b));
    }

    public static Integer[][] creatingBoard(int n, Integer[] qPositions) {
        System.out.println("Queens Positions:");
        Integer[][] b = new Integer[n][n];

        //fills board with 0's where there isn't a queen
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                b[i][j] = 0;
            }
        }

        System.out.println("        (row, column)");
        //loop over the qPositions array and adds queens to the board where they belong
        for (int i = 0; i < qPositions.length; ++i) {
            int col = qPositions[i] - 1;

            //if col = -1 that means there is no queen in that row and in that spot should print out 0
            if (col != -1) {
                System.out.println("Queens: (" + (i + 1) + ", " + (col + 1) + ")");
                b[i][col] = 1;
            }
        }

        return b;
    }

    public static void displayBoard(Integer[][] b) {
        System.out.println("\nBoard:");
        for (int i = 0; i < b.length; ++i) {
            for (int j = 0; j < b[0].length; ++j) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\n");
    }

    //Checks if the queen can be attacked in the position it is in from row, column or diagonals
    public static boolean isQueenUnderAttack(Integer[][] b, int row, int column) {
        int i, j;

        //check rows for queens that are under attack
        for (i = 0; i < b.length; ++i) {
            if (i != column && b[row][i] == 1) {
                System.out.println("Queen at (" + row + ", " + column + ") is under attack");
                return true;
            }
        }

        //check column for queens that are under attack
        for (i = 0; i < row; ++i) {
            if (b[i][column] == 1) {
                System.out.println("i = " + i);
                System.out.println("Queen at (" + row + ", " + column + ") is under attack at " + i + ", " + column);
                return true;
            }
        }

        //check right to left diagonal meaning -x slope for queens that are under attack
        for (i = row, j = column; i >= 0 && j >= 0; --i, --j) {
            if (i != row && b[i][j] == 1) {
                System.out.println("Queen at " + row + ", " + column + " threatened on y = -x diagonal by " + i + ", " + j);
                return true;
            }
        }

        //check left to right diagonal meaning x slope for queens that are under attack
        for (i = row, j = column; j < b.length && i >= 0; --i, ++j) {
            if (i != row && b[i][j] == 1) {
                System.out.println("Queen at " + row + ", " + column + " threatened on y = x diagonal");
                return true;
            }
        }
        return false;
    }

    public static boolean isLegalPosition(Integer[][] b) {
        //loop over the board and see if any queens are under attack
        for (int i = 0; i < b.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                if (b[i][j] == 1) {
                    if (isQueenUnderAttack(b, i, j)) return false;
                }
            }
        }
        return true;
    }
}