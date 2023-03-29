
public class easyinversioncount {

    public static void main(String[] args) {
        int[] array = new int[]{3, 2, 1};

        System.out.println("There are " + arrayBubbleSort(array) + " inversions in " + printOutArray(array));
    }

    //performs a bubble sort (0(n^2)) through the array to find how many inversions there are
    public static int arrayBubbleSort(int a[]) {
        //length of array
        int s = a.length;
        //counter
        int c = 0;

        for (int i = 0; i < s - 1; ++i) {
            for (int j = 0; j < s - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    ++c;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return c;
    }

    public static String printOutArray(int[] a) {
        //length of array
        int s = a.length;
        //open bracket to print out the array
        String sequence = "[";
        for (int i = 0; i < s; ++i) {
            sequence += (a[i]);
            sequence += i == s - 1 ? "" : " ";
        }
        //close bracket for end of array
        sequence += "]";

        return sequence;
    }
}