import Abstractions.Handler;
import UseCases.AuthenticationHandler;
import UseCases.CacheHandler;
import UseCases.DataSanitizationHandler;
import UseCases.RateLimitingHandler;
import UseCases.Request;

public class App {
    public static void main(String[] args) throws Exception {
        Handler authentication = new AuthenticationHandler();
        Handler sanitization = authentication.setNext(new DataSanitizationHandler());
        Handler rateLimiting = sanitization.setNext(new RateLimitingHandler());
        rateLimiting.setNext(new CacheHandler());

        // Simulação de requisição
        Request request = new Request(true, false, false); // Usuário autenticado, sem limite de requisições, sem cache
        authentication.handle(request);
    }
}
