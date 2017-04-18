
import java.util.HashMap;
import java.util.ArrayList;

class Item {
    int cost;
    int weight;
    static final Item EMPTY = new Item(0, 0);
    Item (int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }
    public String toString() {
        return "(" + cost + ", " + weight + ")";
    }
    public int getCost() {
        return this.cost;
    }

    public int getWeight() {
        return this.weight;
    }
}

class ItemList { // Ryan's idea

    ArrayList<Item> items = new ArrayList<Item>();

    ItemList() {
        this.items = new ArrayList<Item>();
    }

    ItemList(ArrayList<Item> items) {
        this.items = items;
    }

    ItemList(Item item) {
        this.items.add(item);
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
            throw new IllegalArgumentException(usage());
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
                throw new IllegalArgumentException(usage());
            }
        }

        int budget = Integer.parseInt(args[args.length - 1]);
        // System.out.println(budget);
        ItemList[][] solvedSols = new ItemList[prices.length + 1][budget + 1];
        Item[] allItems = new Item[prices.length];
        for(int i = 0; i < allItems.length; i++) {
            allItems[i] = new Item(prices[i], weights[i]);
            // System.out.println(allItems[i]);
        }
        ItemList answers = optimize(budget, allItems.length, solvedSols, allItems);
        // System.out.println(answers);
        for(Item i : answers.getItems()) {
            System.out.println("$" + i.cost + " / " + i.weight + " pounds");
        }
        System.out.println(answers.items.size() + " items" +  " / " + "$" + answers.getTotalCost() + " / " + answers.getTotalWeight() + " pounds");
    }

    public static String usage() {
        return "Negative numbers and zero are not allowed. The number of arguments must be odd. Example: \n" +
            "java SumoSolver 48 51 49 52 55 99 100";
    }


    public static ItemList optimize(int remainingMoney, int items, ItemList[][] solvedSols, Item[] allItems) {

        if (remainingMoney <= 0 || items == 0) {
            return new ItemList();
        }

        if (solvedSols[items][remainingMoney] != null) { // returned memoized value
            return solvedSols[items][remainingMoney];
        }

        if (allItems[items - 1].cost > remainingMoney) {
            ItemList answer = new ItemList(optimize(remainingMoney, items - 1, solvedSols, allItems).getItems());
            solvedSols[items - 1][remainingMoney] = answer;
            return answer;
        } else {
            ItemList possibility1 = new ItemList(optimize(remainingMoney - allItems[items - 1].cost, items - 1, solvedSols, allItems).getItems()).addItem(allItems[items - 1]);
            ItemList possibility2 = new ItemList(optimize(remainingMoney, items - 1, solvedSols, allItems).getItems());
            ItemList answer = max(possibility1, possibility2);
            solvedSols[items][remainingMoney] = answer;
            return answer;
        }
    }

    public static ItemList max(ItemList one, ItemList two) {
        return one.getTotalWeight() > two.getTotalWeight() ? one : two;
    }
}
