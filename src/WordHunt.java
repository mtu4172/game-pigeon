import java.util.*;

public class WordHunt {
    public static char[][] board = new char[4][4];
    public static List<String> solution = new ArrayList<>();
    public static int count = 0;
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String letters;
        boolean[][] traversed = new boolean[4][4];

        input: while (true) {
            System.out.println("Input letters left to right, top to bottom: ");
            letters = in.next().toUpperCase();
            if (letters.length() != 16) {
                System.out.println("Error: String must be 16 characters long\n");
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

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = letters.charAt(i*4+j);
                traversed[i][j] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                traverse(i, j, traversed, "");
            }
        }

        String[] sorted = solution.toArray(new String[0]);
        Arrays.sort(sorted, (a, b) -> Integer.compare(b.length(), a.length()));
        for (String s : sorted)
            System.out.println(s);
        System.out.println("Number of words: " + count);
    }

    public static void traverse (int x, int y, boolean[][] traversed, String word) throws Exception {
        boolean[][] updTraversed = new boolean[4][4];
        for (int i = 0; i < 4; i++)
            System.arraycopy(traversed[i], 0, updTraversed[i], 0, 4);
        if (updTraversed[x][y])
            return;
        word += board[x][y];
        updTraversed[x][y] = true;

        if (WordChecker.validWord(word)) {
            if (!solution.contains(word)) {
                solution.add(word);
                count++;
            }
        }
        if (!WordChecker.validWordFrag(word))
            return;

        if (y > 0) {
            if (x > 0)
                traverse(x-1,y-1, updTraversed, word);
            traverse(x,y-1, updTraversed, word);
            if (x < 3)
                traverse(x+1,y-1, updTraversed, word);
        }
        if (x < 3)
            traverse(x+1,y, updTraversed, word);
        if (y < 3) {
            if (x < 3)
                traverse(x+1,y+1, updTraversed, word);
            traverse(x,y+1, updTraversed, word);
            if (x > 0)
                traverse(x-1,y+1, updTraversed, word);
        }
        if (x > 0)
            traverse(x-1,y, updTraversed, word);
    }
}
