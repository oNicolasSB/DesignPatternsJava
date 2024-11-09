package Domain;

import java.util.List;

public class ChickenPizza extends Pizza {
    public ChickenPizza() {
        this.ingredients = List.of(
                new Ingredient("Tomato sauce"),
                new Ingredient("Mozzarella"),
                new Ingredient("Shredded chicken"),
                new Ingredient("Unripe corn"),
                new Ingredient("Cream cheese"),
                new Ingredient("Tomato slices"));
    }
}
