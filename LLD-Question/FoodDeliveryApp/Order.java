import javax.annotation.processing.Generated;
import java.util.List;

public class Order {
    int orderId;
    User user;
    List<Item> items;
    double totalPrice;
    OrderStatus orderStatus; // e.g., "Placed", "Preparing", "Out for Delivery", "Delivered"

     public Order(int orderId, User user, List<Item> items) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
        this.totalPrice = calculateTotalPrice(items);
        this.orderStatus = OrderStatus.PLACED; // Initial status
    }

    private double calculateTotalPrice(List<Item> items) {
        double total = 0.0;
        for (Item item : items) {
            total += item.getItemPrice();
        }
        return total;
    }
    void updateOrderStatus(OrderStatus orderStatus){
         this.orderStatus=orderStatus;
    }
    OrderStatus getOrderStatus(){
         return this.orderStatus;
    }
    void setOrderStatus(OrderStatus orderStatus){
         this.orderStatus=orderStatus;
    }

}
