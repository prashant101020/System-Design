package BehavorialDesignPattern;

//Defining the abstract class that contains the template method and allows its subcall to override certain steps of the algorithm without changing its structure.
class Order{
    private String orderId;
    private double subtotal;

    public Order(String orderId, double subtotal) {
        this.orderId = orderId;
        this.subtotal = subtotal;
    }
    public String getOrderId() {
        return orderId;
    }
    public double getSubtotal() {
        return subtotal;
    }
}

abstract class OrderProcessor {
    public final void processOrder(Order order) {
        validateOrder(order);
        calculateTotal(order);
        saveOrder(order);
    }
    protected abstract void validateOrder(Order order);
    protected abstract void calculateTotal(Order order);
    protected void saveOrder(Order order) {
        System.out.println("Saving order: " + order.getOrderId());
    }
}

class StandardORderProcessr extends OrderProcessor{
    @Override
    protected void validateOrder(Order order) {
        System.out.println("Validating order: " + order.getOrderId());
    }
    @Override
    protected void calculateTotal(Order order) {
        double total = order.getSubtotal() * 1.1; // Adding 10% tax
        System.out.println("Total for order " + order.getOrderId() + ": " + total);
    }
}
class primeOrderProcessor extends OrderProcessor{
    @Override
    protected void validateOrder(Order order) {
        System.out.println("Validating prime order: " + order.getOrderId());
    }
    @Override
    protected void calculateTotal(Order order) {
        double total = order.getSubtotal() * 1.05; // Adding 5% tax for prime orders
        System.out.println("Total for prime order " + order.getOrderId() + ": " + total);
    }
}

class InternationalOrderProcessor extends OrderProcessor{
    @Override
    protected void validateOrder(Order order) {
        System.out.println("Validating international order: " + order.getOrderId());
    }
    @Override
    protected void calculateTotal(Order order) {
        double total = order.getSubtotal() * 1.2; // Adding 20% tax for international orders
        System.out.println("Total for international order " + order.getOrderId() + ": " + total);
    }
}
public class TemplateMethod {
    public static void main(String[] args) {
        Order order1 = new Order("001", 100);
        Order order2 = new Order("002", 200);
        Order order3 = new Order("003", 300);

        OrderProcessor standardProcessor = new StandardORderProcessr();
        OrderProcessor primeProcessor = new primeOrderProcessor();
        OrderProcessor internationalProcessor = new InternationalOrderProcessor();

        standardProcessor.processOrder(order1);
        primeProcessor.processOrder(order2);
        internationalProcessor.processOrder(order3);
    }
}
