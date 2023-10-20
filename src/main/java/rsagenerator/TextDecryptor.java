package rsagenerator;

import java.math.BigInteger;

public class TextDecryptor {
    public static void main(String[] args) {

        //private key ist in Format (n,d)
        BigInteger n;
        BigInteger d;

        //private key sk einlesen und in n und d einspeichern
        BigInteger[] privateKeyValues = FileHandler.readKey("sk.txt");
        n = privateKeyValues[0];
        d = privateKeyValues[1];

        //chiffre.txt einlesen
        BigInteger[] chiffre = FileHandler.readChiffreFile("chiffre.txt");

        //Loop zur Entschlüsselung der Zeichen mit der schnellen Exponentiation
        int[] decryptedAscii = new int[chiffre.length];
        for (int i = 0; i < chiffre.length; i++) {
            decryptedAscii[i] = SchnelleExponentiation.quickExpo(chiffre[i], n, d).intValue();
        }

        //decryptedAscii zu Ascii-Zeichen zurückwandeln
        String dText = FileHandler.fromAsciiCode(decryptedAscii);

        //output to text-d.txt
        FileHandler.writeTextFile(dText, "text-d.txt");

        //Finisch Message
        System.out.println("chiffre.txt wurde entschlüsselt und in /target/text-d.txt gespeichert.");
        System.out.println("Die Nachricht lautet: " + dText);

    }
}
