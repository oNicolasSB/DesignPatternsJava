import UseCases.CarRoute;
import UseCases.Navigator;
import UseCases.Point;
import UseCases.WalkRoute;

public class App {
    public static void main(String[] args) throws Exception {
        Navigator navigator = new Navigator();

        navigator.setStrategy(new CarRoute());
        navigator.createRoute(new Point(0, 0), new Point(10, 10));

        navigator.setStrategy(new WalkRoute());
        navigator.createRoute(new Point(0, 0), new Point(5, 5));
    }
}
