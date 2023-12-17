public class Zoo {
    public static void main(String[] args) {
        Animal tiger = new Tiger();
        Animal lion = new Lion();

        System.out.println("Tiger and Lion have following attributes:");
        System.out.println(tiger.getAttribute());
        System.out.println(lion.getAttribute());
    }
}

class Animal {
    String attribute;

    public String getAttribute() {
        return attribute;
    }

    void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}

class Tiger extends Animal {

    @Override
    void setAttribute(String attribute) {
        super.setAttribute("Tiger: " + attribute);
    }
}

class Lion extends Animal {

    @Override
    void setAttribute(String attribute) {
        super.setAttribute("Lion: " + attribute);
    }
}
