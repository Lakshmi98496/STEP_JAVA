import java.util.*;

public class CaseConverter {

    public static char toUpper(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char)(ch - 32);
        }
        return ch;
    }

    public static char toLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 32);
        }
        return ch;
    }

    public static String convertToUpper(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(toUpper(text.charAt(i)));
        }
        return result.toString();
    }

    public static String convertToLower(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(toLower(text.charAt(i)));
        }
        return result.toString();
    }

    public static String convertToTitleCase(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                result.append(ch);
                newWord = true;
            } else {
                if (newWord) {
                    result.append(toUpper(ch));
                    newWord = false;
                } else {
                    result.append(toLower(ch));
                }
            }
        }
        return result.toString();
    }

    public static void compareWithBuiltIn(String original) {
        System.out.printf("%-20s %-30s %-30s%n", "Conversion", "Manual Result", "Built-in Result");
        System.out.println("---------------------------------------------------------------------------------------------");
        String manualUpper = convertToUpper(original);
        String builtInUpper = original.toUpperCase();
        System.out.printf("%-20s %-30s %-30s%n", "Uppercase", manualUpper, builtInUpper);

        String manualLower = convertToLower(original);
        String builtInLower = original.toLowerCase();
        System.out.printf("%-20s %-30s %-30s%n", "Lowercase", manualLower, builtInLower);

        String manualTitle = convertToTitleCase(original);
        String builtInTitle = toTitleCaseBuiltIn(original);
        System.out.printf("%-20s %-30s %-30s%n", "Title Case", manualTitle, builtInTitle);
    }

    public static String toTitleCaseBuiltIn(String text) {
        String[] words = text.toLowerCase().split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1)).append(" ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String input = scanner.nextLine();

        compareWithBuiltIn(input);
    }
}