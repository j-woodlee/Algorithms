import java.math.BigInteger;

public class MathMethods {

    public static void main(String[] args) {
        // System.out.println(factorial(4));
        // System.out.println(fibonacci(7));

    }

    static BigInteger factorial(int n) {
        if (n == 1 || n == 0) {
            return new BigInteger("1");
        }
        // compareTo: -1, 0 or 1 as this BigInteger is numerically less than, equal to, or greater than val.
        BigInteger nBigInt = new BigInteger(n + "");
        BigInteger product = new BigInteger("1");

        while(nBigInt.compareTo(BigInteger.ZERO) > 0) {
            product = product.multiply(nBigInt);
            // System.out.println(product);
            nBigInt = nBigInt.subtract(new BigInteger("1"));
        }
        return product;
    }

    static BigInteger fibonacci(int n) {
        BigInteger nminus1th = BigInteger.ZERO;
        BigInteger nth = BigInteger.ONE;

        BigInteger next;
        for (int i = 0; i < n; i++) {
            next = nminus1th.add(nth);
            nminus1th = nth;
            nth = next;
        }
        return nth;
    }

    static long gcd(long m, long n) {
        return 0;
    }
    
    static long lcm(long m, long n) {
        return 0;
    }

    static double poly(double x, double[] coeff) {
        return 0;
    }

    static double power(double x, int n) {
        return 0;
    }

    static double root(int n, double x, double epsilon) {
        return 0;
    }

    static double sqrt(double x, double epsilon) {
        return 0;
    }

}
