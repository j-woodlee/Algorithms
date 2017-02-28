
import java.util.HashMap;

public class SumoSolver {
    public static void main(String[] args) {

        if (args.length % 2 != 1) {
            usage();
            return;
        }
        int[] weights = new int[(args.length - 1) / 2];
        int[] prices = new int[(args.length - 1) / 2];
        int priceIndex = 0;
        int weightIndex = 0;
        for (int i = 0; i < args.length - 1; i++) {
            int val = Integer.parseInt(args[i]);
            if (val > 0) {
                if (i % 2 == 0) {
                    prices[priceIndex] = val;
                    priceIndex++;
                } else {
                    weights[weightIndex] = val;
                    weightIndex++;
                }
            } else {
                usage();
                return;
            }
        }
        int budget = Integer.parseInt(args[args.length - 1]);
        Integer[][] solvedSols = new Integer[prices.length][budget];
        System.out.println(optimize(prices, weights, budget, prices.length, solvedSols));
    }

    public static void usage() {
        System.out.println("Negative numbers are not allowed and the number of arguments must be odd. Example: \n" +
            "java SumoSolver 48 51 49 52 55 99 100");
    }

    public static int optimize(int[] prices, int[] weights, int remainingMoney, int items, Integer[][] solvedSols) {
        if (remainingMoney == 0 || items == 0) {
            return 0;
        }

        if (solvedSols[items - 1][remainingMoney - 1] != null) { // returned memoized value
            return solvedSols[items - 1][remainingMoney - 1];
        }

        if (prices[items - 1] > remainingMoney) {
            int answer = optimize(prices, weights, remainingMoney, items - 1, solvedSols);
            solvedSols[items - 1][remainingMoney - 1] = answer;
            return answer;
        } else {
            int possibility1 = weights[items - 1] + optimize(prices, weights, remainingMoney - prices[items - 1], items - 1, solvedSols);
            int possibility2 = optimize(prices, weights, remainingMoney, items - 1, solvedSols);
            int answer = Math.max(possibility1, possibility2);
            if (answer == possibility1) {
                System.out.println("weight: " + weights[items - 1]+ " price: " + prices[items - 1]);
            }
            solvedSols[items - 1][remainingMoney - 1] = answer;
            return answer;
        }
    }
}
