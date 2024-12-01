package UseCases;

import Abstractions.HouseBuilder;

public class LuxuryHouseBuilder implements HouseBuilder {
    private House house = new House();

    @Override
    public void buildWalls() {
        house.setWalls("Marble Walls");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Glass Roof");
    }

    @Override
    public void buildWindows() {
        house.setWindows("Panoramic Windows");
    }

    @Override
    public void buildDoor() {
        house.setDoor("Automatic Door");
    }

    @Override
    public void buildPool() {
        house.setPool("Infinity Pool");
    }

    @Override
    public House getResult() {
        return house;
    }
}
