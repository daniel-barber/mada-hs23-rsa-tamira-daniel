import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAGenerator {
    public static void main(String[] args) {

        //Random generator
        SecureRandom random = new SecureRandom();

        //Set bitLength to size analog bitLength of example in sk.txt
        int bitLength = 1024;

        //Generate 2 prime numbers
        BigInteger p = BigInteger.probablePrime(bitLength,random);
        BigInteger q = BigInteger.probablePrime(bitLength,random);

        //Multiply prime numbers to calculate n
        BigInteger n = p.multiply(q);

        System.out.println(p);
        System.out.println(q);
        System.out.println(n);
    }
}
