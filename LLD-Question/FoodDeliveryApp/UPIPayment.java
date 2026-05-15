public class UPIPayment implements PaymentProcessor{

    public PaymentStatus pay(Order order) throws InterruptedException {

        Thread.sleep(1000);
        System.out.print("Processing UPI Payment...");
        System.out.print("SUCCESS");
        order.setOrderStatus(OrderStatus.ACCEPTED);
        return PaymentStatus.SUCCESS;
    }

}
