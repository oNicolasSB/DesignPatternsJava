package UseCases;

import Abstractions.Command;

public class SaveCommand implements Command {
    private TextEditor editor;

    public SaveCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.save();
    }
}
