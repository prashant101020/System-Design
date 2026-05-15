public interface PaymentProcessor {
    PaymentStatus pay(Order order) throws InterruptedException;
}
