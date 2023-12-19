//консольный калькулятор

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        double a, b;//объявление переменных
        Scanner input = new Scanner(System.in);//новый объект для считывания ввода чисел пользователем

        System.out.print("Введите первое число: ");//введение первого числа
        a = input.nextDouble();

        System.out.print("\nВыберите операцию (+, -, *, /): ");//выбор операции
        String operator = input.next();

        System.out.print("\nВведите второе число: ");//ввод второго числа
        b = input.nextDouble();
        
	//реализация операций и вывод ошибок
        switch (operator){
            case "+":
                a += b;
                break;
            case "-":
                a -= b;
                break;
            case "*":
                a *= b;
                break;
            case "/":
                if (b != 0)
                    a /= b;
                else
                    System.err.println("Нельзя делить на ноль");
                break;
            default:
                System.err.println("Неправильная операция");
        }

        System.out.printf("%.2f", a);
    }
}
