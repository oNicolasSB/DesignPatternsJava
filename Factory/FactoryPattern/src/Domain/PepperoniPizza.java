package Domain;

import java.util.List;

public class PepperoniPizza extends Pizza {
    protected PepperoniPizza() {
        this.ingredients = List.of(
                new Ingredient("Tomato sauce"),
                new Ingredient("Mozzarella"),
                new Ingredient("Oregano"),
                new Ingredient("Pepperoni"),
                new Ingredient("Onions"));
    }
}
