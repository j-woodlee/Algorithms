public class PentagonProblem {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 0;
        int side1, side2, side3, side4, side5;
        while (count < 3628800) {
            count++;
            side1 = array[0] + array[1] + array[2];
            side2 = array[2] + array[3] + array[4];
            side3 = array[4] + array[5] + array[6];
            side4 = array[6] + array[7] + array[8];
            side5 = array[8] + array[9] + array[0];
            if(side1 == side2 && side1 == side3 && side1 == side4 && side1 == side5) {
                if( array[0] == 0 || array[1] == 0 ) {
                    for(int i : array) {
                        System.out.print(i);
                    }
                    System.out.print(" - Side Sum: " + side1);
                    System.out.println();
                }
            }
            array = nextPermutation(array);
        }
    }

    static int[] nextPermutation(int[] array) {

        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return array;
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return array;
    }
}
