//Високосный год
//Посчитать, является ли введенный год високосным. 
//В случае неверного ввода данных выбросить и обработать исключительную ситуацию

import java.util.Scanner;

public class Year {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Создание объекта класса Scanner для чтения данных с консоли
        System.out.print("Введите год (например, 1992): "); // Вывод на экран сообщения о вводе года
        
        try {
            int year = scanner.nextInt(); //считывание года
            if (isYear(year)) {
                System.out.println(year + " - високосный год."); //сообщение о високосном году
            } else {
                System.out.println(year + " - не високосный год.");//сообщение о не високосном году
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод, попробуйте еще раз.");
        }
    }
  
    //функция проверки года
    public static boolean isYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 == 0 && year % 400 != 0){
            return false;
        } else {
            return true;
        }
    }
}
