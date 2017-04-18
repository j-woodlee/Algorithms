import java.util.ArrayList;
import java.io.*;

public class BucketSort {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Double> doubleArgs = new ArrayList<Double>();

        String val = br.readLine();
        double min = Double.parseDouble(val);
        double max = Double.parseDouble(val);

        while (val != null) {
            doubleArgs.add(Double.parseDouble(val));
            if (min > Double.parseDouble(val)) {
                min = Double.parseDouble(val);
            }
            if (max < Double.parseDouble(val)) {
                max = Double.parseDouble(val);
            }
            val = br.readLine();
        }

        ArrayList<ArrayList<Double>> answer = bucketSort(doubleArgs, max, min);
        doubleArgs.clear();
        for(ArrayList<Double> a : answer) {
            for(Double d : a) {
                doubleArgs.add(d.doubleValue());
            }
        }
        System.out.println(doubleArgs);
    }

    public static ArrayList<ArrayList<Double>> bucketSort(ArrayList<Double> numbers, double max, double min) {
        ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < numbers.size(); i++) {
            buckets.add(new ArrayList<Double>());
        }

        double bucketSize = (max - min) / numbers.size();
        int designatedBucket;
        double currentVal;
        for(Double d : numbers) {
            currentVal = d.doubleValue();
            designatedBucket = (int) ((currentVal - min) / bucketSize);
            // System.out.println(designatedBucket);
            if(designatedBucket > (buckets.size() - 1)) {
                designatedBucket = buckets.size() - 1;
            }
            buckets.get(designatedBucket).add(currentVal);
        }

        for(ArrayList<Double> ad : buckets) {
            ad = insertSort(ad);
        }
        return buckets;
    }

    public static ArrayList<Double> insertSort(ArrayList<Double> arrList) {
        double temp;
        for (int i = 1; i < arrList.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(arrList.get(j) < arrList.get(j-1)){
                    temp = arrList.get(j);
                    arrList.set(j, arrList.get(j - 1));
                    arrList.set(j - 1, temp);
                }
            }
        }
        return arrList;
    }
}
