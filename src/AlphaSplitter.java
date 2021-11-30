import java.io.*;
import java.util.Scanner;

public class AlphaSplitter {
    public static void main(String[] args) throws IOException {
        File dict = new File("dictionary.txt");

        for (char i = 'A'; i <= 'Z'; i++) {
            BufferedReader br = new BufferedReader(new FileReader(dict));
            FileWriter f = new FileWriter("dictionary_" + i + ".txt");
            String word;
            while ((word = br.readLine()) != null) {
                if (word.charAt(0) == i)
                    f.write(word + "\r");
                f.flush();
            }
            f.close();
        }
    }
}
