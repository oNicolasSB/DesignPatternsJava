import Abstractions.Notifier;
import UseCases.EmailNotifier;
import UseCases.SMSDecorator;
import UseCases.SlackDecorator;

public class App {
    public static void main(String[] args) throws Exception {

        Notifier notifier = new SMSDecorator(
                new SlackDecorator(
                        new EmailNotifier()));
        notifier.send("Mensagem urgente!");
    }
}
