package rsagenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class FileHandler {

    static String readFile(String fileName) {
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

    public static BigInteger[] readPrivateKey(String fileName) {
        String privateKey = readFile(fileName);
        return parsePrivateKey(privateKey);
    }

    private static BigInteger[] parsePrivateKey(String privateKey) {
        privateKey = privateKey.substring(1, privateKey.length() - 1);
        String[] keyParts = privateKey.split(",");
        if (keyParts.length != 2) {
            throw new RuntimeException("Invalid private key format");
        }

        BigInteger n = new BigInteger(keyParts[0]);
        BigInteger d = new BigInteger(keyParts[1]);

        return new BigInteger[]{n, d};
    }

    static void writeFile(BigInteger[] encryptedAscii) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("target/chiffre.txt"))){
            for(int i=0;i<encryptedAscii.length;i++){
                writer.write(encryptedAscii[i].toString());
                if(i<encryptedAscii.length-1){
                    writer.write(",");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean isAscii(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

     static int[] toAscii(String text) {
        int[] asciiCodes = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiCodes[i] = (int) text.charAt(i);
        }
        return asciiCodes;
    }
}
