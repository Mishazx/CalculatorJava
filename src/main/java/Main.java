import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calc calculator = new Calc();
        Scanner scanner = new Scanner(System.in);

        String previousResult = "0";
        System.out.println("Введите выражение для вычисления (или 'exit' для выхода):");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.println("DEBUG prev: " + previousResult);
            System.out.println("DEBUG input: " + input);

            if (previousResult != null && !previousResult.isEmpty()) {
                input = previousResult + input;
            }

            String output = calculator.input(input);
            previousResult = output;
            System.out.println("Результат: " + output);
        }

        scanner.close();
    }
}