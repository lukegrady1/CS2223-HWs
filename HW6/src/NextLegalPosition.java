
public class NextLegalPosition {

    public static void main(String[] args) {
        //nxn size of board
        int n = 8;

        //queen positions, numbers represent what column they are in and the order they are is the row
        Integer[] qPositions = {1, 6, 8, 3, 5, 0, 0, 0};

        //Creates board
        Integer[][] b = creatingBoard(n, qPositions);

        //display the board after its made
        displayBoard(b);

        System.out.println("Next row = " + findNextRow(b, b.length - 1) +"\n");

        //get next legal position of board
        System.out.println("Finding next legal position of board\n");
        nextLegalPosition(b, findNextRow(b, b.length - 1));

        //print the next legal position of the board
        System.out.println("Legal Position Found");
        displayBoard(b);
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
            int column = qPositions[i] - 1;

            //if col = -1 that means there is no queen in that row and in that spot should print out 0
            if (column != -1) {
                System.out.println("Queens: (" + (i + 1) + ", " + (column + 1) + ")");
                b[i][column] = 1;
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
                System.out.println("Queen at (" + row + ", " + column + ") is under attack through the row from (" + i + ", " + column + ")");
                return true;
            }
        }

        //check columns for queens that are under attack
        for (i = 0; i < row; ++i) {
            if (b[i][column] == 1) {
                System.out.println("i = " + i);
                System.out.println("Queen at (" + row + ", " + column + ") is under attack through the column from (" + i + ", " + column + ")");
                return true;
            }
        }

        //check right to left diagonal meaning x slope for queens that are under attack
        for (i = row, j = column; i >= 0 && j >= 0; --i, --j) {
            if (i != row && b[i][j] == 1) {
                System.out.println("Queen at (" + row + ", " + column + ") is under attack by right-to-left diagonal from (" + i + ", " + j + ")");
                return true;
            }
        }

        //check left to right diagonal meaning x slope for queens that are under attack
        for (i = row, j = column; j < b.length && i >= 0; --i, ++j) {
            if (i != row && b[i][j] == 1) {
                System.out.println("Queen at (" + row + ", " + column + ") is under attack by left-to-right diagonal from (" + i + ", " + j + ")");
                return true;
            }
        }
        return false;
    }

    public static boolean isLegalPosition(Integer[][] b) {
        //loop over the board and see if any queens are threatened
        for (int i = 0; i < b.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                if (b[i][j] == 1) {
                    if (isQueenUnderAttack(b, i, j)) return false;
                }
            }
        }
        return true;
    }

    //finds the next row with a queen in it
    public static int findNextRow(Integer[][] b, int row){
        int answer = row;
        int col = -1;

        //check to see if there is a queen in the row
        for(int i = 0; i < b.length; ++i){
            if(b[row][i] == 1){
                col = i;
            }
        }

        //if not, backtrack up a row
        if(col == -1){
            answer = findNextRow(b, row - 1);
        }

        //if a queen is in the row return the row
        return answer;
    }

    //shows what column the queen in a specific row is in, goes to -1 if the queen doesn't exist
    public static int findColumn(Integer[][] b, int row){
        for(int i = 0; i < b.length; ++i){
            if(b[row][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public static boolean nextLegalPosition(Integer[][] b, int row){
        int nRow = findNextRow(b, b.length - 1);

        //if the board has legal queens and isn't full yet
        if(nRow < b.length - 1 && isLegalPosition(b)){
            //calls the function when on a new row
            return findNextLegalPosition(b, nRow + 1);
        }

        //otherwise call it on the initial row
        return findNextLegalPosition(b, nRow);
    }

    //returns true if the solution exists and returns false if the solution DNE
    public static boolean findNextLegalPosition(Integer[][] b, int row){
        //Finds the first row with a queen in it starting from the bottom

        //see if the row is empty
        int col = -1;
        for(int i = 0; i < b.length; ++i){
            if(b[row][i] == 1) col = i;
        }

        //if row is empty
        if(col == -1){
            //try to put a queen in each column
            for(int i = 0; i < b.length; ++i){
                if(!isQueenUnderAttack(b, row, i)){
                    //Actually place the queen in the row
                    b[row][i] = 1;
                    return true;
                }
            }

            //if the queen could not be placed, backtrack up
            int pRow = findNextRow(b, b.length - 1);
            int pCol = findColumn(b, pRow);
            b[pRow][pCol] = 0;
            return findNextLegalPosition(b, row - 1);
        }

        //record the position of the queen in this row then remove it from a copy of the board because that position can't support a full legal board
        int queenRow = row;
        int queenCol = findColumn(b, row);
        b[queenRow][queenCol] = 0;

        //try placing a queen in each col in this row
        for (int i = 0; i < b.length; ++i) {
            //check if queen can be placed on any square, and if there is not currently a queen there
            if (!isQueenUnderAttack(b, row, i)) {

                //place the queen in board if it is the position it wasn't already in
                if(i != queenCol){
                    b[row][i] = 1;
                    return true;
                }
            }
        }

        //if the queen can not be placed in any column in this row, backtrack
        return findNextLegalPosition(b, row - 1);
    }
}