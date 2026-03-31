package StructuralDesignPattern;
//This is a design pattern that attaches additional responsibility to an object dynamically.
//Instead of extending a class through inheritance, it wraps the original object inside another object that adds the new behavior
// Stacking the behaviour while keeping the core intact.

//Components interface
interface TextView {
    void display();
}

//Concrete component
class PlainTextView implements TextView {
   private final String text;

    public PlainTextView(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println(text);
    }
}
// Abstract Decorator
abstract class TextDecorator implements TextView{
    protected final TextView inner;

    public TextDecorator(TextView inner) {
        this.inner = inner;
    }
}

//Concrete Decorator
class BoldDecorator extends TextDecorator{
    public BoldDecorator(TextView inner) {
        super(inner);
    }

    @Override
    public void display() {
        System.out.print("<b>");
        inner.display();
        System.out.print("</b>");
    }
}

class ItalicDecorator extends TextDecorator{
    public ItalicDecorator(TextView inner) {
        super(inner);
    }

    @Override
    public void display() {
        System.out.print("<i>");
        inner.display();
        System.out.print("</i>");
    }
}

class underlineDecorator extends TextDecorator{
    public underlineDecorator(TextView inner) {
        super(inner);
    }

    @Override
    public void display() {
        System.out.print("<u>");
        inner.display();
        System.out.print("</u>");
    }
}


public class Decorator {

    public static void main(String[] args) {
        TextView textView = new PlainTextView("Hello, World!");
        TextView boldTextView = new BoldDecorator(textView);
        TextView italicBoldTextView = new ItalicDecorator(boldTextView);
        TextView underlineItalicBoldTextView = new underlineDecorator(italicBoldTextView);
        TextView italicunderlineview = new ItalicDecorator(new underlineDecorator(textView));
        System.out.println("Plain Text:");
        textView.display();
        System.out.println("\n\nBold Text:");
        boldTextView.display();
        System.out.println("\n\nItalic and Bold Text:");
        italicBoldTextView.display();
        System.out.println("\n\nUnderline, Italic and Bold Text:");
        underlineItalicBoldTextView.display();

        italicunderlineview.display();
    }
}
