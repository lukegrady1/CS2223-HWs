import java.util.ArrayList;
import java.util.HashMap;

public class graycodesarefun {

    public static void main(String[] args){
        ArrayList<String> bRGC = new ArrayList<String>();

        //The code can be extended to  "Eve", "Felix", "Gerry", "Helen", "Ian", "Jane", "Karl" by just adding them to list below
        String[] wolfeChildren = new String[]{"Alice", "Bob", "Chris", "Dylan"};

        //Finds the number of children from looking at length of list above
        int numberOfChildren = wolfeChildren.length;
        bRGC = bRGC(numberOfChildren);

        //Binary Reflected Gray Codes for n = 4 printed (would print the BRGC for however many children)
        System.out.println("\nThe Binary Reflected Gray Code of n = " + numberOfChildren + " is as follows:");
        for(String s : bRGC){
            System.out.println(s);
        }

        //iterate over the BRGC's and see which binary value changes between each one
        //Which ever value changes print out the name of whatever child is linked to that binary value
        System.out.println("\nWhich Wolfe child has to move for each picture? ");

        for(int i = 0; i < bRGC.size() - 1; ++i){
            //Have to subtract the index from the length of the array in order to get the kids index
            int indexOfKid = numberOfChildren - getSwappedKid(bRGC.get(i), bRGC.get(i + 1))[0];
            System.out.println(wolfeChildren[indexOfKid - 1]);
        }

        //using hash map to find which kid moves in the picture or out of the picture and linking them with a BRGC
        HashMap<String, String> swappedChild = new HashMap<String, String>();
        for(int i = 0; i < bRGC.size() - 1; ++i){
            int[] swappedK = getSwappedKid(bRGC.get(i), bRGC.get(i + 1));
            int index = numberOfChildren - swappedK[0];
            swappedChild.put(bRGC.get(i + 1), wolfeChildren[index - 1] + (swappedK[1] == 0 ? " Out" : " In"));
        }

        //Printing the final table with everything filled out correctly
        printOutTable(bRGC, wolfeChildren, swappedChild);
        System.out.println("\nFinished table for all 15 photos Professor Wolfe wants to take. ");
    }

    public static ArrayList<String> bRGC (int n){
        if( n <= 0 )
            return new ArrayList<String>();

        ArrayList<String> a = new ArrayList<String>();

        a.add("0");
        a.add("1");

        //bit shifts to get the next binary number
        for(int i = 2; i < (1 << n); i = i << 1){
            for (int j = i - 1 ; j >= 0; --j)
                a.add(a.get(j));

            for(int j = 0; j < i; ++j)
                a.set(j, "0" + a.get(j));

            for(int j = i; j < 2*i; ++j){
                a.set(j, "1" + a.get(j));
            }
        }
        return a;
    }

    //finding which kid gets swapped
    public static int[] getSwappedKid(String str0, String str1){
        int[] swapped = new int[2];
        for(int i = 0; i < str0.length(); ++i){
            if(str0.charAt(i) != str1.charAt(i)){
                swapped[0] = i;
                swapped[1] = Character.getNumericValue(str1.charAt(i));
                return swapped;
            }
        }
        return swapped;
    }

    public static String kidsInPhoto(String[] kids, String bRGC){
        String ans = "";
        for(int i = 0; i < bRGC.length(); ++i){
            int binary = Integer.parseInt(bRGC.substring(i, i + 1));
            if(ans != "" && binary == 1) ans += " ";
            if(binary == 1) ans += kids[kids.length - i - 1];
        }
        return ans;
    }

    public static void printOutTable(ArrayList<String> bRGC, String[] kids, HashMap<String, String> swappedKids){
        System.out.println("\n---------------------------------------------------------");
        System.out.println("Index | Gray Code |  Child(ren) in Photo  | Action");
        System.out.println("---------------------------------------------------------");

        //Max string length of kids in picture
        int maxLength = 0;
        for(int i = 1; i < bRGC.size(); ++i){
            maxLength = Math.max(maxLength, kidsInPhoto(kids, bRGC.get(i)).length());
        }

        //Makes sure the spacing lines up with the categories for the table and how each column should look when table is printed
        for(int i = 1; i < bRGC.size(); ++i){
            String column = "";
            String gC = bRGC.get(i);

            //Index column
            column += "  " + i + (i/10 == 0 ? "   " : "  ");

            //Gray Code column
            column += "|    " + gC;

            //Child(ren) in Photo column
            column += "   | " + kidsInPhoto(kids, gC);
            int currentLength = kidsInPhoto(kids, gC).length();
            int extraSpace = maxLength - currentLength;
            for(int j = 0; j < extraSpace; ++j){
                column += " ";
            }

            //Action column
            column += " | " + swappedKids.get(gC);

            //call to print out each column of the table
            System.out.println(column);
        }
    }
}