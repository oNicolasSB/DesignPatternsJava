package UseCases;

import Abstractions.Memento;

public class EditorMemento implements Memento {
    public final String text;
    public final int cursorPosition;

    public EditorMemento(String text, int cursorPosition) {
        this.text = text;
        this.cursorPosition = cursorPosition;
    }

    public String getText() {
        return text;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}
