package Abstractions;

import java.util.List;

import UseCases.Point;

public interface RouteStrategy {
    List<Point> createRoute(Point origin, Point destiny);
}
