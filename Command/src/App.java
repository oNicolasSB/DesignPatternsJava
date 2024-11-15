import Abstractions.Command;
import UseCases.Button;
import UseCases.CopyCommand;
import UseCases.PasteCommand;
import UseCases.SaveCommand;
import UseCases.TextEditor;

public class App {
    public static void main(String[] args) throws Exception {
        // Destinatário: Editor de Texto
        TextEditor editor = new TextEditor();

        // Comandos concretos
        Command copy = new CopyCommand(editor);
        Command paste = new PasteCommand(editor);
        Command save = new SaveCommand(editor);

        // Botões configurados com comandos
        Button copyButton = new Button(copy);
        Button pasteButton = new Button(paste);
        Button saveButton = new Button(save);

        // Simulando cliques nos botões
        copyButton.click(); // Texto copiado para a área de transferência.
        pasteButton.click(); // Texto colado no documento.
        saveButton.click(); // Documento salvo com sucesso.
    }
}
