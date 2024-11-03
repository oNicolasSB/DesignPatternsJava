import Abstractions.GreetingService;
import UseCases.EnglishGreetingService;
import UseCases.ProxyFactoryJDK;

public class App {
    public static void main(String[] args) throws Exception {
        GreetingService realService = new EnglishGreetingService();
        GreetingService proxyService = ProxyFactoryJDK.createProxy(realService, GreetingService.class);

        System.out.println("Real Service:");
        System.out.println(realService.greet("Nicolas"));
        System.out.println("---------------");
        System.out.println("Proxy: ");
        System.out.println(proxyService.greet("Nicolas"));
    }
}
