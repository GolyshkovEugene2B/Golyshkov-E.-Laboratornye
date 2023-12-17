public abstract class SweetProducer {
  public abstract void produceSweet();
}

public class CakeProducer extends SweetProducer {
  @Override
  public void produceSweet() {
    System.out.println("Cake produced");
  }
}
public class ChocolateProducer extends SweetProducer {
  @Override
  public void produceSweet() {
    System.out.println("Chocolate produced");
  }
}
public class CookieProducer extends SweetProducer {
  @Override
  public void produceSweet() {
    System.out.println("Cookie produced");
  }
}

public class SweetCreator {
  private List<SweetProducer> sweetProducer;

  public SweetCreator(List<SweetProducer> sweetProducer) {
    this.sweetProducer = sweetProducer;
  }

  public void createSweets() {
    sweetProducer.forEach(sweet -> sweet.produceSweet());
  }
}

public class SweetCreatorTest {
  public static void main(String... args) {
    SweetCreator sweetCreator = new SweetCreator(Arrays.asList(
      new CakeProducer(),
      new ChocolateProducer(), 
      new CookieProducer()));

     sweetCreator.createSweets();
  }
}
