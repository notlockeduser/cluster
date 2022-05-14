import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {

        System.out.print("Enter a number: ");
        System.out.print("Enter a number: ");
        System.out.print("Enter a number: ");
        System.out.print("Enter a number: ");

        // Creates a reader instance which takes
        // input from standard input - keyboard
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");

        // nextInt() reads the next integer from the keyboard
        int number = reader.nextInt();

        // println() prints the following line to the output screen
        System.out.println("You entered: " + number);
    }
}