package UseCases;

import java.lang.reflect.Proxy;

public class ProxyFactoryJDK {
    public static <T> T createProxy(T target, Class<T> interfaceType)
    {
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[] {interfaceType}, new LoggingInvocationHandler(target));
    }
}
