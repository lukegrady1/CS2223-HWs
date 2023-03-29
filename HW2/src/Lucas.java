import java.util.Scanner;
import static java.lang.System.exit;

public class Lucas {

    public static long lucasNum(int n){

        //make sure of acceptable input for user input (Not used when just calling function)
        if (n<0){
            System.out.println("Not acceptable input.  Only whole Numbers.");
            exit(0);
        }
        //base cases
        if (n==0){
            return 2;
        } else if (n == 1) {
            return 1;
        }
        //equation for Lucas Numbers
        else{
            return lucasNum(n-1) + lucasNum(n-2);
        }
    }

    //calculate runtime
    public static long time(int n){

        long beginningTime = System.nanoTime();

        lucasNum(n);

        long finishTime = System.nanoTime();

        return finishTime - beginningTime;
    }

    //print Lucas Number, run time, ratio of successive calc & ratio of successive time of lucasNum function
    public static void printLucasNum(){

        for(int i=0; i <= 40; i++){

            System.out.println("\nLucas Number: " + lucasNum(i));

            System.out.println("Time needed to compute: " + time(i) + " nanoseconds");

            System.out.println("Ratio of successive calculations: " + (float) lucasNum(i+1)/lucasNum(i));

            System.out.println("Ratio of successive calculation time: " + (float) time(i+1)/time(i));

        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static long birthday(int n){

        //base cases
        if (n == 0){
            return 5;
        } else if (n == 1) {
            return 10;
        }
        //Equation for finding my numbers
        else{
            return birthday(n - 1) + birthday(n - 2);
        }
    }

    //calculate runtime
    public static long timeBirthday(int n){

        long beginningTime = System.nanoTime();

        birthday(n);

        long finishTime = System.nanoTime();

        return finishTime - beginningTime;
    }

    //print number, run time, ratio of successive calc & ratio of successive time of Birthday function
    public static void printBirthday(){

        for(int i=0; i < 40; i++){

            System.out.println("\nMy Number: " + birthday(i));

            System.out.println("Time needed to compute: " + timeBirthday(i) + " nanoseconds");

            System.out.println("ratio of successive calculations: " + (float) birthday(i+1)/birthday(i));

            System.out.println("Ratio of successive calculation time: " + (float) timeBirthday(i+1)/timeBirthday(i));

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //call all the functions in Main
    public static void main(String[] args) {

        printLucasNum();

        printBirthday();

        //take user input then spit out that numbers lucas number
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInput an Int: ");

        int userNumber = scan.nextInt();
        long beginningTime = System.nanoTime();

        System.out.println(lucasNum(userNumber));

        long finishTime = System.nanoTime();
        double actualTime = finishTime - beginningTime ;

        System.out.println("Calculation Time: " + actualTime + " Nanoseconds");


        //Édouard Lucas was a French mathematician known for studying the Fibonacci sequence, Lucas-Carmichael numbers & Pell-Lucas numbers

        //The ratios of the successive calculations remained the same as the regular Lucas Numbers
        //The order of growth for both lucasNum & birthday are 2^n

    }
}