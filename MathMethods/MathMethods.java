import java.math.BigInteger;

public class MathMethods {

    public static void main(String[] args) {
        // System.out.println(factorial(4));
        // System.out.println(fibonacci(7));
        // System.out.println(gcd(1,2));
        // System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17));
        System.out.println(2*2*3*3*5*5*11*13);
        System.out.println(2*3*3*3*7*13*17);
        System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17));
        System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17)/2*2*3*3*5*5*11*13);
        System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17)/2*3*3*3*7*13*17);
        // System.out.println(2*3*3*13*2*5*5*11*13*7*17);
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
        long r = m % n;
        if(r == 0) {
            return n;
        }
        return gcd(n, r);
    }

    static long lcm(long m, long n) {
        return (m*n)/(gcd(m,n));
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
