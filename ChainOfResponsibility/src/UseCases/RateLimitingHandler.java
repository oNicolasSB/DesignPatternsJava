package UseCases;

import Abstractions.BaseHandler;

public class RateLimitingHandler extends BaseHandler {
    @Override
    protected boolean process(Request request) {
        System.out.println("Checking request limit...");
        if (request.isRateLimitExceeded()) {
            System.out.println("Request limit reached. Blocking request.");
            return false;
        }
        System.out.println("Request rate under limit.");
        return true;
    }

}
