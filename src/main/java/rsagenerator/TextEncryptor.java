package rsagenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class TextEncryptor {
    public static void main(String[] args) {

        //öffentlicher Schlüssel ist in Format (n,e)
        BigInteger n;
        BigInteger e;

        //public key einlesen und in n und e einspeichern
        String pk = FileHandler.readFile("pk");
        BigInteger[] privateKeyValues = FileHandler.readPrivateKey("pk");
        n = privateKeyValues[0];
        e = privateKeyValues[1];

        //text.txt einlesen und String in Ascii in int Array speichern
        String text = FileHandler.readFile("text");
        int[] textInAscii = toAscii(text);

        //Loop zur Verschlüsselung der Ascii Werte mit der schnellen exponentation
        BigInteger[] encrytedAscii = new BigInteger[text.length()];
        for (int i = 0; i < textInAscii.length; i++) {
            encrytedAscii[i] = encrypt(BigInteger.valueOf(textInAscii[i]), n, e);
        }

        //output to chiffre.txt
        FileHandler.writeFile(encrytedAscii);

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

    private static int[] toAscii(String text) {
        int[] asciiCodes = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiCodes[i] = (int) text.charAt(i);
        }
        return asciiCodes;
    }

    public static BigInteger encrypt(BigInteger x, BigInteger n, BigInteger e) {
        //exponent e in binary Konvertieren und Länge bestimmen für i
        String binaryString = e.toString(2);
        int i = binaryString.length() - 1;

        //initialisierung
        BigInteger h = BigInteger.ONE;
        BigInteger k = x;


        while (i >= 0) {
            if (binaryString.charAt(i) == '1') {
                h = (h.multiply(k)).mod(n);
            }
            k = k.modPow(BigInteger.valueOf(2), n);
            i--;
        }

        return h;

    }


}
