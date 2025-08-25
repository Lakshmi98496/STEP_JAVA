import java.util.*;

public class StringPerformance {

    public static long[] testStringConcat(int iterations, String sample) {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += sample;
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, result.length()};
    }

    public static long[] testStringBuilder(int iterations, String sample) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(sample);
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }

    public static long[] testStringBuffer(int iterations, String sample) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append(sample);
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }

    public static void displayResults(long[] concat, long[] builder, long[] buffer) {
        System.out.printf("%-20s %-20s %-20s%n", "Method", "Time (ms)", "Final Length");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20s %-20d %-20d%n", "String (+)", concat[0], concat[1]);
        System.out.printf("%-20s %-20d %-20d%n", "StringBuilder", builder[0], builder[1]);
        System.out.printf("%-20s %-20d %-20d%n", "StringBuffer", buffer[0], buffer[1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String sample = "abc";

        long[] concatResult = testStringConcat(iterations, sample);
        long[] builderResult = testStringBuilder(iterations, sample);
        long[] bufferResult = testStringBuffer(iterations, sample);

        displayResults(concatResult, builderResult, bufferResult);
    }
}