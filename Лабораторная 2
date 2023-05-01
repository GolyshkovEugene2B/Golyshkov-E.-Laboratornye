class Stack {
  private int arr[];
  private int top;
  private int capacity;

  // Создаем стек
  Stack(int size) {
    arr = new int[size];
    capacity = size;
    top = -1;
  }

  // Добавляем элементы в стек
  public void push(int x) {
    if (isFull()) {
      System.out.println("Переполнение\nПрограмма остановлена\n");
      System.exit(1);
    }

    System.out.println("Добавлен элемент " + x);
    arr[++top] = x;
  }

  // Удаляем элементы из стека
  public int pop() {
    if (isEmpty()) {
      System.out.println("Стек пуст");
      System.exit(1);
    }
    return arr[top--];
  }

  // Функция возвращает размер стека
  public int size() {
    return top + 1;
  }

  // Проверяем, пуст ли стек
  public Boolean isEmpty() {
    return top == -1;
  }

  // Проверяем, не заполнен ли стек
  public Boolean isFull() {
    return top == capacity - 1;
  }

  public void printStack() {
    for (int i = 0; i <= top; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) {
    Stack stack = new Stack(5);

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);

    stack.pop();
    System.out.println("\nПосле удаления");

    stack.printStack();

  }
}
