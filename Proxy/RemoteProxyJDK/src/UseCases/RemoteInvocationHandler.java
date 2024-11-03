package UseCases;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import Abstractions.DataService;

public class RemoteInvocationHandler implements InvocationHandler {
    private final DataService realService;

    public RemoteInvocationHandler(DataService realService) {
        this.realService = realService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Connecting to remote server...");
        // Simula a comunicação com o serviço remoto
        Object result = method.invoke(realService, args); // Chama o método no serviço real
        System.out.println("Returning data from remote server.");
        return result;
    }
}
