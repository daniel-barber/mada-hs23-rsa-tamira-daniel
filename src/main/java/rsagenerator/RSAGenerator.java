package rsagenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAGenerator {
    // Konfigurierbare Konstanten
    // Bitlänge für die Generierung der Primzahlen, analog Bitlänge des Keys in sk.txt gesetzt
    private static final int BIT_LENGTH = 1024;

    //Anzahl Iterationen für Miller-Rabin Funktion (Fehlerwahrscheinlichkeit verkleinern)
    private static final int MILLER_RABIN_ITERATIONS = 100;

    // Exponent e. Typischer e ist 3 oder 65537. Wir haben 3 gewählt, weil es klein und einfach ist
    private static final BigInteger PUBLIC_EXPONENT = new BigInteger("3");


    public static void main(String[] args) {
        //Variablen
        BigInteger p;
        BigInteger q;
        BigInteger n;
        BigInteger pn;
        BigInteger e = PUBLIC_EXPONENT;

        do {
            //Generate 2 prime numbers
            p = generatePrimeNumber();
            q = generatePrimeNumber();

            //Multiply prime numbers to calculate n
            n = p.multiply(q);

            //Calculate Phi von n
            pn = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        } while (!areCoprime(pn, e));

        //Calculate d with Euklidischer Algo
        BigInteger d = dBerechner(pn, e);

        //Write (n,d) to sk.txt
        try (BufferedWriter skWriter = new BufferedWriter(new FileWriter("target/sk.txt"))) {
            skWriter.write("(" + n + "," + d + ")");
        } catch (IOException ex) {
            throw new RuntimeException("Write to sk.txt failed.");
        }

        //Write (n,e) to pk.txt
        try (BufferedWriter skWriter = new BufferedWriter(new FileWriter("target/pk.txt"))) {
            skWriter.write("(" + n + "," + e + ")");
        } catch (IOException ex) {
            throw new RuntimeException("Write to pk.txt failed.");
        }
    }

    private static boolean areCoprime(BigInteger pn, BigInteger e) {
        return pn.gcd(e).equals(BigInteger.ONE);
    }

    static BigInteger generatePrimeNumber() {
        //Random generator
        SecureRandom random = new SecureRandom();

        //Prime number generator including check, regenerate if not prime
        BigInteger prime;

        do {
            prime = BigInteger.probablePrime(BIT_LENGTH, random);
        } while (!prime.isProbablePrime(MILLER_RABIN_ITERATIONS));

        return prime;
    }

    public static BigInteger dBerechner(BigInteger n, BigInteger e) {
        //Variablen
        BigInteger a = n;
        BigInteger b = e;
        BigInteger x;
        BigInteger y;
        BigInteger x0 = BigInteger.ONE;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger q;
        BigInteger r;

        //Umsetzung Euklidischer Algo
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

        //d positiv machen
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
