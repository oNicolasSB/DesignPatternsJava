package UseCases;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import Abstractions.HotelPersonInterface;

public class HotelStaffInvocationHandler implements InvocationHandler {
    HotelPersonInterface hotelPerson;

    // get the HotelPerson interface in its constructor
    public HotelStaffInvocationHandler(HotelPersonInterface hotelPerson) {
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
            case "AccessMonitorRoom":
                // invoke the method that was called on the proxy, but on the CONCRETE
                // hotelPerson
                return method.invoke(hotelPerson, args);
            default:
                // blocks from executing methods not allowed for the current person
                System.out.println("Disallowed task for hotel staffs");

        }// switch

        return null;
    }
}
