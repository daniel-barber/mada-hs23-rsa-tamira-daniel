package rsagenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class TextEncryptor {
    public static void main(String[] args) {

        //öffentlicher Schlüssel ist in Format (n,e)
        BigInteger n = null;
        BigInteger e = null;

        //public key einlesen und in n und e einspeichern
        String pk = readFile("pk");
        if (pk != null) {
            pk = pk.substring(1, pk.length() - 1);
            String[] pkParts = pk.split(",");
            if (pkParts.length == 2) {
                n = new BigInteger(pkParts[0]);
                e = new BigInteger(pkParts[1]);
            }
        }

        //text.txt einlesen und String in Ascii in int Array speichern
        String text = readFile("text");
        int[] textInAscii = toAscii(text);

        //Loop zur Verschlüsselung der Ascii Werte mit der schnellen exponentation
        int[] encrytedAscii = new int[text.length()];
        for (int i=0;i<textInAscii.length;i++) {
            encrytedAscii[i]=encrypt(textInAscii[i],n,e);
        }

        System.out.println("unencrypted text: " + text);
        System.out.println("public key: " + pk);

        System.out.println("n: " + n);
        System.out.println("e: " + e);
        for (int element : textInAscii) {
            System.out.print(element + ",");
        }
        System.out.println();
        for (int element : encrytedAscii) {
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

    private static String readFile(String fileName) {
        String input;
        try (
            BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/" + fileName + ".txt"))) {
            input = textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find text.txt in /resources or file not compatible.");
        }
        if (!isAscii(input)) {
            throw new RuntimeException(fileName + ".txt contains non-ASCII characters");
        }

        return input;
    }

    public static boolean isAscii(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    public static int encrypt(int x, BigInteger n, BigInteger e){
        //exponent e in binary Konvertieren und Länge bestimmen für i
        String binaryString = e.toString(2);
        int i = binaryString.length()-1;

        //initialisierung
        BigInteger h = BigInteger.ONE;
        BigInteger k = BigInteger.valueOf(x);


        while(i>=0){
            if(binaryString.charAt(i)=='1'){
                h=(h.multiply(k)).mod(n);
            }
            k=k.modPow(BigInteger.valueOf(2),n);
            i--;
        }

        return h.intValue();

    }


}
