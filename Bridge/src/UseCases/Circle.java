package UseCases;

import Abstractions.Color;
import Abstractions.Shape;

public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void Draw() {
        System.out.println("Drawing a circle with ");
        color.fill();
    }
}
