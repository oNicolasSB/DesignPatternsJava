package UseCases;

import Abstractions.Memento;

public class TextEditor {
    private String text;
    private int cursorPosition;

    public void setState(String text, int cursorPosition) {
        this.text = text;
        this.cursorPosition = cursorPosition;
    }

    public void showState() {
        System.out.println("Text: " + text + " | Cursor position: " + cursorPosition);
    }

    public EditorMemento saveState() {
        return new EditorMemento(text, cursorPosition);
    }

    public void restoreState(Memento memento) {
        if (memento instanceof EditorMemento) {
            EditorMemento editorMemento = (EditorMemento) memento;
            this.text = editorMemento.getText();
            this.cursorPosition = editorMemento.getCursorPosition();
        }
    }
}
