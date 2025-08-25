import java.util.*;

public class ManualReplace {

    public static List<Integer> findOccurrences(String text, String target) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(target);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(target, index + target.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String target, String replacement) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - target.length() &&
                text.substring(i, i + target.length()).equals(target)) {
                result.append(replacement);
                i += target.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String manualResult, String original, String target, String replacement) {
        String builtInResult = original.replace(target, replacement);
        return manualResult.equals(builtInResult);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the main text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String target = scanner.nextLine();

        System.out.print("Enter the replacement substring: ");
        String replacement = scanner.nextLine();

        List<Integer> positions = findOccurrences(text, target);
        System.out.println("Occurrences found at positions: " + positions);

        String manualResult = manualReplace(text, target, replacement);
        System.out.println("Manual Replacement Result: " + manualResult);

        boolean isSame = compareWithBuiltIn(manualResult, text, target, replacement);
        System.out.println("Built-in Replacement Result: " + text.replace(target, replacement));
        System.out.println("Do both results match? " + isSame);
    }
}