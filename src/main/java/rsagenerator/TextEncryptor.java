package rsagenerator;

import java.math.BigInteger;

public class TextEncryptor {
    public static void main(String[] args) {

        //öffentlicher Schlüssel ist in Format (n,e)
        BigInteger n;
        BigInteger e;

        //public key einlesen und in n und e einspeichern
        String pk = FileHandler.readTextFile("pk");
        BigInteger[] privateKeyValues = FileHandler.readKey("pk");
        n = privateKeyValues[0];
        e = privateKeyValues[1];

        //text.txt einlesen und String in Ascii in int Array speichern
        String text = FileHandler.readTextFile("text");
        int[] textInAscii = FileHandler.toAsciiCode(text);

        //Loop zur Verschlüsselung der Ascii Werte mit der schnellen exponentation
        BigInteger[] encrytedAscii = new BigInteger[text.length()];
        for (int i = 0; i < textInAscii.length; i++) {
            encrytedAscii[i] = SchnelleExponentiation.quickExpo(BigInteger.valueOf(textInAscii[i]), n, e);
        }

        //output to chiffre.txt
        FileHandler.writeFile(encrytedAscii, "chiffre.txt");

        System.out.println("unencrypted text: " + text);
        System.out.println("public key: " + pk);

        System.out.println("n: " + n);
        System.out.println("e: " + e);
        for (int element : textInAscii) {
            System.out.print(element + ",");
        }
        System.out.println();
        for (BigInteger element : encrytedAscii) {
            System.out.print(element + ",");
        }

    }




}
