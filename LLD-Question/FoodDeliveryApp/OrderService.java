import java.util.List;

public class OrderService {
    PaymentProcessor paymentProcessor;
    Order placeOrder(Restaurant restaurant, User user, List<Item> items,String paymentMethod ) throws InterruptedException {
        Order order=new Order(1,user,items);
        if(paymentMethod.equals("UPI")){
             paymentProcessor= new UPIPayment();
             paymentProcessor.pay(order);
        }else if(paymentMethod.equals("CARD")){
            paymentProcessor =new CardPayment();
            paymentProcessor.pay(order);
        }
        order.updateOrderStatus(OrderStatus.PLACED);
        restaurant.restaurantOrders.add(order);
        return order;
    }

    void cancelOrder(Order order){
    order.updateOrderStatus(OrderStatus.REJECTED);
    }

    void updateOrderStatus(Order order, OrderStatus status){
    order.updateOrderStatus(status);
    }

    OrderStatus trackOrder(Order order){
    return order.getOrderStatus();
    }

}
