import Abstractions.DataService;
import UseCases.DataServiceProxyFactory;

public class App {
    public static void main(String[] args) throws Exception {
        DataService dataServiceProxy = DataServiceProxyFactory.createProxy();

        System.out.println("Client requesting data...");
        String data = dataServiceProxy.fetchData(); // Chamada ao proxy
        System.out.println("Received data: " + data);
    }
}
