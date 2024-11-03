package UseCases;

import java.lang.reflect.Proxy;

import Abstractions.DataService;

public class DataServiceProxyFactory {
    public static DataService createProxy() {
        DataService realService = new RealDataService(); // Instancia o serviço real
        RemoteInvocationHandler handler = new RemoteInvocationHandler(realService);

        // Cria e retorna o proxy dinâmico
        return (DataService) Proxy.newProxyInstance(
                DataService.class.getClassLoader(),
                new Class<?>[] { DataService.class },
                handler);
    }
}
