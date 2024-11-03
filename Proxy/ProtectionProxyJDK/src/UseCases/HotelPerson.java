package UseCases;

import Abstractions.HotelPersonInterface;

public class HotelPerson implements HotelPersonInterface {
    @Override
    public void accessKitchen() {
        System.out.println("Accessing the kitchen...");
    }

    @Override
    public void AccessMonitorRoom() {
        System.out.println("Accessing the monitor room...");
    }

    @Override
    public void checkIn() {
        System.out.println("Checking in...");
    }

    @Override
    public void checkOut() {
        System.out.println("Checking out...");
    }

    @Override
    public void rate() {
        System.out.println("Rate...");
    }
}
