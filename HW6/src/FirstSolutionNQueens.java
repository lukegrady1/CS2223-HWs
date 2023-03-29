public class FirstSolutionNQueens {

    public static void main(String[] args) {
        //nxn
        int n = 25;

        Integer[][] b = new Integer [n][n];
        //zeroes the board
        zeroedBoard(b);

        //get the first solution for the n-queens problem with the given size
        findSolution(b);
    }

    //fills the board with zeroes
    public static void zeroedBoard(Integer[][] b){
        //fill with 0's
        for(int i = 0; i < b.length; ++i){
            for(int j = 0; j < b.length; ++j){
                b[i][j] = 0;
            }
        }
    }

    //displays the board
    public static void displayBoard(Integer[][] b){
        Integer[] queens = new Integer[b.length];
        System.out.println("\nBoard:");
        for(int i = 0; i < b.length; ++i){
            for(int j = 0; j < b[0].length; ++j){
                System.out.print(b[i][j] + " ");
                if(b[i][j] == 1) queens[i] = j;
            }
            System.out.println();
        }
        System.out.print("(");
        for(int i = 0; i < queens.length; ++i){
            System.out.print((i == queens.length || i == 0 ? (queens[i] + 1) : ", " + (queens[i] + 1)));
        }
        System.out.println(")\n");
    }

    //Checks if the queen can be attacked in the position it is in from row, column or diagonals
    public static boolean isQueenUnderAttack(Integer[][] b, int row, int column){
        int i, j;

        //only need to check to the top of the queen

        //check rows for queens that are under attack
        for(i = 0; i < b.length; ++i){
            if(i != column && b[row][i] == 1) return true;
        }

        //check columns for queens that are under attack
        for(i = 0; i < row; ++i){
            if(b[i][column] == 1) return true;

        }

        //check right to left diagonal meaning -x slope for queens that are under attack
        for(i = row, j = column; i >= 0 && j >= 0; --i, --j){
            if(i != row && b[i][j] == 1) return true;

        }

        //check left to right diagonal meaning x slope for queens that are under attack
        for(i = row, j = column; j < b.length && i >= 0; --i, ++j){
            if(i != row && b[i][j] == 1) return true;

        }
        return false;
    }

    //solves board using recursive function
    public static boolean solveBoard(Integer[][] b, int row){
        //if all queens are placed, done
        if(row >= b.length) return true;

        //try to place a queen in all columns of the row left to right
        for(int i = 0; i < b.length; ++i){
            //check if move is legal
            if(!isQueenUnderAttack(b, row, i)){
                //place queen
                b[row][i] = 1;

                if(solveBoard(b, row + 1)) return true;

                //if placing the queen isn't legal, backtrack
                b[row][i] = 0;
            }
        }
        //if the queen can't be placed return false
        return false;
    }

    //the main function to find the solution
    public static boolean findSolution(Integer[][] b){
        if(solveBoard(b, 0) == false){
            System.out.println("No solution");
            return false;
        }
        displayBoard(b);
        return true;
    }
}