import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAGenerator {
    public static void main(String[] args) {

        //Random generator
        SecureRandom random = new SecureRandom();

        //Set bitLength to size analog bitLength of example in sk.txt
        int bitLength = 1024;

        //Generate 2 prime numbers
        BigInteger prime1 = BigInteger.probablePrime(bitLength,random);
        BigInteger prime2 = BigInteger.probablePrime(bitLength,random);

        System.out.println(prime1);
        System.out.println(prime2);
    }
}
