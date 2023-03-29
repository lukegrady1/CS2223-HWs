public class GaussJordan {

    /*
    1. The ForwardElimination algorithm on page 210 of Levitin fails to provide a solution for the matrix because the second column in row two turns to zero and
    the algorithm tries to use that as a pivot, but can't do anything due to the zero.
    The BetterForwardElimination algorithm on page 211 of Levitin remedies this because it checks the pivots to see if it is zero or not.  In the algorithm
    ForwardElimination it thinks that if the pivot is zero then the matrix is redundant, because it's impossible to row reduce with a zero.  The algorithm
    BetterForwardElimination fixes it by selecting a different row to use as the pivot if the original is a zero.

    2. The BetterForwardElimination algorithm fails to provide a solution for the matrix featured below because there are infinite solutions due to free variables
    In order to remedy this problem the algorithm will have to check if two of the rows are multiples of one another and then print that there are infinite solutions
    for the matrix.
    */

    public static void main(String[] args) {

        float a[][] =
                {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36},
                        {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61},
                        {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0},
                        {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42}     };

        int z = a.length;

        System.out.println("\nThe original matrix is: ");
        printMatrix(a, z);
        gaussJordanMatrix(a, z);
        System.out.println("\nThe final matrix is: ");
        printMatrix(a, z);
    }

    //Prints the final matrix
    static void printMatrix(float a[][], int z){
        for (int i = 0; i < z; i++){
            for (int j = 0; j <= z; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
    }

    //put matrix in REF using gauss-jordan
    public static void gaussJordanMatrix(float a[][], int z){
        int i, j, k = 0, c, m = 0;
        //row by row operations
        for (i = 0; i < z; i++){
            if (a[i][i] == 0){
                c = 1;
                while ((i + c) < z && a[i + c][i] == 0)
                    c++;

                for (j = i, k = 0; k <= z; k++){
                    float x =a[j][k];
                    a[j][k] = a[j+c][k];
                    a[j+c][k] = x;
                }
            }
            for (j = 0; j < z; j++){
                if (i != j){
                    //convert to REF
                    float x = a[j][i] / a[i][i];
                    for (k = 0; k <= z; k++)
                        a[j][k] = a[j][k] - (a[i][k]) * x;
                }
            }
        }

        //Prints solution through dividing the last column by whatever number is also in the row
        System.out.println("\nFinal Values:");
        for (i = 0; i < z; i++)
            System.out.println("x" + (i + 1) + " = " + a[i][z] / a[i][i]);
    }

}
