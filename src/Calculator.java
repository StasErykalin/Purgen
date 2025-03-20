import java.util.Arrays;
import java.util.Scanner;

/**
 * Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a
 * + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)! Решения, в которых
 * каждое число и арифметическая операция передаются с новой строки считаются неверными. Калькулятор
 * должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не
 * ограничиваются по величине и могут быть любыми. Калькулятор умеет работать только с целыми
 * числами. При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает
 * свою работу. При вводе пользователем строки, не соответствующей одной из вышеописанных
 * арифметических операций, приложение выбрасывает исключение и завершает свою работу. Результатом
 * операции деления является целое число, остаток отбрасывается. Результатом работы калькулятора с
 * арабскими числами могут быть отрицательные числа и ноль.
 */

public class Calculator {

  public static void main(String[] args) {
    String[] input = getInput();
    char operator = getOperator(input);
    int[] operands = getOperands(input);
    int firstOperand = operands[0];
    int secondOperand = operands[1];
    int result = calculate(firstOperand, secondOperand, operator);
    System.out.print(result);
  }

  /**
   * Получаем строку с выражением, в формате a+b или a + b. И приводим к массиву ["a","+","b"],
   * помощи регулярного выражения.
   *
   * @return {@code String[] input}
   */
  private static String[] getInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введи арифметическое выражение, в формате: а+б или а + б");
    String input = scanner.nextLine().replace(" ", "");
    return input.split("(?<=[^+\\-*/])(?=[+\\-*/])|(?<=[+\\-*/])(?=[^+\\-*/])");
  }

  /**
   * Метод принимает строку с арифметическим выражением и выделяет оператор выражения.
   *
   * @param input строка с арифметическим выражением (например, "5 + 3")
   * @return {@code operator} - символ оператора ('+', '-', '*', '/')
   * @throws IllegalArgumentException выбрасывается если отсутствует арифметический оператор
   */
  private static char getOperator(String[] input) {
    if (input.length != 3) {
      throw new IllegalArgumentException(
          "Неверный формат выражения: количество операторов или операндов не"
              + " соответствует заданным условиям.");
    }
    if (Arrays.asList(input).contains("+")) {
      return '+';
    } else if (Arrays.asList(input).contains("-")) {
      return '-';
    } else if (Arrays.asList(input).contains("*")) {
      return '*';
    } else if (Arrays.asList(input).contains("/")) {
      return '/';
    } else {
      throw new IllegalArgumentException("Неверный формат выражения: отсутствует арифметический оператор");
    }
  }

  /**
   * Метод для извлечения чисел из строки разделенной оператором
   * @param input    строка с арифметическим выражением (например, "5 + 3")
   * @return {@code int[]} [первый операнд, второй операнд]
   * @throws IllegalArgumentException выбрасывается если количество операндов больше 2-х или
   *                                  количество операторов больше 1-о
   * @throws NumberFormatException    выбрасывается если один или все операнды не являются целым
   *                                  числом в диапазоне то 1 до 10 включительно
   */
  private static int[] getOperands(String[] input) {
    try {
      int firstOperand = Integer.parseInt(input[0]);
      int secondOperand = Integer.parseInt(input[2]);
      if (firstOperand < 0 || secondOperand < 0 || firstOperand > 10 || secondOperand > 10) {
        throw new IllegalArgumentException("Неверный формат выражения: один или все операнды "
            + "выходят за границы диапазона от 1 до 10");
      }
      return new int[]{firstOperand, secondOperand};
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Неверный формат выражения: один или все операнды не "
          + "являются целым числом");
    }
  }

  /**
   * Метод получения результата выражения
   * @param firstOperand  первый операнд
   * @param secondOperand второй операнд
   * @param operator      арифметический оператор
   * @return {@code int} возвращает результат выражения
   * @throws IllegalArgumentException выбрасывается если какое-то из условий не удовлетворяет
   *                                  заданным условиям
   */
  private static int calculate(int firstOperand, int secondOperand, char operator) {
    return switch (operator) {
      case '+' -> firstOperand + secondOperand;
      case '-' -> firstOperand - secondOperand;
      case '*' -> firstOperand * secondOperand;
      case '/' -> firstOperand / secondOperand;
      default -> throw new IllegalArgumentException("Чет какая то хуйня, Миша, давай по новой...");
    };
  }
}