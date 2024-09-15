import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calc calculator = new Calc();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Result: " + calculator.evaluate(input));
    }
}