import java.util.ArrayList;
import java.util.List;

public class Restaurant{
    int restaurantId;
    String restaurantName;
    String restaurantAddress;
    Menu restaurantMenu;
    List<Order> restaurantOrders;

    public Restaurant(int restaurantId, String restaurantName, String restaurantAddress, Menu restaurantMenu) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantMenu = restaurantMenu;
        restaurantOrders= new ArrayList<>();
    }
}