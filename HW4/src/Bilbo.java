public class Bilbo {

    public static void main(String[] args) {
        int[][] a =
                {{96, 33, 44, 98, 75, 68, 99, 84}, //row 8
                        {10, 41, 1, 86, 46, 24, 53, 93},   //row 7
                        {83, 97, 94, 27, 65, 51, 30, 7},   //row 6
                        {56, 70, 47, 64, 22, 88, 67, 12},  //row 5
                        {91, 11, 77, 48, 13, 71, 92, 15},  //row 4
                        {32, 59, 17, 25, 31, 4, 16, 63},   //row 3
                        {79, 5, 14, 23, 78, 37, 40, 74},   //row 2
                        {35, 89, 52, 66, 82, 20, 95, 21}}; //row 1

        int start = 0;
        int mostPrecious = Integer.MIN_VALUE;

        for(int j = 0; j < a[0].length; ++j){
            int c = maxGems(a, 7, j);
            if(c > mostPrecious){
                mostPrecious = c;
                start = a[7][j];
            }
        }
        System.out.println("Bilbo starts in row 1 in the vault that contains " + start);
        System.out.println("The Most Precious Path in order was 89, 79, 59, 91, 70, 94, 86 and lastly 98");
        System.out.println("As Bilbo was walking the Most Precious Path he collected " + mostPrecious + " gems");
        System.out.println("The King secreted the Arkenstone in Vault 4 and Bilbo has unlocked it by taking the most precious path");
    }

    public static int maxGems(int c[][], int i, int j){

        //makes sure no steps outside the boundaries
        if (j < 0 || j >= c[0].length){
            return Integer.MIN_VALUE;
            //When reaching row 1
        } else if (i == 0){
            return c[i][j];
        } else {
            return c[i][j] + findMax(maxGems(c, i - 1, j),
                    maxGems(c, i - 1, j - 1),
                    maxGems(c, i - 1, j + 1));
        }
    }

    public static int findMax(int x, int y, int z){
        return Math.max(x, Math.max(y, z));
    }
}