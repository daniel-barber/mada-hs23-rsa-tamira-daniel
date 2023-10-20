package rsagenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class FileHandler {

    static BigInteger[] readChiffreFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException(fileName + " is empty.");
            }

            String[] parts = line.split(",");
            BigInteger[] chiffre = new BigInteger[parts.length];

            for (int i = 0; i < parts.length; i++) {
                chiffre[i] = new BigInteger(parts[i]);
            }

            return chiffre;
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't read " + fileName + ": " + ex.getMessage());
        }
    }

    static String readTextFile(String fileName) {
        String input;
        try (
            BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            input = textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find " + fileName + " in /resources folder or file not in valid format");
        }
        if (!isAscii(input)) {
            throw new RuntimeException(fileName + " contains non-ASCII characters");
        }

        return input;
    }

    public static BigInteger[] readKey(String fileName) {
        String Key = readTextFile(fileName);
        return parseKey(Key);
    }

    private static BigInteger[] parseKey(String Key) {
        Key = Key.substring(1, Key.length() - 1);
        String[] keyParts = Key.split(",");
        if (keyParts.length != 2) {
            throw new RuntimeException("Invalid Key format");
        }

        BigInteger n = new BigInteger(keyParts[0]);
        BigInteger d = new BigInteger(keyParts[1]);

        return new BigInteger[] {n, d};
    }

    static void writeFile(BigInteger[] encryptedAscii, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/" + fileName))) {
            for (int i = 0; i < encryptedAscii.length; i++) {
                writer.write(encryptedAscii[i].toString());
                if (i < encryptedAscii.length - 1) {
                    writer.write(",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTextFile(String text, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/" + fileName))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeKey(BigInteger a, BigInteger b, String fileName) throws IOException {
        try (BufferedWriter skWriter = new BufferedWriter(new FileWriter("target/" + fileName))) {
            skWriter.write("(" + a + "," + b + ")");
        } catch (IOException ex) {
            throw new IOException("Write to " + fileName + "failed.", ex);

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

    static int[] toAsciiCode(String text) {
        int[] asciiCodes = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiCodes[i] = (int) text.charAt(i);
        }
        return asciiCodes;
    }

    public static String fromAsciiCode(int[] asciiCodes) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int code : asciiCodes) {
            stringbuilder.append((char) code);
        }
        return stringbuilder.toString();
    }
}
