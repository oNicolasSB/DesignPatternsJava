package UseCases;

import Abstractions.Color;
import Abstractions.Shape;

public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void Draw() {
        System.out.println("Drawing a square with ");
        color.fill();

    }

}
