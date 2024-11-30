package Abstractions;

import UseCases.Request;

public interface Handler {
    Handler setNext(Handler next);

    void handle(Request request);
}
