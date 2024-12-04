package UseCases;

import java.util.Stack;

public class History {
    private final Stack<TextEditor.Memento> mementos = new Stack<>();

    public void push(TextEditor.Memento memento) {
        mementos.push(memento);
    }

    public TextEditor.Memento pop() {
        return mementos.pop();
    }

    public boolean isEmpty() {
        return mementos.isEmpty();
    }
}
