package BehavorialDesignPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//The Observer Design pattern is a behavioral design pattern that defined a one to many
//dependency betweeen objects so that one object changes statte then  all its
//dependents are notified and updated automatically
interface StockObserver {
    void update(StockExchange exchange);
}

class StockExchange{
    private final Map<String, Double> stockPrices = new HashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();
    private String lastUpdatedSymbol;
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }
    public void notifyObserver(){
        for(StockObserver stockObserver:observers){
            stockObserver.update(this);
        }
    }
    public void updatePrice(String Symbol, double price){
        stockPrices.put(Symbol, price);
        lastUpdatedSymbol=Symbol;
        notifyObserver();
    }
        public Map<String, Double> getStockPrices() {
            return stockPrices;
        }
        public String getLastUpdatedSymbol(){
        return lastUpdatedSymbol;
        }
}

class priceDisplay implements StockObserver{
    @Override
    public void update(StockExchange exchange) {
        System.out.println("Price Display: Updated price for " + exchange.getLastUpdatedSymbol() + " is " + exchange.getStockPrices().get(exchange.getLastUpdatedSymbol()));
    }
}

class AlertService implements StockObserver{
    private final double threshold;
    public AlertService(double threshold) {
        this.threshold = threshold;
    }
    @Override
    public void update(StockExchange exchange) {
        double price = exchange.getStockPrices().get(exchange.getLastUpdatedSymbol());
        if(price>threshold){
            System.out.println("Alert: Price of " + exchange.getLastUpdatedSymbol() + " has exceeded the threshold! Current price: " + price);
        }
    }
}

public class Observer {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();
        priceDisplay priceDisplay = new priceDisplay();
        AlertService alertService = new AlertService(150.0);
        stockExchange.registerObserver(priceDisplay);
        stockExchange.registerObserver(alertService);
        stockExchange.updatePrice("AAPL", 145.0);
        stockExchange.updatePrice("AAPL", 155.0);
    }
}
