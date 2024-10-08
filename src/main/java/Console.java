import java.util.Objects;
import java.util.Scanner;

public class Console {
    String previousResult = "0";
    Scanner scanner = new Scanner(System.in);
    Calc calculator = new Calc();

    public void mainMenu() {
        System.out.println("|------------------------------------------|");
        System.out.println("|Добро пожаловать в уникальный калькулятор!|");
        System.out.println("|------------------------------------------|");
        System.out.println("| Введите 1 для однострочного калькулятора |");
        System.out.println("| Введите 2 для трехстрочного калькулятора |");
        System.out.println("| Введите q для выхода из программы");
        String start_input = scanner.nextLine();
//        System.out.println(start_input);
        if (Objects.equals(start_input, "1")) {
            oneLineInput();
        }
        else if (Objects.equals(start_input,"2")) {
            moreLineInput();
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

    public void moreLineInput() {
        while (true) {
            System.out.println("Введите первое число для вычисления");
            String num1 = scanner.next();
            System.out.println("Введите знак для вычисления: ");
            char operator = scanner.next().charAt(0);
            System.out.println("Введите второе число для вычисления: ");
            String num2 = scanner.next();
            String input = num1 + operator + num2;
            System.out.println(input);
            String output = calculator.input(input);
            handleOutput(output, input);
        }
    }

    public void oneLineInput() {
        System.out.println("Введите выражение для вычисления (или 'q' для выхода):");
        System.out.println("Пример ввода `1 + 2 * (10 - 3)`");
        System.out.println("Второй ввод и последующие подразумевает ввод типо `+ 3 * (12 - 3)`");

        while (true) {
            String input = scanner.nextLine();
            String input2 = handleInput(input);
            if (Objects.equals(input2, "out")) {
                System.out.println("Сбросили результат! Результат: " + previousResult);
            }
            else {
                String output = calculator.input(input2);
                handleOutput(output);
            }
        }
    }

    public String handleInput(String input) { // Функция обработки ввода
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
        handleOutput(output, null); // Вызов с null в качестве второго параметра
    }

    public void handleOutput(String output, String input) { // Функция обработки вывода
        if (Objects.equals(output, "NaN")) {
            previousResult = "0";
            System.out.println("Что-то пошло не так, занулили результат");
        } else {
            previousResult = output;
        }
        if (input == null) {
            System.out.println("Результат: " + output);
        }
        else {
            System.out.println("Посчитали " + input + " = " + output);
        }
    }
}
