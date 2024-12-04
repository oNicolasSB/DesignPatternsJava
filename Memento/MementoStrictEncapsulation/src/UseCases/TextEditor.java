package UseCases;

import Abstractions.Memento;
import Abstractions.Originator;

public class TextEditor implements Originator {
    private String text;
    private int cursorPosition;

    // Cria um Memento vinculado ao Originator
    public Memento createMemento() {
        return save();
    }

    // Métodos de edição
    public void setText(String text) {
        this.text = text;
    }

    public void setCursorPosition(int position) {
        this.cursorPosition = position;
    }

    public void printState() {
        System.out.println("Text: " + text + ", Cursor Position: " + cursorPosition);
    }

    @Override
    public Memento save() {
        return new TextEditorMemento(this, text, cursorPosition);
    }

    public void restore(Memento memento) {
        if (memento instanceof TextEditorMemento) {
            ((TextEditorMemento) memento).restore();
        } else {
            throw new IllegalArgumentException("Memento inválido!");
        }
    }

    private static class TextEditorMemento implements Memento {
        private final TextEditor editor;
        private final String savedText;
        private final int savedCursorPosition;

        private TextEditorMemento(TextEditor editor, String text, int cursorPosition) {
            this.editor = editor;
            this.savedText = text;
            this.savedCursorPosition = cursorPosition;
        }

        @Override
        public void restore() {
            editor.setText(savedText);
            editor.setCursorPosition(savedCursorPosition);
        }
    }
}
