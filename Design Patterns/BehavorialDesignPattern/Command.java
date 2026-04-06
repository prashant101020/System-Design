package BehavorialDesignPattern;

import java.util.Stack;

class TextEditor{
    private StringBuilder content = new StringBuilder();
    public void append(String text){
        content.append(text);
    }
    public String deleteLast(int length){
        int start=Math.max(0,content.length()-length);
        String deletedText=content.substring(start);
        content.delete(start,content.length());
        return deletedText;
    }
}

interface EditorCommand{
    void execute();
    void undo();
}

class TypeCommand implements EditorCommand{
    private TextEditor editor;
    private String text;

    public TypeCommand(TextEditor editor,String text){
        this.editor=editor;
        this.text=text;
    }

    @Override
    public void execute() {
        editor.append(text);
    }

    @Override
    public void undo() {
    editor.deleteLast(text.length());
    System.out.println("Undo typing: "+text);
    }
}
class DeleteCommand implements EditorCommand{
    private TextEditor editor;
    private int length;
    private String deletedText;

    public DeleteCommand(TextEditor editor,int length){
        this.editor=editor;
        this.length=length;
    }

    @Override
    public void execute() {
        deletedText=editor.deleteLast(length);
    }

    @Override
    public void undo() {
        editor.append(deletedText);
        System.out.println("Undo deletion: "+deletedText);
    }
}
class EditorInvoker{
   private final Stack<EditorCommand> undoStack = new Stack<>();
   private final Stack<EditorCommand> redoStack = new Stack<>();

   public void executeCommand(EditorCommand command){
       command.execute();
       undoStack.push(command);
       redoStack.clear();
   }
    public void undo() {
        if (!undoStack.isEmpty()) {
            EditorCommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }
       public void redo() {
            if (!redoStack.isEmpty()) {
                EditorCommand command = redoStack.pop();
                command.execute();
                undoStack.push(command);
            }
        }
}
public class Command {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorInvoker invoker = new EditorInvoker();

        invoker.executeCommand(new TypeCommand(editor, "Hello "));
        invoker.executeCommand(new TypeCommand(editor, "World!"));
        invoker.executeCommand(new DeleteCommand(editor, 6));

        invoker.undo();
        invoker.undo();
        invoker.redo();
    }
}
