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
        String pk = readFile("pk");
        if (pk != null) {
            pk = pk.substring(1, pk.length() - 1);
            String[] pkParts = pk.split(",");
            if (pkParts.length == 2) {
                n = new BigInteger(pkParts[0]);
                e = new BigInteger(pkParts[1]);
            }
        }


        String text = readFile("text");


        System.out.println("unencrypted text: " + text);
        System.out.println("public key: " + pk);

        System.out.println("n: " + n);
        System.out.println("e: " + e);

    }

    private static String readFile(String fileName) {
        String input;
        try (
            BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/" + fileName + ".txt"))) {
            input = textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find text.txt in /resources or file not compatible.");
        }
        return input;
    }


}
