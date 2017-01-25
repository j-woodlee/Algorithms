import java.io.*;
import java.util.*;


public class Select {
    public static void main(String[] args) throws Exception {
        BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
        String s = stdIn.readLine();// grab the first line (or null, if end-of-file)
        ArrayList<Integer> numList = new ArrayList<Integer>();

        while ( s != null ) { // while not end-of-file
            numList.add(Integer.parseInt(s));
            s = stdIn.readLine(); // grab the next line (or null)
        }

        System.out.println(findKthLargest(numList, Integer.parseInt(args[0])));
    }

    public static int findKthLargest (List<Integer> numList, int k) {
        if (k < 1 || numList == null || k > numList.size() ) {
            throw new IllegalArgumentException();
        }

        int pivotIndex = (int)(Math.random() * numList.size());
        //System.out.println("; " + pivotIndex);
        swap(numList, pivotIndex, numList.size() - 1);
        pivotIndex = partition(numList, 0, numList.size() - 1);


        if ((k-1) < pivotIndex) {

            return findKthLargest ( numList.subList(0, pivotIndex) , k );

        } else if ((k-1) > pivotIndex) {

            return findKthLargest(  numList.subList(pivotIndex + 1, numList.size()) , (k - 1 - pivotIndex) );

        } else {//equal

            return numList.get((k-1));
        }
    }

    public static int partition(List<Integer> numList, int begin, int end) { //returns the index of where the pivot should be
        int pivotValue = numList.get(end);
        int swapIndex = begin;

        for (int i = begin; i < end; i++) {
            if (numList.get(i) <= pivotValue) {
                swap(numList, swapIndex, i);
                swapIndex++;
            }
        }
        swap(numList, swapIndex, end);
        return swapIndex;
    }

    public static void swap(List<Integer> numList, int iOne, int iTwo) {
        int temp = numList.get(iOne);
        numList.set(iOne, numList.get(iTwo));
        numList.set(iTwo, temp);
    }

}
