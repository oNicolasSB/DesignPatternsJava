package UseCases;

import java.lang.reflect.Proxy;

import Abstractions.HotelPersonInterface;

public class HotelStaffProxy {
    public static HotelPersonInterface getHotelStaffProxy(HotelPersonInterface hotelPerson) {
        // pass the HotelStaffInvocationHandler class, and the class and
        // interfaces of the hotelPerson interface
        return (HotelPersonInterface) Proxy.newProxyInstance(hotelPerson.getClass().getClassLoader(),
                hotelPerson.getClass().getInterfaces(),
                new HotelStaffInvocationHandler(hotelPerson));
    }
}
