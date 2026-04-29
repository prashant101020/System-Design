import java.time.LocalDateTime;

public class Ticket {
    private  String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime start;
    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.start=LocalDateTime.now();
    }
    LocalDateTime getStart(){
        return this.start;
    }
}
