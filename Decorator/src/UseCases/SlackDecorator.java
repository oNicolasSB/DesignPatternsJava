package UseCases;

import Abstractions.Notifier;
import Abstractions.NotifierDecorator;

public class SlackDecorator extends NotifierDecorator {

    public SlackDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Enviando Slack: " + message);
    }

}
