import java.util.Arrays;

public class Subirach {

    public static int[] makeSingle(int[][] m) {
        int[] flatArray = Arrays.stream(m)
                .flatMapToInt(Arrays::stream)
                .toArray();
        return flatArray;
    }

    public static int calcSum(int[][] list) {
        return list[0][0] + list[0][1] + list[0][2] + list[0][3];
    }

    public static void fourElemSums(int[][] s){
        int counter = 0;
        int sums = calcSum(s);
        int[] listOfMagicSquare = makeSingle(s);
        int n = listOfMagicSquare.length;

        System.out.println("Possible combinations of four elements that have a sum of " + sums + ": \n");

        for(int i = 0; i < n-3; i++){
            for(int j = i+1; j < n-2; j++){
                for(int k = i+1; k < n-1; k++){
                    for(int l = k+1; l < n; l++){
                        if (listOfMagicSquare[i] + listOfMagicSquare[j] + listOfMagicSquare[k] + listOfMagicSquare[l] == sums) {
                            System.out.println("[" + listOfMagicSquare[i] + "," + listOfMagicSquare[j] + "," + listOfMagicSquare[k] + "," + listOfMagicSquare[l] + "]");
                            counter++;
                        }
                    }
                }
            }
        }
        System.out.println("\nPossible combinations using four elements that have a sum of " + sums + ": " + counter + " combinations");
    }

    public static void allSums(int[][] s){
        int counter = 0;
        int sums = calcSum(s);
        int[] listOfMagicSquare = makeSingle(s);
        int n = listOfMagicSquare.length;

        int totalCount = 0;

        for(int i=0; i < (1<<n); i++){
            int tempSum = 0;
            for (int j=0; j<n; j++){
                if ((i & (1<<j)) > 0) {
                    tempSum += listOfMagicSquare[j];
                }
            };
            totalCount++;
            if(tempSum == sums){
                counter++;
            }
        }
        System.out.println("Possible combinations for a sum of "+ sums +": \n" + counter + " Combinations");
        System.out.println("\nTotal Count: " + totalCount);
    }

    public static void largestSum(int[][] s){
        int counter = 0;
        int sums = calcSum(s);
        int[] listOfMagicSquare = makeSingle(s);
        int n = listOfMagicSquare.length;

        System.out.println("The number of ways every possible sum can be formed: \n");

        for(int i=0; i<(1<<n); i++){
            int tempSum= 0;
            System.out.print("[");
            for ( int j =0; j <n; j++){
                if ((i & (1<<j)) > 0){
                    tempSum += listOfMagicSquare[j];
                    System.out.print(listOfMagicSquare[j] + " ");
                }
            }
            counter++;
            System.out.println("]");
        }
        System.out.println("\nPossible Combinations: \n" + counter + " Combinations");
        //The interesting thing about this is that it comes out to 65536 which is 2^16 which makes sense due to the Magic Square consisting of 16 smaller squares
    }

    public static void main(String[] args){
        int[][] Subirach = new int[4][4];

        Subirach[0][0] = 1;
        Subirach[0][1] = 14;
        Subirach[0][2] = 14;
        Subirach[0][3] = 4;

        Subirach[1][0] = 11;
        Subirach[1][1] = 7;
        Subirach[1][2] = 6;
        Subirach[1][3] = 9;

        Subirach[2][0] = 8;
        Subirach[2][1] = 10;
        Subirach[2][2] = 10;
        Subirach[2][3] = 5;

        Subirach[3][0] = 13;
        Subirach[3][1] = 2;
        Subirach[3][2] = 3;
        Subirach[3][3] = 15;

        //Calls to the three functions below, will have to comment out largestSum(Subirach) in order for the others to run
        fourElemSums(Subirach);
        allSums(Subirach);
        //largestSum(Subirach);
    }
}
