//Если класс B наследует класс A, то все методы и поля класса A становятся доступными в классе B
//Класс А
public class A {
    private String name;

    public A(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }
}

//Класс B, который наследует класс A
public class B extends A {
    public B(String name) {
        super(name);
    }

    public void newMethod() {
        // добавляем новый метод
        System.out.println("New method from B class");
    }
} 

public static void main(String[] args) {
B b = new B(“John”);
b.printName();
b.newMethod();
}
//Принцип наследования позволяет классам наследовать свойства и методы от других классов
