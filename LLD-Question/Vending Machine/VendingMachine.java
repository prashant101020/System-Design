import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

enum OrderStatus{
    FAILED, PENDING, DELIVERED
}
enum VendingState{
    IDLE,ACCEPTING_PAYMENT, DISPENSING
}
enum PaymentStatus{
    PENDING,PROCESSING,SUCCESSFULL,FAILED
}

class OutOfStockException extends Exception{
    OutOfStockException(String msg){
        super(msg);
    }
}
class InsufficientBalanceException extends Exception{
    InsufficientBalanceException(String msg){
        super(msg);
    }
}
class PaymentFailedException extends Exception{
    PaymentFailedException(String msg){
        super(msg);
    }
}

class Item{
    private int id;
    private String itemName;
    private double itemPrice;

    Item(int id,String itemName, double itemPrice){
        this.id=id;
        this.itemName=itemName;
        this.itemPrice=itemPrice;
    }
    public int getId() {
        return id;
    }
    public String getItemName() {
        return itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
}

class Order{
    private int orderId;
    private Item item;
    private int quantity;
    private double totalPrice;
    private OrderStatus orderStatus;
    Order(int orderId,Item item,int quantity,double totalPrice){
        this.orderId=orderId;
        this.item=item;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
        this.orderStatus=OrderStatus.PENDING;
    }
    // Add getters
    public int getOrderId() { return orderId; }
    public Item getItem() { return item; }
    public OrderStatus getStatus() { return orderStatus; }

    // Add setters
    public void setOrderStatus(OrderStatus status) { this.orderStatus = status; }

    @Override
    public String toString(){
        return "Order #" + orderId + ": " + item.getItemName()
                + " x" + quantity + " = $" + totalPrice
                + " | Status: " + orderStatus + " | Change: $" ;
    }
}
class Inventory{
    private Map<Item, Integer> map;
    Inventory(){
        map=new HashMap<>();
    }
    void updateQuantity(Item item,int quantity){
        map.put(item,map.getOrDefault(item, 0)+quantity );
    }
    int getQuantity(Item item){
        return map.getOrDefault(item,0);
    }
    boolean isAvailable(Item item, int quant){
        if(map.getOrDefault(item,0)>=quant){
            return true;
        }
        return false;
    }
    void addItem(Item item, int quant){
        map.put(item,quant);
    }
    Map<Item,Integer> getAllItem(){
        return this.map;
    }
    Item getItemById(int id){
        for(Item item:map.keySet()){
            if(item.getId()==id){
                return item;
            }
        }
        return null;
    }
    void sell(Item item, int quantity){
        map.put(item,map.getOrDefault(item,0)-quantity);
    }
}
class Vending{
    int vendingId;
    VendingState vendingState;
    Inventory inventory;
    Vending(int vendingId,Inventory inventory){
        this.vendingId=vendingId;
        this.inventory=inventory;
        this.vendingState=VendingState.IDLE;
    }

    private void changeState(VendingState newState){
        this.vendingState=newState;
    }

    public void selectItem(){
        if(vendingState!=VendingState.IDLE){
            throw new IllegalStateException("Vending Machine is not in IDLE state");
        }
        changeState(VendingState.ACCEPTING_PAYMENT);
    }
    public void processPayment(){
        if(vendingState!=VendingState.ACCEPTING_PAYMENT){
            throw new IllegalStateException("Vending Machine is not accepting payment");
        }
        changeState(VendingState.DISPENSING);
    }
    public void disPenseItem(){
        if(vendingState!=VendingState.DISPENSING){
            throw new IllegalStateException("Vending Machine is not dispensing");
        }
         changeState(VendingState.IDLE);
    }
    void listAllItem(){
        int num=1;
        for(Item item:inventory.getAllItem().keySet()){
            System.out.println("Item "+num+": "+item.getItemName());
            num++;
        }
    }


}
class Payment{
    double amount;
    PaymentStatus paymentStatus;
    Payment(double amount){
        this.amount=amount;
        this.paymentStatus=PaymentStatus.PENDING;
    }
}
interface PaymentHandler{
    PaymentResult pay(double amount);
}
class Card implements PaymentHandler{
    public PaymentResult pay(double amount){
       System.out.println("Payment Processing via Card: "+amount);
       System.out.println("Payment Done");
       return new PaymentResult(true, amount, 0, "Payment Successful");
    }
}
class UPI implements PaymentHandler{
    public PaymentResult pay(double amount){
        System.out.println("Payment Processing with UPI: "+amount);
        System.out.println("Payment Done");
        return new PaymentResult(true, amount, 0, "Payment Successful");

    }
}
class Cash implements PaymentHandler{
    public PaymentResult pay(double amount){
        System.out.println("Payment Processing: "+amount);
        System.out.println("Payment Done");
        return new PaymentResult(true, amount, 0, "Payment Successful");

    }
    public PaymentResult pay(double amount, double total) throws InsufficientBalanceException {
        if(total<amount){
            System.out.println("please insert rest "+(total-amount));
            throw new InsufficientBalanceException("Insufficient Balance");
        }else{
            System.out.println("Payment Process...");
            if(amount==total){
                System.out.println("Payment Done");
            }else{
                System.out.println("Here is you rest change "+ (amount-total));

            }
        }
        return new PaymentResult(true, amount, (amount-total), "Payment Successful");
    }
}
class PaymentResult{
    private boolean successful;
    private double amountPaid;
    private double change;
    private String message;

