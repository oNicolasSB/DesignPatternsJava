package Abstractions;

import UseCases.City;
import UseCases.Factory;

public interface Visitor {
    void VisitCity(City city);

    void VisitFactory(Factory factory);
}
