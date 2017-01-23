import java.io.*;
import java.util.ArrayList;

public class Select {
    public static void main(String[] args) throws Exception {
        BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
        String s = stdIn.readLine();// grab the first line (or null, if end-of-file)
        ArrayList<Integer> numList = new ArrayList<Integer>();
        int i = 0;
        while ( s != null ) { // while not end-of-file
            numList.add(Integer.parseInt(s));
            s = stdIn.readLine(); // grab the next line (or null)
            System.out.println(numList.get(i));
            i++;
        }
    }

    public static int findNthLargest (ArrayList<Integer> numList, int n) {
        return 0;
    }

}
