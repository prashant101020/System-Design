package CreationalDesignPattern;

public class AbstractFactory {
    public static void main(String args[]){
    String os=System.getProperty("os.name");
    GuiFactory factory;
    System.out.println(os);
    if(os.contains("Windows")){
        factory=new WindowsFactory();
    }else{
        factory=new macOsFactory();
    }
    Application app=new Application(factory);
    app.renderUI();
    }
}

interface Button{
void paint();
void onClick();
}

interface Checkbox{
    void paint();
    void onSelect();
}

class windowsButton implements Button{
    @Override
    public void paint() {
        System.out.print("Windows CreationalDesignPattern.Button painting....");
    }
    @Override
    public void onClick() {
        System.out.print("Windows CreationalDesignPattern.Button onClick....");
    }
}

class WindowsCheckBox implements Checkbox{
    @Override
    public void paint() {
        System.out.print("Windows Check Box painting....");
    }

    @Override
    public void onSelect() {
        System.out.print("Windows CheckBox OnSelect....");
    }
}


class macOsButton implements Button{
    @Override
    public void paint() {
        System.out.print("Mac OS CreationalDesignPattern.Button painting....");
    }
    @Override
    public void onClick() {
        System.out.print("Mac OS CreationalDesignPattern.Button onClick....");
    }
}

class macOsCheckBox implements Checkbox{
    @Override
    public void paint() {
        System.out.print("Mac OS Check Box painting....");
    }

    @Override
    public void onSelect() {
        System.out.print("Mac OS CheckBox OnSelect....");
    }
}

interface GuiFactory{
    Button createButton();
    Checkbox createCheckBoX();
}

class WindowsFactory implements GuiFactory{
    @Override
    public Button createButton() {
        return new windowsButton();
    }

    @Override
    public Checkbox createCheckBoX() {
        return new WindowsCheckBox();
    }
}

class macOsFactory implements GuiFactory{

    @Override
    public Button createButton() {
        return new macOsButton();
    }

    @Override
    public Checkbox createCheckBoX() {
        return new macOsCheckBox();
    }
}

class Application{
    private final Button button;
    private final Checkbox checkbox;

    public Application(GuiFactory factory){
        this.button= factory.createButton();
        this.checkbox=factory.createCheckBoX();
    }
    public void renderUI(){
        button.paint();
        checkbox.paint();
    }
}