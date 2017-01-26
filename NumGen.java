public class NumGen {
    public static void main(String[] args) {
            for (int i = 1; i <= 5000000; i++) {
                System.out.println((int)(Math.random() * 5000000) + 1);
            }
    }
}
