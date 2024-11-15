package UseCases;

import Abstractions.Color;

public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Color: Blue");
    }
}
