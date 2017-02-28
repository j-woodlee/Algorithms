
import java.util.HashMap;
import java.util.ArrayList;

class Item { // courtesy of Ryan
    int cost;
    int weight;
    Item (int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }
    public String toString() {
        return "(" + cost + ", " + weight + ")";
    }
}

class ItemList {

    ArrayList<Item> items = new ArrayList<Item>();

    ItemList() {
        this.items = new ArrayList<Item>();
    }

    ItemList(ArrayList<Item> items) {
        this.items = items;
    }

    public ItemList addItem(Item item) {
        items.add(item);
        return this;
    }

    public int getTotalCost() {
        int sum = 0;
        for (Item i : items) {
            sum += i.cost;
        }
        return sum;
    }

    public int getTotalWeight() {
        int sum = 0;
        for (Item i : items) {
            sum += i.weight;
        }
        return sum;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public String toString() {
        return this.items.toString();
    }
}


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
        ItemList[][] solvedSols = new ItemList[prices.length][budget];
        Item[] itemArray = new Item[prices.length];
        for(int i = 0; i < itemArray.length; i++) {
            itemArray[i] = new Item(prices[i], weights[i]);
        }
        System.out.println(optimize(prices, weights, budget, prices.length, solvedSols, itemArray));
    }

    public static void usage() {
        System.out.println("Negative numbers are not allowed and the number of arguments must be odd. Example: \n" +
            "java SumoSolver 48 51 49 52 55 99 100");
    }

    public static ItemList optimize(int[] prices, int[] weights, int remainingMoney, int items, ItemList[][] solvedSols, Item[] itemArray) {

        if (remainingMoney == 0 || items == 0) {
            return new ItemList();
        }

        if (solvedSols[items - 1][remainingMoney - 1] != null) { // returned memoized value
            return solvedSols[items - 1][remainingMoney - 1];
        }

        if (prices[items - 1] > remainingMoney) {
            ItemList answer = optimize(prices, weights, remainingMoney, items - 1, solvedSols, itemArray);
            solvedSols[items - 1][remainingMoney - 1] = answer;
            return answer;
        } else {
            ItemList possibility1 = new ItemList(optimize(prices, weights, remainingMoney - prices[items - 1], items - 1, solvedSols, itemArray).getItems()).addItem(itemArray[items - 1]);
            ItemList possibility2 = new ItemList(optimize(prices, weights, remainingMoney, items - 1, solvedSols, itemArray).getItems());
            ItemList answer = max(possibility1, possibility2);
            solvedSols[items - 1][remainingMoney - 1] = answer;
            return answer;
        }
    }
    public static ItemList max(ItemList one, ItemList two) {
        return one.getTotalWeight() >= two.getTotalWeight() ? one : two;
    }
}
