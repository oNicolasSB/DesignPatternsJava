package Domain;

import java.util.List;


//abstract class or interface
public abstract class Pizza {
    protected List<Ingredient> ingredients;

    public Pizza() {
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Pizza { ingredients= " + ingredients + "}";
    }
}
