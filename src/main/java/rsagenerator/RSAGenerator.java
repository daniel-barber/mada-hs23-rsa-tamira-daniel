package rsagenerator;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAGenerator {
    public static void main(String[] args) {

        //Generate 2 prime numbers
        BigInteger p = generatePrimeNumber();
        BigInteger q = generatePrimeNumber();

        //Multiply prime numbers to calculate n
        BigInteger n = p.multiply(q);

        //Calculate Phi von n
        BigInteger pn = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        //Typical e is 3 or 65537. We choose 3 because it's small and easy
        BigInteger e = new BigInteger("3");

        //Calculate d with Euklidischer Algo
        BigInteger d = dBerechner(pn, e);

        System.out.println(p);
        System.out.println(q);
        System.out.println(n);
        System.out.println(pn);
        System.out.println(d);
        System.out.println((e.multiply(d)).mod(pn).equals(BigInteger.ONE));

    }

    static BigInteger generatePrimeNumber() {

        //Random generator
        SecureRandom random = new SecureRandom();

        //Set bitLength to size analog bitLength of example in sk.txt
        int bitLength = 1024;

        //Set iteration amount for certainty of Miller-Rabin test
        int i = 100;

        //Prime number generator including check, regenerate if not prime
        BigInteger prime;
        do {
            prime = BigInteger.probablePrime(bitLength, random);
        } while (!prime.isProbablePrime(i));


        return prime;
    }

    public static BigInteger dBerechner(BigInteger n, BigInteger e) {
        BigInteger a = n;
        BigInteger b = e;

        //Check if GGT of n and e is 1
        if (!a.gcd(b).equals(BigInteger.ONE)) {
            throw new IllegalArgumentException("e and n are not coprime.");
        }

        BigInteger x;
        BigInteger y;
        BigInteger x0 = BigInteger.ONE;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger q;
        BigInteger r;

        while (!b.equals(BigInteger.ZERO)) {
            q = a.divide(b);
            r = a.mod(b);
            a = b;
            b = r;
            x = x0;
            y = y0;
            x0 = x1;
            y0 = y1;
            x1 = x.subtract(q.multiply(x1));
            y1 = y.subtract(q.multiply(y1));
        }
        BigInteger d = y0;

        if (a.equals(BigInteger.ONE)) {
            while (d.compareTo(BigInteger.ZERO) < 0) {
                d = d.add(n);
            }
            return d;
        } else {
            return BigInteger.valueOf(-1);
        }
    }

}
