package BehavorialDesignPattern;

class CashRequest{
    int amount;
    public CashRequest(int amount) {
        this.amount= amount;
    }
}
interface CashHandler{
    void setNext(CashHandler handler);
    void dispense(CashRequest request);
}
abstract class BaseCashHandler implements CashHandler {
    protected CashHandler nextHandler;
    protected int denomination;

    BaseCashHandler(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public void setNext(CashHandler handler) {
        this.nextHandler = handler;
    }

    public void dispense(CashRequest request) {
        if (request.amount >= denomination) {
            int noteCount = request.amount / denomination;
            request.amount = request.amount % denomination;
            System.out.println("Dispensing " + noteCount + " notes of " + denomination);
        }
        forward(request);
    }

    protected void forward(CashRequest request) {
        if (nextHandler != null) {
            nextHandler.dispense(request);
        } else if (request.amount > 0) {
            System.out.println("Cannot dispense remaining amount: " + request.amount);
        }
    }
}
    class HundredRupeeHandler extends BaseCashHandler{
        public HundredRupeeHandler(){
            super(100);
        }
    }

    class fiftyRupeeHandler extends BaseCashHandler{
        public fiftyRupeeHandler(){
            super(50);
        }
    }
    class twentyRupeeHandler extends BaseCashHandler{
        public twentyRupeeHandler(){
            super(20);
        }
    }
    class tenRupeeHandler extends BaseCashHandler{
        public tenRupeeHandler(){
            super(10);
        }
    }

public class ChainOfRensponsibility {
    public static void main(String[] args){
        CashHandler hundred=new HundredRupeeHandler();
        CashHandler fifty=new fiftyRupeeHandler();
        CashHandler twenty=new twentyRupeeHandler();
        CashHandler ten=new tenRupeeHandler();
        hundred.setNext(fifty);
        fifty.setNext(twenty);
        twenty.setNext(ten);

        CashRequest request=new CashRequest(680);
        hundred.dispense(request);
    }
}
