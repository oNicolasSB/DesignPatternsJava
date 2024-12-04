import Abstractions.Memento;
import UseCases.History;
import UseCases.TextEditor;

public class App {
    public static void main(String[] args) throws Exception {
        TextEditor editor = new TextEditor();
        History history = new History();

        // Estado inicial
        editor.setText("Hello");
        editor.setCursorPosition(5);
        editor.printState();
        history.push(editor.createMemento()); // Salva o estado atual

        // Modifica o estado
        editor.setText("Hello, World!");
        editor.setCursorPosition(13);
        editor.printState();
        history.push(editor.createMemento()); // Salva o novo estado

        // Modifica novamente
        editor.setText("Hello, Memento!");
        editor.setCursorPosition(15);
        editor.printState();

        // Desfazer uma alteração
        history.undo(editor);
        editor.printState();

        // Desfazer outra alteração
        history.undo(editor);
        editor.printState();
    }
}
