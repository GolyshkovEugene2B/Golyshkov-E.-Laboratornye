//Реализовать программу, вычисляющую сложный процент на основе введенных данных:
//1. Числа
//2. Процента
//3. Количества периодов
//Реализовать обратное действие. Посчитать каким должен быть процент, чтобы от Числа1 дойти до Числа2 за N периодов.

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.println("Введите начальное значение:");//ввод числа
    Scanner scanner = new Scanner(System.in);//объект для считывания ввода
    double principal = scanner.nextDouble();
    scanner.close();

    System.out.println("Введите процент:");//ввод процента
    double rate = scanner.nextDouble();
    scanner.close();

    System.out.println("Введите количество периодов:");//ввод периодов
    int periods = scanner.nextInt();

    //вычисление сложного процента
    double interest = 0;
    for (int i = 0; i < periods; i++) {
      interest += principal * rate / 100;
      principal += interest;
    }
    System.out.println("Сложный процент за " + periods + " периодов: " + interest);//вывод на экран
  }
}
