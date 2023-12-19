//Объект класса Halter создается извне Horse и передается в конструктор для установления связи. Если объект класса Horse будет удален, объект класса Halter может и дальше использоваться, если, конечно, на него останется ссылка
public class Horse {
    private Halter halter;

    public Horse(Halter halter) {
        this.halter = halter;
    }
}

//Объект класса Halter создается в конструкторе, что означает более тесную связь между объектами. Объект класса Halter не может существовать без создавшего его объекта Horse
public class Horse {
    private Halter halter;

    public Horse() {
        this.halter = new Halter();
    }
}
