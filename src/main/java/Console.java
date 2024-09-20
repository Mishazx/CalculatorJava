import java.util.Objects;
import java.util.Scanner;

public class Console {
    String previousResult = "0";
    Scanner scanner = new Scanner(System.in);
    Calc calculator = new Calc();

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
        else if (Objects.equals(start_input,"2")) {
            System.out.println("Типо старый режим");
        }
        else if (Objects.equals(start_input, "q")) {
            System.out.println("Выходим из программы");
            System.exit(0);
        }
        else {
            System.out.println("Доступны только команды '1' '2' 'q' ");
        }
    }

    public void MoreLineInput() {
        System.out.println("Введите первое число для вычисления (или 'q' для выхода):");
        System.out.println("Введите знак для вычисления");
        System.out.println("Введите второе число для вычисления");
    }

    public void OneLineInput() {


        System.out.println("Введите выражение для вычисления (или 'q' для выхода):");
        System.out.println("Пример ввода `1 + 2 * (10 - 3)`");
        System.out.println("Второй ввод и последующие подразумевает ввод типо `+ 3 * (12 - 3)`");

        while (true) {
            String input = scanner.nextLine();
            String input2 = handleInput(input);
            if (input2 == "out") {
                System.out.println("Сбросили результат! Результат: " + previousResult);
            }
            else {
                String output = calculator.input(input2);
                handleOutput(output);
            }
        }
    }

    public String handleInput(String input) {
        if (input.equalsIgnoreCase("q")) {
            System.out.println("Выходим из программы");
            System.exit(0);
        }

        if (input.equalsIgnoreCase("c")) {
            previousResult = "0";
            return "out";
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

