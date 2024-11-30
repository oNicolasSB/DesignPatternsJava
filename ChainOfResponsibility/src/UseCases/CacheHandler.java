package UseCases;

import Abstractions.BaseHandler;

public class CacheHandler extends BaseHandler{

    @Override
    protected boolean process(Request request) {
        System.out.println("Checking cache...");
        if (request.isCached()) {
            System.out.println("Response found in cache. Request ended.");
            return false;
        }
        System.out.println("No response in cache. Continuing...");
        return true;
    }

}
