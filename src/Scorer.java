public class Scorer {
    public static int scoreWH (String[] words) {
        int sum = 0;
        for (String word : words) {
            if (word.length() == 3) {
                sum += 100;
                continue;
            }
            sum += (word.length() - 3) * 400;
        }
        return sum;
    }
    public static int scoreAN (String[] words) {
        int sum = 0;
        for (String word : words) {
            if (word.length() == 3) {
                sum += 100;
                continue;
            }
            sum += (word.length() - 3) * 800 - 400;
        }
        return sum;
    }
}

//      WH     AN
// -----------------------
// 3 - 100    100
// 4 - 400    400
// 5 - 800    1200
// 6 - 1400   2000
// 7 - 1800    --
// 8 - 2200    --
// . -  .      --
// . -  .      --
// . -  .      --