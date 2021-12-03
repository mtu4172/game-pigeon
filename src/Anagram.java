import java.io.IOException;
import java.util.*;
public class Anagram {
    public static char[] pool = new char[6];
    public static List<String> solution = new ArrayList<>();
    public static int count = 0;
    public static void AN() throws IOException {
        Scanner in = new Scanner(System.in);
        String letters;
        boolean[] visited = new boolean[6];

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
            visited[i] = false;
        }
        for (int i = 0; i < 6; i++) {
            easyTraverse(i, visited, "");
        }
        String[] sorted = solution.toArray(new String[0]);
        Arrays.sort(sorted, (a, b) -> Integer.compare(b.length(), a.length()));
        System.out.println("Number of words: " + count);
        System.out.println("Total possible score: " + Scorer.scoreAN(sorted));
        for (String s : sorted)
            System.out.println(s);
    }
    public static void easyTraverse (int x, boolean[] visited, String word) throws IOException {
        boolean[] visitedTemp = new boolean[6];
        System.arraycopy(visited, 0, visitedTemp, 0, 6);
        word += pool[x];
        visitedTemp[x] = true;
        if (WordChecker.validWord(word)) {
            if (!solution.contains(word)) {
                solution.add(word);
                count++;
            }
        }
        if (!WordChecker.validWordFrag(word))
            return;

        for (int i = 0; i < 6; i++) {
            if (!visitedTemp[i]) {
                easyTraverse(i, visitedTemp, word);
            }
        }
    }
}
