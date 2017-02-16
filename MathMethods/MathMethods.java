import java.math.BigInteger;

public class MathMethods {

    public static void main(String[] args) {
            String method = args[0];
            if (method.equals("factorial") && args.length == 2) {
                System.out.println(factorial(Integer.parseInt(args[1])));
            } else if (method.equals("fibonacci") && args.length == 2) {
                System.out.println(fibonacci(Integer.parseInt(args[1])));
            } else if (method.equals("gcd") && args.length == 3) {
                System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
            } else if (method.equals("lcm") && args.length == 3) {
                System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
            } else if (method.equals("poly")) {
                double x = Double.parseDouble(args[1]);
                double[] coeffs = new double[args.length - 2];
                for (int i = 2; i < args.length; i++) {
                    coeffs[i - 2] = Double.parseDouble(args[i]);
                }
                System.out.println(poly(x, coeffs));
            } else if (method.equals("power") && args.length == 3) {
                System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
            } else if (method.equals("root") && args.length == 4) {
                System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
            } else if (method.equals("sqrt") && args.length == 3) {
                System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
            } else {
                usage();
            }
    }

    public static void usage() {
        System.out.println("Make sure that the number of arguments passed is correct.  usage: java MathMethods <method> <args>");
    }

    static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

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
        if (n < 0) {
            throw new IllegalArgumentException();
        }
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
        m = m < 0 ? -m : m;
        n = n < 0 ? -n : n;
        long r = m % n;
        return r == 0 ? n : gcd(n, r);
    }

    static long lcm(long m, long n) {
        return (m*n)/gcd(m,n);
    }

    static double poly(double x, double[] coeff) {
        if (coeff.length <= 0 ) {
            throw new IllegalArgumentException();
        }
        double val = coeff[coeff.length - 1]; //val = 13
        for (int i = coeff.length - 2; i >= 0; i--) {
            val = val*x + coeff[i];
        }
        return val;
    }

    static double power(double x, int n) {

        if (n < 0) {
            return 1 / power(x, -n);
        }

        if (n == 0) {
            return 1;
        }
        double y = power(x, n / 2);
        if (n % 2 == 0) {
            return y*y;
        } else {
            return x*y*y;
        }
    }

    static double root(int n, double x, double epsilon) {

        double upperBound, lowerBound;
        if (x == 0) {
            return 0;
        }
        if(n <= 0 || epsilon == 0) {
            throw new IllegalArgumentException();
        }

        boolean neg = false;
        if (n % 2 == 1 && x < 0) {
            neg = true;
            x = -x;
        } else if (x < 0) {
            throw new IllegalArgumentException();
        }

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
        return neg ? -mid : mid;
    }

    static double sqrt(double x, double epsilon) {
        return root(2, x, epsilon);
    }
}
