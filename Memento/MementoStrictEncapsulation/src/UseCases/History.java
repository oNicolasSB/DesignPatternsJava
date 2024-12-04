package UseCases;

import Abstractions.Memento;

public class History {
    private final java.util.Stack<Memento> history = new java.util.Stack<>();

    public void push(Memento memento) {
        history.push(memento);
    }

    public Memento pop() {
        return history.isEmpty() ? null : history.pop();
    }

    public void undo(TextEditor editor) {
        Memento memento = pop();
        if (memento != null) {
            editor.restore(memento);
        } else {
            System.out.println("Nenhum estado para desfazer!");
        }
    }
}
