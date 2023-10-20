package rsagenerator;

import java.math.BigInteger;

public class TextEncryptor {
    public static void main(String[] args) {

        //öffentlicher Schlüssel ist in Format (n, e)
        BigInteger n;
        BigInteger e;

        //public key einlesen und in n und e einspeichern
        BigInteger[] privateKeyValues = FileHandler.readKey("pk.txt");
        n = privateKeyValues[0];
        e = privateKeyValues[1];

        //text.txt einlesen und String in Ascii in int Array speichern
        String text = FileHandler.readTextFile("text.txt");
        int[] textInAscii = FileHandler.toAsciiCode(text);

        //Loop zur Verschlüsselung der Ascii Werte mit der schnellen Exponentiation
        BigInteger[] encryptedAscii = new BigInteger[text.length()];
        for (int i = 0; i < textInAscii.length; i++) {
            encryptedAscii[i] = SchnelleExponentiation.quickExpo(BigInteger.valueOf(textInAscii[i]), n, e);
        }

        //output to chiffre.txt
        FileHandler.writeFile(encryptedAscii, "chiffre.txt");
        System.out.println("text.txt wurde erfolgreich verschlüsselt und in /target/chiffre.txt gespeichert.");
    }
}
