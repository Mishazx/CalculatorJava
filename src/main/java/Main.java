import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calc calculator = new Calc();
        Scanner scanner = new Scanner(System.in);

        String previousResult = "0";

        System.out.println("Введите выражение для вычисления (или 'q' для выхода):");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Выход из программы.");
                break;
            }

            if (previousResult != null && !previousResult.isEmpty()) {
                input = previousResult + input;
            }

            String output = calculator.input(input);

            if (Objects.equals(output, "NaN")) {
                previousResult = String.valueOf(0);
                System.out.println("Что-то пошло не так, занулили результат");
            } else {
                previousResult = output;
            }

            System.out.println("Результат: " + previousResult);
        }
        scanner.close();
    }
}