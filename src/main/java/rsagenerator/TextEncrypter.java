package rsagenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextEncrypter {
    public static void main(String[] args) {
        String text = readFile();

    }

    private static String readFile() {
        String text;
        try (BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/text.txt"))) {

            while ((text = textReader.readLine()) != null) {
                System.out.println("unencrypted text: " + text);
            }
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find text.txt in /resources");
        }
        return  text;
    }


}
