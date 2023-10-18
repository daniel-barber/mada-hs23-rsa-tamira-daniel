package rsagenerator;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextEncryptorTest {

    @Test
    public void testEncrypt(){
        //test encryption with values from serie1 A26.1

        //Given
        BigInteger x = BigInteger.valueOf(9);
        BigInteger n = BigInteger.valueOf(11);
        BigInteger e = BigInteger.valueOf(25);

        //When
        BigInteger result = SchnelleExponentiation.quickExpo(x,n,e);

        //Then
        assertEquals(BigInteger.valueOf(1),result);
    }
}
