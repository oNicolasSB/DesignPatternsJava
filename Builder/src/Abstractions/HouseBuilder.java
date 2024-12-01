package Abstractions;

import UseCases.House;

public interface HouseBuilder {
    void buildWalls();

    void buildRoof();

    void buildWindows();

    void buildDoor();

    void buildPool();

    House getResult();
}
