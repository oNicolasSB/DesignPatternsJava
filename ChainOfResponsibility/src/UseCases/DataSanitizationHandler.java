package UseCases;

import Abstractions.BaseHandler;

public class DataSanitizationHandler extends BaseHandler {

    @Override
    protected boolean process(Request request) {
        System.out.println("Sanitizing data...");
        request.sanitize();
        System.out.println("Data sanitized.");
        return true;
    }

}
