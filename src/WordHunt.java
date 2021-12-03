import java.util.*;

public class WordHunt {
    public static char[][] board = new char[4][4];
    public static List<String> solution = new ArrayList<>();
    public static int count = 0;
    public static void WH() throws Exception {
        Scanner in = new Scanner(System.in);
        String letters;
        boolean[][] visited = new boolean[4][4];

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
        System.out.println("Loading...");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = letters.charAt(i*4+j);
                visited[i][j] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                traverse(i, j, visited, "");
            }
        }

        String[] sorted = solution.toArray(new String[0]);
        Arrays.sort(sorted, Comparator.comparingInt(String::length));
        System.out.println("Number of words: " + count);
        System.out.println("Total possible score: " + Scorer.scoreWH(sorted));
        for (String s : sorted)
            System.out.println(s);
    }

    public static void traverse (int x, int y, boolean[][] visited, String word) throws Exception {
        boolean[][] visitedTemp = new boolean[4][4];
        for (int i = 0; i < 4; i++)
            System.arraycopy(visited[i], 0, visitedTemp[i], 0, 4);
        if (visitedTemp[x][y])
            return;
        word += board[x][y];
        visitedTemp[x][y] = true;

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
                traverse(x-1,y-1, visitedTemp, word);
            traverse(x,y-1, visitedTemp, word);
            if (x < 3)
                traverse(x+1,y-1, visitedTemp, word);
        }
        if (x < 3)
            traverse(x+1,y, visitedTemp, word);
        if (y < 3) {
            if (x < 3)
                traverse(x+1,y+1, visitedTemp, word);
            traverse(x,y+1, visitedTemp, word);
            if (x > 0)
                traverse(x-1,y+1, visitedTemp, word);
        }
        if (x > 0)
            traverse(x-1,y, visitedTemp, word);
    }
}
