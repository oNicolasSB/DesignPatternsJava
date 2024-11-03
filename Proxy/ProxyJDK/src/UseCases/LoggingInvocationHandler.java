package UseCases;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + " is called with arguments: " +
                (args != null ? Arrays.toString(args) : "null"));

        Object result = method.invoke(target, args);
        System.out.println("Method " + method.getName() + " return: " + result);
        return result;
    }

}
