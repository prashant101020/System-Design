package BehavorialDesignPattern;

//The behavioral design pattern that allows you to separate an algorithm from the objects on which it operates,
// enabling you to add new operations without modifying the existing object structure.
interface Shape {
    void accept(ShapeVisitor visitor);
}
class Circle implements Shape{
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }
}
class Rectangle implements Shape{
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }
}
interface ShapeVisitor {
    void visitCircle(Circle circle);
    void visitRectangle(Rectangle rectangle);
}

class AreaCalculator implements ShapeVisitor{
    private double area;
    @Override
    public void visitCircle(Circle circle) {
        area = Math.PI * Math.pow(circle.getRadius(), 2);
    }
    @Override
    public void visitRectangle(Rectangle rectangle) {
        area = rectangle.getWidth() * rectangle.getHeight();
    }
    public double getArea() {
        return area;
    }
}
class scgExportrerVisitor implements ShapeVisitor{
    @Override
    public void visitCircle(Circle circle) {
        System.out.println("Exporting circle with radius: " + circle.getRadius());
    }
    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("Exporting rectangle with width: " + rectangle.getWidth() + " and height: " + rectangle.getHeight());
    }
}
public class VisitorDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        AreaCalculator areaCalculator = new AreaCalculator();
        circle.accept(areaCalculator);
        System.out.println("Area of circle: " + areaCalculator.getArea());

        rectangle.accept(areaCalculator);
        System.out.println("Area of rectangle: " + areaCalculator.getArea());

        scgExportrerVisitor exporterVisitor = new scgExportrerVisitor();
        circle.accept(exporterVisitor);
        rectangle.accept(exporterVisitor);
    }

}
