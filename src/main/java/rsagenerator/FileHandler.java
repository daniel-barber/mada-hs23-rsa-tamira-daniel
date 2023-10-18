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
}
