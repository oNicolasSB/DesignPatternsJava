package UseCases;

import Abstractions.Node;
import Abstractions.Visitor;

public class City extends Node {

    public City(Double latitude, Double longitude) {
        super(latitude, longitude);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.VisitCity(this);
    }

}
