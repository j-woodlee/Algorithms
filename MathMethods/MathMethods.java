import java.math.BigInteger;

public class MathMethods {

    public static void main(String[] args) {
        // System.out.println(factorial(4));
        // System.out.println(fibonacci(7));
        // System.out.println(gcd(1,2));
        // System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17));
        // System.out.println(2*2*3*3*5*5*11*13);
        // System.out.println(2*3*3*3*7*13*17);
        // System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17));
        // System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17)/2*2*3*3*5*5*11*13);
        // System.out.println(lcm(2*2*3*3*5*5*11*13,2*3*3*3*7*13*17)/2*3*3*3*7*13*17);
        // System.out.println(2*3*3*13*2*5*5*11*13*7*17);
        // System.out.println(poly(1,new double[]{2,9,6,13}));
        System.out.println(root(3,27,0.1));

        // System.out.println(((13 + 6) + 9) + 2);
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
        return r == 0 ? n : gcd(n, r);
    }

    static long lcm(long m, long n) {
        return (m*n)/(gcd(m,n));
    }

    static double poly(double x, double[] coeff) {
        double val = coeff[coeff.length - 1]; //val = 13
        for (int i = coeff.length - 2; i >= 0; i--) {
            val = val*x + coeff[i];
        }
        return val;
    }

    static double power(double x, int n) {
        return 0;
    }

    static double root(int n, double x, double epsilon) {
        double upperBound, lowerBound;

        if (x == 1.0) {
            return 1;
        } else if (x < 1.0) {
            upperBound = 1;
            lowerBound = 0;
        } else {
            upperBound = x;
            lowerBound = 1;
        }
        double mid = 0;
        while(upperBound - lowerBound > epsilon) {
            mid = ((upperBound - lowerBound) / 2.0) + lowerBound;
            if (Math.pow(mid,n) > x) {
                upperBound = mid;
            } else {
                lowerBound = mid;
            }
        }
        return mid;
    }

    static double sqrt(double x, double epsilon) {
        return root(2, x, epsilon);
    }
}
