import java.util.Arrays;

public class fastinversioncount {

    public static void main(String[] args){

        int[] array0 = new int[] {3, 2, 1};
        int numInversions0 = arrayMergeSort(array0, 0, array0.length - 1);
        System.out.println("There are " + numInversions0 + " inversions in " + printOutArray(array0));
    }

    //performs a merge sort (O(nlogn)) through the array to find how many inversions there are
    private static int arrayMergeSort(int[] a, int leftHalf, int rightHalf) {
        //counter
        int c = 0;

        if (leftHalf < rightHalf) {
            int merge = (leftHalf + rightHalf) / 2;

            //left half inversions
            c += arrayMergeSort(a, leftHalf, merge);

            //right half inversions
            c += arrayMergeSort(a, merge + 1, rightHalf);

            //merge both halves inversions
            c += arrayMergeCounter(a, leftHalf, merge, rightHalf);
        }

        return c;
    }

    private static int arrayMergeCounter(int[] a, int leftIn, int middleIn, int rightIn) {
        int[] leftHalf = Arrays.copyOfRange(a, leftIn, middleIn + 1);
        int[] rightHalf = Arrays.copyOfRange(a, middleIn + 1, rightIn + 1);

        int i = 0, j = 0, k = leftIn, numSwaps = 0;

        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j])
                a[k++] = leftHalf[i++];
            else {
                a[k++] = rightHalf[j++];
                numSwaps += (middleIn + 1) - (leftIn + i);
            }
        }

        while (i < leftHalf.length)
            a[k++] = leftHalf[i++];

        while (j < rightHalf.length)
            a[k++] = rightHalf[j++];

        return numSwaps;
    }

    //Same print out function from easyinversioncount
    public static String printOutArray(int[] a){
        //length of array
        int s = a.length;
        //open bracket to print out the array
        String sequence = "[";
        for (int i=0; i < s; ++i) {
            sequence += (a[i]);
            sequence += i == s - 1 ? "" : " ";
        }
        //close bracket for end of array
        sequence += "]";
        return sequence;
    }
}