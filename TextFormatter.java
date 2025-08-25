import java.util.*;

public class TextFormatter {

    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        return words;
    }

    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();
            if (gaps == 0 || j == words.size()) {
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) line.append(" ");
                }
                while (line.length() < width) line.append(" ");
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line.append(" ");
                        if (extraSpaces-- > 0) line.append(" ");
                    }
                }
            }
            lines.add(line.toString());
            i = j;
        }
        return lines;
    }

    public static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            int padding = (width - line.trim().length()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < padding; i++) sb.append(" ");
            sb.append(line.trim());
            centered.add(sb.toString());
        }
        return centered;
    }

    public static List<String> justifyWithConcat(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }

            int gaps = j - i - 1;
            String line = "";
            if (gaps == 0 || j == words.size()) {
                for (int k = i; k < j; k++) {
                    line += words.get(k);
                    if (k < j - 1) line += " ";
                }
                while (line.length() < width) line += " ";
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words.get(k);
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line += " ";
                        if (extraSpaces-- > 0) line += " ";
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines;
    }

    public static void displayFormattedText(List<String> lines, String title) {
        System.out.println("\n📄 " + title);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.printf("Line %2d (%3d chars): %s%n", i + 1, line.length(), line);
        }
    }

    public static void comparePerformance(List<String> words, int width) {
        long startSB = System.nanoTime();
        List<String> sbLines = justifyText(words, width);
        long endSB = System.nanoTime();

        long startConcat = System.nanoTime();
        List<String> concatLines = justifyWithConcat(words, width);
        long endConcat = System.nanoTime();

        System.out.println("\n⏱️ Performance Comparison:");
        System.out.printf("StringBuilder Time : %d ns%n", endSB - startSB);
        System.out.printf("String Concatenation Time : %d ns%n", endConcat - startConcat);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to format: ");
        String text = scanner.nextLine();

        System.out.print("Enter desired line width: ");
        int width = scanner.nextInt();

        List<String> words = splitWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(justified, width);

        System.out.println("\n📝 Original Text:\n" + text);
        displayFormattedText(justified, "Left-Justified Text");
        displayFormattedText(centered, "Center-Aligned Text");
        comparePerformance(words, width);
    }
}