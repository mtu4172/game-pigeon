import java.io.IOException;
import java.util.*;
public class Anagram {
    public static char[] pool = new char[6];
    public static List<String> solution = new ArrayList<>();
    public static int count = 0;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String letters;
        boolean[] traversed = new boolean[6];

        input: while (true) {
            System.out.println("Input letters: ");
            letters = in.next().toUpperCase();
            if (letters.length() != 6) {
                System.out.println("Error: String must be 6 characters long\n");
                continue;
            }
            for (int i = 0; i < letters.length(); i++) {
                if (letters.charAt(i) < 'A' || letters.charAt(i) > 'Z') {
                    System.out.println("Error: String must only contain letters\n");
                    continue input;
                }
            }
            break;
        }
        for (int i = 0; i < 6; i++) {
            pool[i] = letters.charAt(i);
            traversed[i] = false;
        }
        for (int i = 0; i < 6; i++) {
            easyTraverse(i, traversed, "");
        }
        String[] sorted = solution.toArray(new String[0]);
        Arrays.sort(sorted, (a, b) -> Integer.compare(b.length(), a.length()));
        for (String s : sorted)
            System.out.println(s);
        System.out.println("Number of words: " + count);
    }
    public static void easyTraverse (int x, boolean[] traversed, String word) throws IOException {
        boolean[] updTraversed = new boolean[6];
        System.arraycopy(traversed, 0, updTraversed, 0, 6);
        word += pool[x];
        updTraversed[x] = true;
        if (WordChecker.validWord(word)) {
            if (!solution.contains(word)) {
                solution.add(word);
                count++;
            }
        }
        if (!WordChecker.validWordFrag(word))
            return;

        for (int i = 0; i < 6; i++) {
            if (!updTraversed[i]) {
                easyTraverse(i, updTraversed, word);
            }
        }
    }
}
