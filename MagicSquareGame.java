import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MagicSquareGame {

    private int[][] current;
    private int[][] solution;
    private int n;
    private int moves = 0;

    public MagicSquareGame(MagicSquare squareObj) {
        this.solution = squareObj.getSquare();
        this.n = solution.length;

        // copy solution into current grid
        current = new int[n][n];
        for (int i = 0; i < n; i++) {
            current[i] = Arrays.copyOf(solution[i], n);
        }

        shuffle();
    }

    private void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < n * n; i++) {

            int row = rand.nextInt(n);
            int col = rand.nextInt(n);

            int dir = rand.nextInt(4);

            int newRow = row;
            int newCol = col;

            switch (dir) {
                case 0: newRow = (row - 1 + n) % n; break; // up
                case 1: newRow = (row + 1) % n; break;     // down
                case 2: newCol = (col - 1 + n) % n; break; // left
                case 3: newCol = (col + 1) % n; break;     // right
            }

            // swap
            int temp = current[row][col];
            current[row][col] = current[newRow][newCol];
            current[newRow][newCol] = temp;
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while (!isSolved()) {

            print();

            System.out.print("Enter move (row col direction U/D/L/R): ");
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            String dir = scanner.next();

            makeMove(r, c, dir.toUpperCase());

            moves++;
        }

        System.out.println("Solved in " + moves + " moves!");
    }

    private void makeMove(int r, int c, String dir) {
        
        int row = r - 1; // user uses 1-based indexing
        int col = c - 1;

        int newRow = row;
        int newCol = col;

        if (row < 0 || row >= n || col < 0 || col >= n) {
            System.out.println("Invalid position");
            return;
        }

        switch (dir) {
            case "U": newRow = (row - 1 + n) % n; break;
            case "D": newRow = (row + 1) % n; break;
            case "L": newCol = (col - 1 + n) % n; break;
            case "R": newCol = (col + 1) % n; break;
            default:
                System.out.println("Invalid direction");
                return;
        }

        int temp = current[row][col];
        current[row][col] = current[newRow][newCol];
        current[newRow][newCol] = temp;
    }

    private boolean isSolved() {
        return Arrays.deepEquals(current, solution);
    }

    private void print() {
        System.out.println();

        for (int[] row : current) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}