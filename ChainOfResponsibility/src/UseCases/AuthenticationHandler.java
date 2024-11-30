package UseCases;

import Abstractions.BaseHandler;

public class AuthenticationHandler extends BaseHandler {

    @Override
    protected boolean process(Request request) {
        System.out.println("Authenticating User");
        if (request.isAuthenticated()) {
            System.out.println("User authenticated successfully!");
            return true;
        }
        System.out.println("Authentication failed");
        return false;
    }

}
