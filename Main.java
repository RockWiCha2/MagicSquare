import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an odd integer: ");
        int n = scanner.nextInt();

        // ensure number is odd
        while (n % 2 == 0) {
            System.out.print("Please enter an odd integer: ");
            n = scanner.nextInt();
        }

        MagicSquare square = new MagicSquare(n);

        square.generate();
        square.print();
    }
}