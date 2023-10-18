package rsagenerator;

import java.math.BigInteger;

public class TextDecrypt {
    public static void main(String[] args) {

        //private key ist in Format (n,d)
        BigInteger n;
        BigInteger d;

        //private key sk einlesen und in n und d einspeichern
        String pk = FileHandler.readFile("sk");
        BigInteger[] privateKeyValues = FileHandler.readPrivateKey("sk");
        n = privateKeyValues[0];
        d = privateKeyValues[1];
    }
}
