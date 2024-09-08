package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        double result = 0;

        System.out.println("Введите выражение или 'exit' для выхода:");

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            result = Calculator.evaluate(result + " " + input);
            System.out.println("result: " + result);
        }

        scanner.close();
    }
}
