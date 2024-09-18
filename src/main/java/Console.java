import java.util.Objects;
import java.util.Scanner;

public class Console {
    String previousResult = "0";
    Scanner scanner = new Scanner(System.in);

    public void MainMenu() {
        System.out.println("|------------------------------------------|");
        System.out.println("|Добро пожаловать в уникальный калькулятор!|");
        System.out.println("|------------------------------------------|");
        System.out.println("| Введите 1 для однострочного калькулятора |");
        System.out.println("| Введите 2 для трехстрочного калькулятора |");
        System.out.println("| Введите q для выхода из программы");
        String start_input = scanner.nextLine();
        System.out.println(start_input);
        if (Objects.equals(start_input, "1")) {
            OneLineInput();
        }
        if (Objects.equals(start_input,"2")) {
//            ManyLineInput();
            System.out.println("Типо старый режим");
        }
        if (Objects.equals(start_input, "q")) {
            System.out.println("Выходим из программы");
            System.exit(0);
        }
        else {
            System.out.println("Доступны только команды '1' '2' 'q' ");
        }
    }

    public void OneLineInput() {
        Calc calculator = new Calc();

        System.out.println("Введите выражение для вычисления (или 'q' для выхода):");

        while (true) {
            String input = scanner.nextLine();

            String output = calculator.input(handleInput(input));

            handleOutput(output);
        }
    }

    public String handleInput(String input) {
        if (input.equalsIgnoreCase("q")) {
            MainMenu();
        }

        if (input.equalsIgnoreCase("c")) {
            previousResult = "0";
            System.out.println("Сбросили результат! Результат: " + previousResult);
        }

        if (previousResult != null && !previousResult.isEmpty()) {
            input = previousResult + input;
        }
        return input;
    }

    public void handleOutput(String output) {
        if (Objects.equals(output, "NaN")) {
            previousResult = "0";
            System.out.println("Что-то пошло не так, занулили результат");
        } else {
            previousResult = output;
        }

        System.out.println("Результат: " + previousResult);
    }
}

