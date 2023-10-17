package rsagenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RSAGeneratorTest {

    private RSAGenerator rsaGenerator;

    @BeforeEach
    public void setUp(){
      //  rsaGenerator = new RSAGenerator();
    }

    @Test
    public void testGeneratePrimeNumber(){
        //Given
        BigInteger prime;

        //When
        prime = RSAGenerator.generatePrimeNumber();

        //Then
        assertTrue(prime.isProbablePrime(100));
    }

    @Test
    public void testDBerechner(){
        //Given
        BigInteger n = new BigInteger("3233");
        BigInteger e = new BigInteger("17");

        //When
        BigInteger d = RSAGenerator.dBerechner(n,e);
        BigInteger pMinus1qMinus1 = (new BigInteger("61")).multiply(new BigInteger("53")); // Example (p-1)*(q-1)

        //Then
        assertTrue(d.multiply(e).mod(pMinus1qMinus1).equals(BigInteger.ONE));
        assertTrue(d.compareTo(BigInteger.ZERO) > 0);

    }
}
