import java.io.*;
import java.util.*;

public class WordHunt {
    public static char[][] board = new char[4][4];
    public static List<String> solution = new ArrayList<>();
    public static int count = 0;
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String letters;
        boolean[][] traversed = new boolean[4][4];

        // reads input until valid string of 16 letters is found
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

        // copies string to board
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = letters.charAt(i*4+j);
                //System.out.print(board[i][j]);
                traversed[i][j] = false;
            }
            //System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                traverse(i, j, traversed, "");
            }
        }
        String[] sorted = solution.toArray(new String[0]);
        Arrays.sort(sorted, (a, b) -> Integer.compare(b.length(), a.length()));
        for (int i = 0; i < sorted.length; i++) {
            //System.out.println(sorted[i]);
        }
        System.out.println(count);
    }

    public static void traverse (int x, int y, boolean[][] traversed, String word) throws Exception {
        boolean[][] updTraversed = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                updTraversed[i][j] = traversed[i][j];
            }
        }
        if (updTraversed[x][y])
            return;
        word += board[x][y];
        updTraversed[x][y] = true;

        if (validWord(word)) {
            if (!solution.contains(word)) {
                System.out.println(word);
                solution.add(word);
                count++;
            }
        }
        if (!validWordFrag(word))
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

    public static boolean validWord (String word) throws IOException {
        if (word.length() < 3)
            return false;
        File dict = new File("dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(dict));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals(word))
                return true;
        }
        return false;
    }
    public static boolean validWordFrag (String word) throws IOException {
        File dict = new File("dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(dict));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(word))
                return true;
        }
        return false;
    }

    /*public static boolean validWord (String word) throws Exception {
        if (word.length() < 3)
            return false;
        URL url = new URL("https://dictionary.com/browse/" + word);
        try {
            Scanner in = new Scanner(url.openStream());
        } catch (Exception e) {
            return false;
        }
        return true;
    }*/
}
