import java.util.*;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                encrypted.append((char) ((ch - 'A' + shift) % 26 + 'A'));
            } else if (ch >= 'a' && ch <= 'z') {
                encrypted.append((char) ((ch - 'a' + shift) % 26 + 'a'));
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAsciiComparison(String original, String encrypted) {
        System.out.printf("%-10s %-10s %-10s %-10s%n", "Char", "ASCII", "EncChar", "EncASCII");
        System.out.println("------------------------------------------");
        for (int i = 0; i < original.length(); i++) {
            char o = original.charAt(i);
            char e = encrypted.charAt(i);
            System.out.printf("%-10c %-10d %-10c %-10d%n", o, (int) o, e, (int) e);
        }
    }

    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String inputText = scanner.nextLine();

        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();

        String encryptedText = encrypt(inputText, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("\nOriginal Text: " + inputText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        System.out.println("\nASCII Comparison:");
        displayAsciiComparison(inputText, encryptedText);

        boolean isValid = validateDecryption(inputText, decryptedText);
        System.out.println("\nDecryption Valid: " + isValid);
    }
}