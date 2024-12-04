import UseCases.History;
import UseCases.TextEditor;

public class App {
    public static void main(String[] args) throws Exception {
        TextEditor editor = new TextEditor();
        History history = new History();

        // Starting State
        editor.setContent("Texto inicial.");
        System.out.println("Conteúdo atual: " + editor.getContent());

        // Save state
        history.push(editor.save());

        // Modify text
        editor.setContent("Texto modificado.");
        System.out.println("Conteúdo atual: " + editor.getContent());

        // Save state
        history.push(editor.save());

        // More modifications
        editor.setContent("Texto final.");
        System.out.println("Conteúdo atual: " + editor.getContent());

        // Undo last changes
        if (!history.isEmpty()) {
            editor.restore(history.pop());
            System.out.println("Desfazer: " + editor.getContent());
        }

        // Undo again
        if (!history.isEmpty()) {
            editor.restore(history.pop());
            System.out.println("Desfazer: " + editor.getContent());
        }
    }
}
