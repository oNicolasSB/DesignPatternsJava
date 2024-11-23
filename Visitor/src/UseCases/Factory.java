package UseCases;

import Abstractions.Node;
import Abstractions.Visitor;

public class Factory extends Node {
    public Factory(Double latitude, Double longitude) {
        super(latitude, longitude);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.VisitFactory(this);
    }
}
