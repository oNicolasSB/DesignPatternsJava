package UseCases;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import Abstractions.HotelPersonInterface;

public class HotelGuestInvocationHandler implements InvocationHandler {
    HotelPersonInterface hotelPerson;

    // get the HotelPerson interface in its constructor
    public HotelGuestInvocationHandler(HotelPersonInterface hotelPerson) {
        this.hotelPerson = hotelPerson;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        switch (methodName) {
            case "accessKitchen":
                // invoke the method that was called on the proxy, but on the CONCRETE
                // hotelPerson
                return method.invoke(hotelPerson, args);
            case "checkIn":
                // invoke the method that was called on the proxy, but on the CONCRETE
                // hotelPerson
                return method.invoke(hotelPerson, args);
            case "checkOut":
                // invoke the method that was called on the proxy, but on the CONCRETE
                // hotelPerson
                return method.invoke(hotelPerson, args);
            case "rate":
                // invoke the method that was called on the proxy, but on the CONCRETE
                // hotelPerson
                return method.invoke(hotelPerson, args);
            default:
                System.out.println("Disallowed task for hotel Guests");

        }// switch

        return null;
    }// invoke
}
