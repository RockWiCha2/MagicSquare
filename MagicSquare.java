public class MagicSquare {

    private int[][] square;
    private int n;

    public MagicSquare(int n) {
        this.n = n;
        square = new int[n][n];
    }

    public void generate() {

        int row = 0;
        int col = n / 2;

        for (int i = 1; i <= n * n; i++) {

            square[row][col] = i;

            int newRow = (row - 1 + n) % n;
            int newCol = (col - 1 + n) % n;

            if (square[newRow][newCol] == 0) {
                row = newRow;
                col = newCol;
            } else {
                row = (row + 1) % n;
            }
        }
    }

    public void print() {

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.print(square[i][j] + "\t");
            }

            System.out.println();
        }
    }
}