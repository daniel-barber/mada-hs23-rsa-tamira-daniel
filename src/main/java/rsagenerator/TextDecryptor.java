package rsagenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class TextDecryptor {
    public static void main(String[] args) {

        //private key ist in Format (n,d)
        BigInteger n = null;
        BigInteger d = null;

        //private key sk einlesen und in n und d einspeichern
        String sk = FileHandler.readFile("sk");
        if (sk != null) {
            sk = sk.substring(1, sk.length() - 1);
            String[] pkParts = sk.split(",");
            if (pkParts.length == 2) {
                n = new BigInteger(pkParts[0]);
                d = new BigInteger(pkParts[1]);
            }
        }
    }
}
