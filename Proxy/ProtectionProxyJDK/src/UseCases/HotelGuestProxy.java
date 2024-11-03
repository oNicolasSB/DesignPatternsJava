package UseCases;

import java.lang.reflect.Proxy;

import Abstractions.HotelPersonInterface;

public class HotelGuestProxy {
    public static HotelPersonInterface getHotelGuestProxy(HotelPersonInterface hotelPerson) {
        // pass the HotelGuestInvocationHandler class, and the class and
        // interfaces of the hotelPerson interface
        return (HotelPersonInterface) Proxy.newProxyInstance(hotelPerson.getClass().getClassLoader(),
                hotelPerson.getClass().getInterfaces(),
                new HotelGuestInvocationHandler(hotelPerson));
    }
}
