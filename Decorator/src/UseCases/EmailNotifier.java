package UseCases;

import Abstractions.Notifier;

public class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("Enviando email: " + message);
    }

}
