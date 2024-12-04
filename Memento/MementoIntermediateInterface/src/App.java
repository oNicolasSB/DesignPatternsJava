import Abstractions.Memento;
import UseCases.History;
import UseCases.TextEditor;

public class App {
    public static void main(String[] args) throws Exception {
        TextEditor editor = new TextEditor();
        History history = new History();

        // set starting state
        editor.setState("Hello, world!", 12);
        editor.showState();

        // saves current state
        history.save((Memento) editor.saveState());

        // alter state
        editor.setState("Hello, design patterns!", 21);
        editor.showState();

        // save current state
        history.save((Memento) editor.saveState());

        // do another action
        editor.setState("Memento in action!", 25);
        editor.showState();

        // undo last action
        Memento memento = history.undo();
        if (memento != null) {
            editor.restoreState(memento);
            editor.showState();
        }

        // undo again
        memento = history.undo();
        if (memento != null) {
            editor.restoreState(memento);
            editor.showState();
        }
    }
}
