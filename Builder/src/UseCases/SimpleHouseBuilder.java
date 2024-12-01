package UseCases;

import Abstractions.HouseBuilder;

public class SimpleHouseBuilder implements HouseBuilder {
    private House house = new House();

    @Override
    public void buildWalls() {
        house.setWalls("Wooden Walls");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Wooden Roof");
    }

    @Override
    public void buildWindows() {
        house.setWindows("Glass Windows");
    }

    @Override
    public void buildDoor() {
        house.setDoor("Wooden Door");
    }

    @Override
    public void buildPool() {
        // There is no pool on a simple house
    }

    @Override
    public House getResult() {
        return house;
    }
}
