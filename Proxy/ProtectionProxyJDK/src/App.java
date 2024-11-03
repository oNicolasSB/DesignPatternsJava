import Abstractions.HotelPersonInterface;
import UseCases.HotelGuestProxy;
import UseCases.HotelPerson;
import UseCases.HotelStaffProxy;

public class App {
    public static void main(String[] args) throws Exception {
        HotelPersonInterface hotelPerson = new HotelPerson();

        HotelPersonInterface hotelStaffProxy = HotelStaffProxy.getHotelStaffProxy(hotelPerson);

        System.out.println("Created hotel staff proxy");

        System.out.println("Hotel staff trying to access kitchen");
        hotelStaffProxy.accessKitchen();

        System.out.println("Hotel staff trying to rate the hotel");
        hotelStaffProxy.rate();

        HotelPersonInterface hotelGuestProxy = HotelGuestProxy.getHotelGuestProxy(hotelPerson);
        System.out.println("Created hotel guest proxy");

        System.out.println("Hotel guest trying to access kitchen");
        hotelGuestProxy.accessKitchen();

        System.out.println("Hotel guest trying to rate the hotel");
        hotelGuestProxy.rate();
    }
}
