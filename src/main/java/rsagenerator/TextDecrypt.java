package rsagenerator;

import java.math.BigInteger;

public class TextDecrypt {
    public static void main(String[] args) {

        //private key ist in Format (n,d)
        BigInteger n;
        BigInteger d;

        //private key sk einlesen und in n und d einspeichern
        String sk = FileHandler.readTextFile("sk");
        BigInteger[] privateKeyValues = FileHandler.readKey("sk");
        n = privateKeyValues[0];
        d = privateKeyValues[1];

        //chiffre.txt einlesen
        BigInteger[] chiffre = FileHandler.readChiffreFile("chiffre");

        //Loop zur Entschlüsselung der Zeichen mit der schnellen Exponentiation
        int[] decryptedAscii = new int [chiffre.length];
        for (int i = 0; i < chiffre.length; i++) {
            decryptedAscii[i] = SchnelleExponentiation.quickExpo(chiffre[i], n, d).intValue();
        }

        //decryptedAscii zu Asciizeichen zurückwandeln
        String dText = FileHandler.fromAsciiCode(decryptedAscii);

        //output to text-d.txt
        FileHandler.writeTextFile(dText,"text-d");
    }
}
