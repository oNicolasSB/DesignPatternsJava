package UseCases;

import java.util.List;

import Abstractions.RouteStrategy;

public class Navigator {
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void createRoute(Point origin, Point destiny) {
        if (strategy == null) {
            System.out.println("No defined strategy!");
            return;
        }

        List<Point> route = strategy.createRoute(origin, destiny);
        System.out.println(route);

        // render route on map...
    }
}
