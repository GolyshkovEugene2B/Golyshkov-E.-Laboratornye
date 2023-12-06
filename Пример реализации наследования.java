public class A {
    private String name;

    public A(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }
}
public class B extends A {
    public B(String name) {
        super(name);
    }

    public void newMethod() {
        // добавляем новый метод
        System.out.println("New method from B class");
    }
} 
