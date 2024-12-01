package UseCases;

import Abstractions.HouseBuilder;

public class Director {
    private HouseBuilder builder;

    public void setBuilder(HouseBuilder builder) {
        this.builder = builder;
    }

    public void constructHouse() {
        builder.buildWalls();
        builder.buildRoof();
        builder.buildWindows();
        builder.buildDoor();
        builder.buildPool();
    }
}
