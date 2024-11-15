import Abstractions.Color;
import Abstractions.Shape;
import UseCases.Blue;
import UseCases.Circle;
import UseCases.Red;
import UseCases.Square;

public class App {
    public static void main(String[] args) throws Exception {
        Color red = new Red();
        Shape circle = new Circle(red);

        circle.Draw();

        Color blue = new Blue();
        Shape square = new Square(blue);
        square.Draw();
    }
}
