public class CardPayment implements PaymentProcessor{

    public PaymentStatus pay(Order order) throws InterruptedException {

        Thread.sleep(1000);
        System.out.print("Processing CARD Payment...");
        System.out.print("SUCCESS");
        order.setOrderStatus(OrderStatus.ACCEPTED);
        return PaymentStatus.SUCCESS;
    }

}
