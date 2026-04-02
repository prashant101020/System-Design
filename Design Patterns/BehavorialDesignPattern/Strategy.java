package BehavorialDesignPattern;
//This is behavorial design pattern which is used to define a family of algorithms,
//encapsulating each one and makes them interchangeable.

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class cryptoPayment implements PaymentStrategy {
    private String walletAddress;

    public cryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Crypto.");
    }
}

class CheckoutService{
    private PaymentStrategy paymentStrategy;
    public CheckoutService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method.");
            return;
        }
        paymentStrategy.pay(amount);
    }
}
public class Strategy {

    public static void main(String[] args){
        CheckoutService checkoutService=   new CheckoutService(new CreditCardPayment("1234-5678-9012-3456", "John Doe", "123"));
        checkoutService.checkout(100);
        checkoutService.setPaymentStrategy(new PayPalPayment("pyss02026@gmail.com"));
        checkoutService.checkout(500);
        checkoutService.setPaymentStrategy(new cryptoPayment("0x1234567890abcdef"));
        checkoutService.checkout(1000);

    }
}
