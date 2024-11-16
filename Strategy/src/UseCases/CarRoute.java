package UseCases;

import java.util.LinkedList;
import java.util.List;

import Abstractions.RouteStrategy;

public class CarRoute implements RouteStrategy {

    @Override
    public List<Point> createRoute(Point origin, Point destiny) {
        System.out.println("Creating route for cars...");
        return new LinkedList<Point>();
    }

}