    PaymentResult(boolean successful, double amountPaid, double change, String message){
        this.successful=successful;
        this.amountPaid=amountPaid;
        this.change=change;
        this.message=message;
    }
    public boolean isSuccessful() {
        return successful;
    }
    public double isAmountPaid() {
        return amountPaid;
    }
    public double getChange() {
        return change;
    }
}
public class VendingMachine{
    public static void main(String[] main) throws OutOfStockException, InsufficientBalanceException {
        Scanner sc = new Scanner(System.in);
        Inventory inv = createVending();
        AtomicInteger orderIdGenerator = new AtomicInteger(0);
        while(true) {
            System.out.println("Listing Itemsss.........");

            Vending vending = new Vending(1, inv);
            vending.listAllItem();
            System.out.println("Enter Item number to buy.........");
            int num = sc.nextInt();
            System.out.println("Enter the quantity");
            int quant = sc.nextInt();
            System.out.println("Checking if quantity: " + quant + " Available for Item " + num);
            Item item=inv.getItemById(num);
            if(item==null){
                System.out.println("Invalid item Id");
                continue;
            }
            Order order;
            if (inv.isAvailable(item, quant)) {
                System.out.println("Item Available");
                System.out.println("Calculating total price");
                vending.selectItem();
                double totalPrice = quant * inv.getItemById(num).getItemPrice();
               order= new Order(orderIdGenerator.addAndGet(1), item, quant, totalPrice);
                System.out.println("Total Price: " + totalPrice);
                System.out.println("How you want to pay:");
                System.out.println("1. Card \n 2. UPI \n 3. Cash");
                int payOption = sc.nextInt();
                PaymentHandler paymentHandler;
                PaymentResult result;
                vending.processPayment();
                if (payOption == 1) {
                    paymentHandler = new Card();
                     result= paymentHandler.pay(totalPrice);
                } else if (payOption == 2) {
                    paymentHandler = new UPI();

                    result= paymentHandler.pay((int) totalPrice);
                } else {
                    paymentHandler = new Cash();
                    System.out.println("Please insert cash: ");
                    double cashAmount = sc.nextDouble();
                    result= ((Cash) paymentHandler).pay(cashAmount, totalPrice);
                }
                if(result.isSuccessful()){
                    inv.sell(inv.getItemById(num), quant);
                    vending.disPenseItem();
                    order.setOrderStatus(OrderStatus.DELIVERED);
                }else{
                    order.setOrderStatus(OrderStatus.FAILED);
                }
                order.toString();

            } else {
                System.out.println("Item Not Available");
                throw new OutOfStockException("Item Not Available");
            }
        }
    }

  static  Inventory createVending(){
        Item item1 = new Item(1,"Coke",1.5);
        Item item2 = new Item(2,"Pepsi",3.5);
        Item item3 = new Item(3,"Water",1.0);
        Item item4 = new Item(4,"Lays",2.5);
        Item item5 = new Item(5,"Soda",1.0);
        Item item6 = new Item(6,"Chocolate",1.0);
        Inventory inventory=new Inventory();
        inventory.addItem(item1,2);
        inventory.addItem(item2,1);
        inventory.addItem(item3,5);
        inventory.addItem(item4,12);
        inventory.addItem(item5,16);
        inventory.addItem(item6,0);
        return inventory;
    }
}
