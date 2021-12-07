import java.util.*;
public class Mancala {
    public static int[][] board = new int[2][6];
    public static int p1Score = 0, p2Score = 0;
    public static boolean _turn = true; // true = p1, false = p2
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            board[0][i] = 4;
            board[1][i] = 4;
        }
        System.out.println("Who is playing first? (1/2)");
        switch (in.next()) {
            case "1": _turn = true; break;
            case "2": _turn = false; break;
        }
        run: while (true) {
            if (_turn) {
                System.out.println("Player 1 to move: ");
                print();
                try {
                    if (!move(1, RtI(1, localMax())))
                        _turn = !_turn;
                } catch (StackOverflowError e) {
                    System.out.println("Error: that space is empty.\n" +
                            "Try again.\n");
                }
            } else {
                System.out.println("Player 2 to move: ");
                print();
                try {
                    if (!move(2, RtI(2, in.nextInt())))
                        _turn = !_turn;
                } catch (StackOverflowError e) {
                    System.out.println("Error: that space is empty.\n" +
                            "Try again.\n");
                }
            }
        }
    }

    public static void print() {
        System.out.println(" ~ " + p2Score + " ~");
        for (int i = 0; i < 6; i++)
            System.out.println(" |" + board[0][i] + "|" + board[1][i] + "| <-" + (6-i));
        System.out.println(" ~ " + p1Score + " ~ \n\n");
    }

    public static boolean move (int turn, int ind) {
        int[] linear = linearize(turn);
        if (turn == 1) {
            if (linear[ind] == 0)
                throw new StackOverflowError();
            int temp = linear[ind];
            linear[ind] = 0;
            for (int i = ind+1; i < ind+1 + temp; i++)
                linear[i%13]++;
            p1Score += linear[6];
            board = fold(1, linear);
            temp = (ind+temp)%13;
            if (temp == 6)
                return true;
            if (linear[temp] != 1)
                return move(1, temp);
        } else if (turn == 2) {
            if (linear[ind] == 0)
                throw new StackOverflowError();
            int temp = linear[ind];
            linear[ind] = 0;
            for (int i = ind+1; i < ind+1 + temp; i++)
                linear[i%13]++;
            p2Score += linear[12];
            board = fold(2, linear);
            temp = (ind+temp)%13;
            if (temp == 12)
                return true;
            if (linear[temp] != 1)
                return move(2, temp);
        }
        return false;
    }

    public static int RtI (int turn, int row) {
        switch (turn) {
            case 1: return -row+6;
            case 2: return row+5;
            default: return -1;
        }
    }

    public static int[] linearize (int turn) {
        int[] linear = new int[13];
        switch (turn) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    linear[i] = board[0][i];
                    linear[i+7] = board[1][5-i];
                } linear[6] = 0;
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    linear[i] = board[0][i];
                    linear[i+6] = board[1][5-i];
                } linear[12] = 0;
                break;
        }
        return linear;
    }
    
    public static int[][] fold (int turn, int[] linear) {
        int[][] folded = new int[2][6];
        switch (turn) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    folded[0][i] = linear[i];
                    folded[1][5-i] = linear[i+7];
                }
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    folded[0][i] = linear[i];
                    folded[1][5-i] = linear[i+6];
                }
                break;
        }
        return folded;
    }

    public static int localMax () {
        int[][] origState = new int[2][6];
        boolean[] resets = new boolean[6];
        int[] scores = new int[6];
        int origScore = p1Score, moveScore = 0, bestMove = -1;
        for (int i = 0; i < 6; i++) {
            origState[0][i] = board[0][i];
            origState[1][i] = board[1][i];
        }
        for (int i = 0; i < 6; i++) {
            if (board[0][-i+5] == 0) {
                continue;
            }
            resets[i] = move(1, RtI(1, i+1));
            scores[i] = p1Score;
            p1Score = origScore;
            for (int j = 0; j < 6; j++) {
                board[0][j] = origState[0][j];
                board[1][j] = origState[1][j];
            }
        }
        for (int i = 0; i < 6; i++) {
            if (scores[i] > moveScore) {
                bestMove = i;
                moveScore = scores[i];
                continue;
            }
            if (scores[i] == moveScore && resets[i]) {
                bestMove = i;
            }
        }
        System.out.println("Player 1 best move: " + (bestMove+1));
        if (resets[bestMove])
            System.out.println("THIS IS A RESET MOVE\n");
        return bestMove+1;
    }
}
