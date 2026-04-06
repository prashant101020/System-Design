package BehavorialDesignPattern;
// Updated memento stores both content and cursor position
class TextEditorMemento {
    private final String state;
    private final int cursorPosition;

    public TextEditorMemento(String state, int cursorPosition) {
        this.state = state;
        this.cursorPosition = cursorPosition;
    }

    public String getState() { return state; }
    public int getCursorPosition() { return cursorPosition; }
}

// Updated editor saves and restores cursor too
class TextEditor1 {
    private String content = "";
    private int cursorPosition = 0;

    public void type(String newText) {
        content += newText;
        cursorPosition = content.length();
    }

    public TextEditorMemento save() {
        return new TextEditorMemento(content, cursorPosition);
    }

    public void restore(TextEditorMemento memento) {
        content = memento.getState();
        cursorPosition = memento.getCursorPosition();
    }

    public String getContent() { return content; }
    // getContent(), getCursorPosition() ...
}


public class Momento {
    public static void main(String[] args) {
        TextEditor1 editor = new TextEditor1();
        editor.type("Hello, ");
        TextEditorMemento memento1 = editor.save();

        editor.type("world!");
        TextEditorMemento memento2 = editor.save();

        System.out.println("Current content: " + editor.getContent()); // Hello, world!

        editor.restore(memento1);
        System.out.println("After undo: " + editor.getContent()); // Hello,
    }
}
