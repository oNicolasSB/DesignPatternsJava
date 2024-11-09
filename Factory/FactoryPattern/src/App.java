import Domain.Pizza;
import Domain.PizzaFactory;
import Domain.PizzaFlavor;

public class App {
    public static void main(String[] args) throws Exception {
        Pizza pizza1 = PizzaFactory.getInstance(PizzaFlavor.CREAMCHEESECHICKEN);
        Pizza pizza2 = PizzaFactory.getInstance(PizzaFlavor.PEPPERONI);

        System.out.println(pizza1);
        System.out.println(pizza2);

    }
}
