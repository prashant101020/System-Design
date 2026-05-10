import java.util.ArrayList;
import java.util.List;

public class FoodDeliiveryApplication {
    public static void main(String[] main) throws InterruptedException {
        Item item=new Item(1,"Shahi Paneer", 250);

        Item item2=new Item(2,"Kadhai Paneer", 350);

        Item item3=new Item(3,"Masala dala", 157);

        Item item4=new Item(4,"dahi", 50);
        Menu menu=new Menu();
        menu.addItem(item);
        menu.addItem(item3);
        Restaurant restaurant=new Restaurant(1,"Garam Masala", "Sector 10, Airoli", menu);

        Restaurant restaurant2=new Restaurant(2,"Punjabi dhaba", "Sector 20, Airoli", menu);

        Restaurant restaurant3=new Restaurant(3,"SPince and ice", "Sector 10, Airoli", menu);

        Restaurant restaurant4=new Restaurant(4,"Suruchi", "Sector 1, Airoli", menu);

        Restaurant restaurant5=new Restaurant(5,"Sai prakash", "Sector 4, Airoli", menu);

        RestaurantService restaurantService=new RestaurantService();
        restaurantService.addRestaurant(restaurant);
        restaurantService.addRestaurant(restaurant2);
        restaurantService.addRestaurant(restaurant3);
        restaurantService.addRestaurant(restaurant4);
        restaurantService.addRestaurant(restaurant5);
        restaurantService.listAll();
        User prashant=new User(1,"Prashant", "Airoli", "845413");
        OrderService oRderService=new OrderService();
        List<Item> itemList=new ArrayList<>();
        itemList.add(item);
        itemList.add(item3);
        Order order=oRderService.placeOrder(restaurant,prashant,itemList,"CARD");

        String ss= """
                order Status: %d 
                """;
        System.out.println("Order Status of Order"+order.orderId+" for "+order.user.userName+" is "+order.getOrderStatus());

        oRderService.updateOrderStatus(order, OrderStatus.DELIVERED);
        System.out.println("Order Status of Order"+order.orderId+" for "+order.user.userName+" is "+order.getOrderStatus());

    }
}
