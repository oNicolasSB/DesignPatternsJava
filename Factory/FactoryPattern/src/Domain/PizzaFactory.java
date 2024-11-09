package Domain;

public class PizzaFactory {
    private PizzaFactory() {
    }

    public static Pizza getInstance(PizzaFlavor flavor) throws Exception {
        Pizza pizza = new Pizza() {
        };
        switch (flavor) {
            case PizzaFlavor.PEPPERONI:
                pizza = new PepperoniPizza();
                break;
            case PizzaFlavor.CREAMCHEESECHICKEN:
                pizza = new ChickenPizza();
                break;
            default:
                throw new Exception("Invalid flavor");
        }

        return pizza;
    }
}
