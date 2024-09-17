import java.util.Stack;

public class Calc {
    char[] tokens;
    Stack<Double> values = new Stack<>();
    Stack<Character> operators = new Stack<>();

    public double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }

            if (Character.isDigit(tokens[i])) {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    if (values.size() < 2) {
                        System.out.println("Error: Insufficient values in expression");
                        return Double.NaN;
                    }
                    try {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    } catch (UnsupportedOperationException e) {
                        System.out.println("Error: " + e.getMessage());
                        return Double.NaN;
                    }
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                } else {
                    System.out.println("Error: Mismatched parentheses");
                    return Double.NaN;
                }
            } else if (isOperator(tokens[i])) {
                while (!operators.isEmpty() && precedence(tokens[i]) <= precedence(operators.peek())) {
                    if (values.size() < 2) {
                        System.out.println("Error: Insufficient values in expression");
                        return Double.NaN;
                    }
                    try {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    } catch (UnsupportedOperationException e) {
                        System.out.println("Error: " + e.getMessage());
                        return Double.NaN;
                    }
                }
                operators.push(tokens[i]);
            } else {
                System.out.println("Error: Invalid character in expression");
                return Double.NaN;
            }
        }

        while (!operators.isEmpty()) {
            if (values.size() < 2) {
                System.out.println("Error: Insufficient values in expression");
                return Double.NaN;
            }
            try {
                values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
            } catch (UnsupportedOperationException e) {
                System.out.println("Error: " + e.getMessage());
                return Double.NaN;
            }
        }

        if (values.size() != 1) {
            System.out.println("Error: The expression is invalid");
            return Double.NaN;
        }

        return values.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private double applyOperation(char operator, Double b, Double a) {
        if (b == null || a == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0 || a == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                yield a / b;
            }
            default -> 0;
        };
    }

    public String input(String input) {
        if (input.equalsIgnoreCase("c") || input.equals("c")) {
            return "Сброс калькулятора"; // Код выхода из программы
        }
        if (input.equalsIgnoreCase("q") || input.equals("q")) {
            return "123";
        }
        return String.valueOf(evaluate(input));
    }
}
