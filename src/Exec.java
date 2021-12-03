import java.util.*;
public class Exec {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        run: while(true) {
            System.out.println("What game would you like to play?\n" +
                    "1. WordHunt\n" +
                    "2. Anagrams");
            switch(in.next()) {
                case "1":
                    WordHunt.WH();
                    break run;
                case "2":
                    Anagram.AN();
                    break run;
                default:
                    System.out.println("Error: Invalid input! Please select 1 or 2.");
            }
        }
    }
}
