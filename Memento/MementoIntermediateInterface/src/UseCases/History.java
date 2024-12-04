package UseCases;

import Abstractions.Memento;

public class History {
    private final java.util.Stack<Memento> history = new java.util.Stack<>();

    public void save(Memento memento) {
        history.push(memento);
    }

    public Memento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}
