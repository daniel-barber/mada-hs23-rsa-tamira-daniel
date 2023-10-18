package rsagenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextEncryptor {
    public static void main(String[] args) {
        String pk = readFile("pk");
        String text = readFile("text");


        System.out.println("unencrypted text: " + text);
        System.out.println("public key: " + pk);

    }

    private static String readFile(String fileName) {
        String input;
        try (BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/"+fileName+".txt"))) {
            input= textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find text.txt in /resources or file not compatible.");
        }
        return  input;
    }


}
