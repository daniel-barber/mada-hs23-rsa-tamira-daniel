package rsagenerator;

import java.math.BigInteger;

public class SchnelleExponentiation {
    public static BigInteger quickExpo(BigInteger base, BigInteger mod, BigInteger exponent) {
        //exponent e in binary Konvertieren und Länge bestimmen für i
        String binaryString = exponent.toString(2);
        int i = binaryString.length() - 1;

        //initialisierung
        BigInteger h = BigInteger.ONE;
        BigInteger k = base;


        while (i >= 0) {
            if (binaryString.charAt(i) == '1') {
                h = (h.multiply(k)).mod(mod);
            }
            k = k.modPow(BigInteger.valueOf(2), mod);
            i--;
        }

        return h;

    }
}
