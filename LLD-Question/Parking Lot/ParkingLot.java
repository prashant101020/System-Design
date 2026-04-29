import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    public static ParkingLot instance;
    private List<Floor> floors;
    private ParkingLot(){
        this.floors=new ArrayList<>();
    }
    public synchronized Ticket entry(Vehicle vehicle){
    ParkingSpot parkingSpot=getSpot(vehicle.getType());
    if(parkingSpot!=null){
        parkingSpot.park(vehicle);
        return new Ticket("TICKET-"+vehicle.getLisencePlate(),vehicle,parkingSpot);
    }
    throw new RuntimeException("No Available Spots");
    }
    private ParkingSpot getSpot(VehicleType type){
        for(Floor floor:floors){
            for(ParkingSpot parkingSpot: floor.getParkingSpotList()){
                if(parkingSpot.isAvailable() && parkingSpot.getSpotType().equals(type)){
                    return parkingSpot;
                }
            }
        }
        throw null;
    }
    public static ParkingLot getInstance(){
        if(instance==null){
            instance=new ParkingLot();

        }
        return  instance;
    }
public synchronized void exit(Ticket ticket){
    Duration duration=Duration.between(ticket.getStart(),LocalDateTime.now());
    long hours=duration.toHours();
    long minutes=duration.toMinutes();
        Bill bill=new Bill(25,(int)hours,(int)minutes);
    System.out.print("Here is your bill Sir"+bill.toString());
    }
}
