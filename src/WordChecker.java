import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordChecker {
    public static boolean validWord (String word) throws IOException {
        char let = word.charAt(0);
        if (word.length() < 3)
            return false;
        File dict = new File("dict/dictionary_" + let + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(dict));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals(word))
                return true;
        }
        return false;
    }
    public static boolean validWordFrag (String word) throws IOException {
        char let = word.charAt(0);
        File dict = new File("dict/dictionary_" + let + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(dict));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(word))
                return true;
        }
        return false;
    }
}
