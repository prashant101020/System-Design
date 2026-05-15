public class Bill {

    int base;
    int hours;
    int minutes;
    public Bill(int base, int hours, int minutes){
        this.base=base;
        this.hours=hours;
        this.minutes=minutes;
    }
    public String toString(){
       return String.format(
                """
                Base Price: %d
                hours: %d
                minutes: %d
                additional charge: 0
                Total: %d
                        
                        """,base,hours,minutes,(hours*base)+(minutes+10)*base);
    }
}
